package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.service.ExamPdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamPdfController {

    private static final Logger logger = LoggerFactory.getLogger(ExamPdfController.class);

    private final ExamPdfService examPdfService;

    @Autowired
    public ExamPdfController(ExamPdfService examPdfService) {
        this.examPdfService = examPdfService;
    }

    /**
     * Genera un PDF para un examen específico
     * @param examId ID del examen
     * @return ID del documento generado
     */
    @PostMapping("/{examId}/generate-pdf")
    public ResponseEntity<String> generateExamPdf(@PathVariable int examId) {
        logger.info("Solicitud para generar PDF del examen ID: {}", examId);
        try {
            String documentId = examPdfService.generateExamPdf(examId);
            return ResponseEntity.ok(documentId);
        } catch (Exception e) {
            logger.error("Error al generar PDF para el examen ID: {}", examId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar PDF: " + e.getMessage());
        }
    }

    /**
     * Descarga el PDF de un examen
     * @param examId ID del examen
     * @return Archivo PDF para descargar
     */
    @GetMapping("/{examId}/download-pdf")
    public ResponseEntity<byte[]> downloadExamPdf(@PathVariable int examId) {
        logger.info("Solicitud para descargar PDF del examen ID: {}", examId);
        try {
            byte[] pdfContent = examPdfService.getExamPdfContent(examId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "examen-" + examId + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al descargar PDF para el examen ID: {}", examId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
