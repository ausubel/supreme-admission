package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.Province;
import com.api.supremeAdmision.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping
    public List<Province> getProvinces() {
        return provinceRepository.getProvinces();
    }
}
