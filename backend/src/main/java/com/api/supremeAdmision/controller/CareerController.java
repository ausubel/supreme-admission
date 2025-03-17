package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.Career;
import com.api.supremeAdmision.repository.career.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/careers")
public class CareerController {

    @Autowired
    private CareerRepository careerRepository;

    @GetMapping
    public List<Career> getCareers() {
        return careerRepository.getCareers();
    }
}
