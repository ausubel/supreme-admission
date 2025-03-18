package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.model.Exam;
import com.api.supremeAdmision.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    /**
     * Get all exams
     * @return List of all exams
     */
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        logger.info("Request to get all exams");
        try {
            List<Exam> exams = examService.getAllExams();
            return ResponseEntity.ok(exams);
        } catch (Exception e) {
            logger.error("Error getting all exams: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
