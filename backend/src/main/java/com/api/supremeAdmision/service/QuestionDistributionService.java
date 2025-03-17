package com.api.supremeAdmision.service;

import com.api.supremeAdmision.model.*;
import com.api.supremeAdmision.repository.admisionprocess.AdmisionProcessRepository;
import com.api.supremeAdmision.repository.exam.ExamRepository;
import com.api.supremeAdmision.repository.question.QuestionRepository;
import com.api.supremeAdmision.repository.questionexam.QuestionExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Service
public class QuestionDistributionService {

    private final AdmisionProcessRepository admisionProcessRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final QuestionExamRepository questionExamRepository;

    @Autowired
    public QuestionDistributionService(AdmisionProcessRepository admisionProcessRepository,
                                      ExamRepository examRepository,
                                      QuestionRepository questionRepository,
                                      QuestionExamRepository questionExamRepository) {
        this.admisionProcessRepository = admisionProcessRepository;
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.questionExamRepository = questionExamRepository;
    }

    /**
     * Distributes questions to exams for the current active admission process
     * @return A message indicating success or failure
     */
    @Transactional
    public String distributeQuestions() {
        try {
            // Get the current active admission process
            System.out.println("DEBUG: Buscando proceso de admisión activo...");
            AdmisionProcess activeProcess = admisionProcessRepository.findActiveProcess();
            if (activeProcess == null) {
                System.out.println("ERROR: No se encontró un proceso de admisión activo");
                return "ERROR: No active admission process found";
            }
            System.out.println("DEBUG: Proceso de admisión activo encontrado con ID: " + activeProcess.getId());

            int admisionProcessId = activeProcess.getId();

            // Clear any existing question assignments for the current process's exams
            System.out.println("DEBUG: Eliminando asignaciones de preguntas existentes...");
            int deletedCount = questionExamRepository.deleteByAdmisionProcessId(admisionProcessId);
            System.out.println("DEBUG: Se eliminaron " + deletedCount + " asignaciones de preguntas");

            // Get all exams directly from the database
            System.out.println("DEBUG: Obteniendo todos los exámenes de la base de datos...");
            List<Exam> allExams = examRepository.findAll();
            if (allExams.isEmpty()) {
                System.out.println("ERROR: No se encontraron exámenes en la base de datos");
                return "ERROR: No exams found in the database";
            }
            System.out.println("DEBUG: Se encontraron " + allExams.size() + " exámenes en total");
            
            // Print all exams for debugging
            System.out.println("DEBUG: Lista de todos los exámenes:");
            for (Exam exam : allExams) {
                System.out.println("  Exam ID: " + exam.getId() + ", Type: " + exam.getType() + ", Area: " + exam.getAreaId() + ", Process ID: " + exam.getAdmisionProcessId());
            }
            
            // Filter exams for the current admission process
            System.out.println("DEBUG: Filtrando exámenes para el proceso de admisión " + admisionProcessId);
            List<Exam> processExams = new ArrayList<>();
            for (Exam exam : allExams) {
                if (exam.getAdmisionProcessId() == admisionProcessId) {
                    processExams.add(exam);
                    System.out.println("DEBUG: Incluyendo examen ID: " + exam.getId() + ", Type: " + exam.getType() + ", Area: " + exam.getAreaId());
                }
            }
            
            if (processExams.isEmpty()) {
                System.out.println("ERROR: No se encontraron exámenes para el proceso de admisión activo");
                return "ERROR: No exams found for the active admission process";
            }
            
            System.out.println("DEBUG: Se encontraron " + processExams.size() + " exámenes para el proceso de admisión " + admisionProcessId);
            
            // Get all available questions
            System.out.println("DEBUG: Obteniendo todas las preguntas disponibles...");
            List<Question> allQuestions = questionRepository.findAll();
            if (allQuestions.isEmpty()) {
                System.out.println("ERROR: No se encontraron preguntas en la base de datos");
                return "ERROR: No questions found in the database";
            }
            
            System.out.println("DEBUG: Se encontraron " + allQuestions.size() + " preguntas en la base de datos");
            
            // Shuffle all questions to get random selection
            System.out.println("DEBUG: Mezclando preguntas aleatoriamente...");
            Collections.shuffle(allQuestions, new Random(System.currentTimeMillis()));
            
            // Calculate how many questions each exam should get
            int questionsPerExam = Math.min(100, allQuestions.size());
            System.out.println("DEBUG: Se asignarán " + questionsPerExam + " preguntas por examen");
            
            // If we don't have enough questions for all exams, we'll need to reuse some
            boolean needToReuse = (questionsPerExam * processExams.size()) > allQuestions.size();
            System.out.println("DEBUG: ¿Es necesario reutilizar preguntas? " + needToReuse);
            
            // Distribute questions for each exam
            for (int i = 0; i < processExams.size(); i++) {
                Exam exam = processExams.get(i);
                System.out.println("\nDEBUG: Procesando examen ID: " + exam.getId() + " (" + (i+1) + " de " + processExams.size() + ")");
                
                // Create a new list for selected questions
                List<Question> selectedQuestions = new ArrayList<>();
                
                if (needToReuse) {
                    // If we need to reuse questions, reshuffle the full list for each exam
                    System.out.println("DEBUG: Mezclando preguntas para este examen...");
                    Collections.shuffle(allQuestions, new Random(System.currentTimeMillis() + i));
                    
                    // Create a new list with the selected questions
                    for (int j = 0; j < questionsPerExam; j++) {
                        selectedQuestions.add(allQuestions.get(j));
                    }
                } else {
                    // If we have enough questions, take a unique subset for each exam
                    int startIndex = (i * questionsPerExam) % allQuestions.size();
                    System.out.println("DEBUG: Seleccionando preguntas desde el índice " + startIndex);
                    
                    for (int j = 0; j < questionsPerExam; j++) {
                        int index = (startIndex + j) % allQuestions.size();
                        selectedQuestions.add(allQuestions.get(index));
                    }
                }
                
                System.out.println("DEBUG: Seleccionadas " + selectedQuestions.size() + " preguntas para el examen ID: " + exam.getId());
                
                // Insert questions into the exam with sequential order numbers
                System.out.println("DEBUG: Insertando preguntas en la base de datos con números de orden secuenciales...");
                int successCount = 0;
                for (int j = 0; j < selectedQuestions.size(); j++) {
                    try {
                        Question question = selectedQuestions.get(j);
                        int orderNumber = j + 1;
                        
                        // Log every 10th question for brevity
                        if (j % 10 == 0) {
                            System.out.println("DEBUG: Insertando pregunta ID: " + question.getId() + " con orden: " + orderNumber);
                        }
                        
                        QuestionExam questionExam = new QuestionExam(question.getId(), exam.getId(), orderNumber);
                        int result = questionExamRepository.save(questionExam);
                        if (result > 0) {
                            successCount++;
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR al insertar pregunta: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                
                System.out.println("DEBUG: Se insertaron correctamente " + successCount + " de " + selectedQuestions.size() + " preguntas para el examen ID: " + exam.getId());
            }

            System.out.println("\nDEBUG: Distribución de preguntas completada con éxito");
            return "SUCCESS: Questions distributed successfully";
        } catch (Exception e) {
            System.out.println("ERROR CRÍTICO en la distribución de preguntas: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
}
