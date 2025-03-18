package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.service.QuestionDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/questions")
public class QuestionDistributionController {

    private final QuestionDistributionService questionDistributionService;

    @Autowired
    public QuestionDistributionController(QuestionDistributionService questionDistributionService) {
        this.questionDistributionService = questionDistributionService;
    }

    /**
     * Endpoint to trigger the distribution of questions to exams
     * @return A response indicating success or failure
     */
    @CrossOrigin
    @PostMapping("/distribute")
    public ResponseEntity<String> distributeQuestions() {
        String result = questionDistributionService.distributeQuestions();
        
        if (result.startsWith("ERROR")) {
            return ResponseEntity.badRequest().body(result);
        }
        
        return ResponseEntity.ok(result);
    }
}
