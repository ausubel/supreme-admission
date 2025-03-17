package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.service.ExamKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin
@RestController
@RequestMapping("/api/exam-keys")
public class ExamKeyController {

    private static final Logger logger = LoggerFactory.getLogger(ExamKeyController.class);
    private final ExamKeyService examKeyService;

    @Autowired
    public ExamKeyController(ExamKeyService examKeyService) {
        this.examKeyService = examKeyService;
    }

    /**
     * Endpoint para exportar las claves de examen en formato CSV
     * @return Archivo CSV con las claves de examen
     */
    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportExamKeysCSV() {
        logger.info("Recibida solicitud para exportar claves de examen en formato CSV");
        
        try {
            String csvContent = examKeyService.generateExamKeysCSV();
            
            if (csvContent.isEmpty()) {
                logger.warn("No se encontraron claves de examen para exportar");
                return ResponseEntity.noContent().build();
            }
            
            // Generar nombre de archivo con fecha y hora actual
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "claves_examen_" + timestamp + ".csv";
            
            // Configurar encabezados para descarga de archivo
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            logger.info("Exportando CSV de claves de examen con nombre: {}", filename);
            
            return new ResponseEntity<>(csvContent.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al exportar claves de examen: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
