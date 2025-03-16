package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.IdentificationType;
import com.api.supremeAdmision.repository.IdentificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/identification-types")
public class IdentificationTypeController {

    @Autowired
    private IdentificationTypeRepository identificationTypeRepository;

    @GetMapping
    public List<IdentificationType> getIdentificationTypes() {
        return identificationTypeRepository.getIdentificationTypes();
    }
}
