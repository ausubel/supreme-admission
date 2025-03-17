package com.api.supremeAdmision.service;

import com.api.supremeAdmision.model.AdmisionProcess;
import com.api.supremeAdmision.model.StartAdmisionProcessRequest;
import com.api.supremeAdmision.repository.admisionprocess.AdmisionProcessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdmisionProcessService {

    private static final Logger logger = LoggerFactory.getLogger(AdmisionProcessService.class);

    private final AdmisionProcessRepository admisionProcessRepository;

    @Autowired
    public AdmisionProcessService(AdmisionProcessRepository admisionProcessRepository) {
        this.admisionProcessRepository = admisionProcessRepository;
    }

    public List<AdmisionProcess> getAdmisionProcesses() {
        return admisionProcessRepository.getAdmisionProcesses();
    }

    public AdmisionProcess findActiveProcess() {
        return admisionProcessRepository.findActiveProcess();
    }

    @Transactional
    public String startAdmisionProcess(StartAdmisionProcessRequest request) {
        logger.info("Iniciando proceso de admisión: {}", request.getName());
        try {
            String result = admisionProcessRepository.startAdmisionProcess(
                    request.getName(), 
                    request.getStartDate(), 
                    request.getEndDate());
            logger.info("Proceso de admisión iniciado exitosamente: {}", request.getName());
            return result;
        } catch (Exception e) {
            logger.error("Error al iniciar proceso de admisión: {}", request.getName(), e);
            throw new RuntimeException("Error al iniciar proceso de admisión: " + e.getMessage(), e);
        }
    }
}
