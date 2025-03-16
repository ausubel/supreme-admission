package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.District;
import com.api.supremeAdmision.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @GetMapping
    public List<District> getDistricts() {
        return districtRepository.getDistricts();
    }
}
