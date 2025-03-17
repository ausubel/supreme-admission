package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.AdmisionProcess;
import com.api.supremeAdmision.model.StartAdmisionProcessRequest;
import com.api.supremeAdmision.service.AdmisionProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admision-processes")
public class AdmisionProcessController {

    private static final Logger logger = LoggerFactory.getLogger(AdmisionProcessController.class);

    private final AdmisionProcessService admisionProcessService;

    @Autowired
    public AdmisionProcessController(AdmisionProcessService admisionProcessService) {
        this.admisionProcessService = admisionProcessService;
    }

    @GetMapping
    public List<AdmisionProcess> getAdmisionProcesses() {
        return admisionProcessService.getAdmisionProcesses();
    }
    
    @GetMapping("/active")
    public ResponseEntity<AdmisionProcess> getActiveProcess() {
        AdmisionProcess activeProcess = admisionProcessService.findActiveProcess();
        if (activeProcess == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activeProcess);
    }
    
    /**
     * Inicia un nuevo proceso de admisión
     * @param request Datos del proceso de admisión a iniciar
     * @return Mensaje de éxito o error
     */
    @PostMapping("/start")
    public ResponseEntity<String> startAdmisionProcess(@RequestBody StartAdmisionProcessRequest request) {
        logger.info("Recibida solicitud para iniciar proceso de admisión: {}", request.getName());
        try {
            String result = admisionProcessService.startAdmisionProcess(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error al iniciar proceso de admisión: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al iniciar proceso de admisión: " + e.getMessage());
        }
    }
}
