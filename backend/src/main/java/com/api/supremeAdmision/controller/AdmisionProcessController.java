package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.AdmisionProcess;
import com.api.supremeAdmision.repository.admisionprocess.AdmisionProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admision-processes")
public class AdmisionProcessController {

    @Autowired
    private AdmisionProcessRepository admisionProcessRepository;

    @GetMapping
    public List<AdmisionProcess> getAdmisionProcesses() {
        return admisionProcessRepository.getAdmisionProcesses();
    }
}
