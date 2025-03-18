package com.api.supremeAdmision.service;

import com.api.supremeAdmision.model.Exam;
import com.api.supremeAdmision.repository.exam.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private static final Logger logger = LoggerFactory.getLogger(ExamService.class);

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    /**
     * Retrieves all exams from the database
     * @return List of all exams
     */
    public List<Exam> getAllExams() {
        logger.info("Retrieving all exams");
        try {
            return examRepository.getAllExams();
        } catch (Exception e) {
            logger.error("Error retrieving exams: {}", e.getMessage());
            throw new RuntimeException("Error retrieving exams: " + e.getMessage(), e);
        }
    }
}
