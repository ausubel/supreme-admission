package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamGrader {
    // Constants for the exam structure
    private static final int MATH_QUESTIONS = 20;
    private static final int SCIENCE_QUESTIONS = 20;
    private static final int HUMANITIES_QUESTIONS = 20;
    private static final int TOTAL_QUESTIONS = 100;

    // Maps to store the weights for different areas
    private static final Map<Character, int[]> AREA_WEIGHTS = new HashMap<>();

    // Map to store the exam type to area mapping
    private static final Map<Character, Character> EXAM_TYPE_TO_AREA = new HashMap<>();

    // Map to store the answer keys for each exam type
    private static final Map<Character, String> ANSWER_KEYS = new HashMap<>();

    // Initialize the weights and mappings
    static {
        // Area A weights: Math, Science, Humanities, Academic Aptitude
        AREA_WEIGHTS.put('A', new int[]{2, 6, 2, 4});

        // Area B weights
        AREA_WEIGHTS.put('B', new int[]{2, 2, 6, 4});

        // Area C weights
        AREA_WEIGHTS.put('C', new int[]{6, 2, 2, 4});

        // Exam type to area mapping
        EXAM_TYPE_TO_AREA.put('H', 'A');
        EXAM_TYPE_TO_AREA.put('I', 'A');
        EXAM_TYPE_TO_AREA.put('J', 'A');
        EXAM_TYPE_TO_AREA.put('K', 'B');
        EXAM_TYPE_TO_AREA.put('L', 'B');
        EXAM_TYPE_TO_AREA.put('M', 'B');
        EXAM_TYPE_TO_AREA.put('N', 'C');
        EXAM_TYPE_TO_AREA.put('O', 'C');
        EXAM_TYPE_TO_AREA.put('P', 'C');
    }

    public static void main(String[] args) {
        String keysFilePath = "c:\\Users\\ausub\\dev\\supreme_admision\\supreme_calificador\\data\\keys.txt";
        String responsesFilePath = "c:\\Users\\ausub\\dev\\supreme_admision\\supreme_calificador\\data\\responses.txt";
        String outputFilePath = "c:\\Users\\ausub\\dev\\supreme_admision\\supreme_calificador\\data\\results.csv";

        try {
            // Load answer keys
            loadAnswerKeys(keysFilePath);

            // Process student responses and generate results
            processResponses(responsesFilePath, outputFilePath);

            System.out.println("Grading completed successfully. Results saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads answer keys from the specified file
     */
    private static void loadAnswerKeys(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            if (line.length() > 0) {
                char examType = line.charAt(0);
                String answers = line.substring(1);
                ANSWER_KEYS.put(examType, answers);
            }
        }
    }
    /**
     * Processes student responses and generates results
     */
    private static void processResponses(String responsesFilePath, String outputFilePath) throws IOException {
        List<String> responses = Files.readAllLines(Paths.get(responsesFilePath));
        List<StudentResult> results = new ArrayList<>();

        for (String response : responses) {
            if (response.length() >= 107) { // 6 (ID) + 1 (exam type) + 100 (answers)
                String studentId = response.substring(0, 6);
                char examType = response.charAt(6);
                String studentAnswers = response.substring(7, 107);

                // Calculate the score
                StudentResult result = calculateScore(studentId, examType, studentAnswers);
                results.add(result);
            }
        }

        // Write results to CSV file
        writeResultsToCsv(results, outputFilePath);
    }

    /**
     * Calculates the score for a student
     */
    private static StudentResult calculateScore(String studentId, char examType, String studentAnswers) {
        // Get the answer key for this exam type
        String answerKey = ANSWER_KEYS.get(examType);

        // Get the area for this exam type
        char area = EXAM_TYPE_TO_AREA.get(examType);

        // Get the weights for this area
        int[] weights = AREA_WEIGHTS.get(area);

        // Count correct and incorrect answers for each section
        int mathCorrect = 0, mathIncorrect = 0;
        int scienceCorrect = 0, scienceIncorrect = 0;
        int humanitiesCorrect = 0, humanitiesIncorrect = 0;
        int aptitudeCorrect = 0, aptitudeIncorrect = 0;

        // Check math section (questions 0-19)
        for (int i = 0; i < MATH_QUESTIONS; i++) {
            if (studentAnswers.charAt(i) == answerKey.charAt(i) || answerKey.charAt(i) == ' ') {
                mathCorrect++;
            } else { // Count only if answered (not blank)
                mathIncorrect++;
            }
        }

        // Check science section (questions 20-39)
        for (int i = MATH_QUESTIONS; i < MATH_QUESTIONS + SCIENCE_QUESTIONS; i++) {
            if (studentAnswers.charAt(i) == answerKey.charAt(i) || answerKey.charAt(i) == ' ') {
                scienceCorrect++;
            } else {
                scienceIncorrect++;
            }
        }

        // Check humanities section (questions 40-59)
        for (int i = MATH_QUESTIONS + SCIENCE_QUESTIONS; i < MATH_QUESTIONS + SCIENCE_QUESTIONS + HUMANITIES_QUESTIONS; i++) {
            if (studentAnswers.charAt(i) == answerKey.charAt(i) || answerKey.charAt(i) == ' ') {
                humanitiesCorrect++;
            } else {
                humanitiesIncorrect++;
            }
        }

        // Check academic aptitude section (questions 60-99)
        for (int i = MATH_QUESTIONS + SCIENCE_QUESTIONS + HUMANITIES_QUESTIONS; i < TOTAL_QUESTIONS; i++) {
            if (studentAnswers.charAt(i) == answerKey.charAt(i) || answerKey.charAt(i) == ' ') {
                aptitudeCorrect++;
            } else {
                aptitudeIncorrect++;
            }
        }

        // Calculate scores for each section using the formula: Score = Weight * (Correct - 0.25 * Incorrect)
        double mathScore = weights[0] * (mathCorrect - 0.25 * mathIncorrect);
        double scienceScore = weights[1] * (scienceCorrect - 0.25 * scienceIncorrect);
        double humanitiesScore = weights[2] * (humanitiesCorrect - 0.25 * humanitiesIncorrect);
        double aptitudeScore = weights[3] * (aptitudeCorrect - 0.25 * aptitudeIncorrect);

        // Calculate total score
        double totalScore = mathScore + scienceScore + humanitiesScore + aptitudeScore;

        // Calculate final grade using the formula: Grade = 20 * (Total Score + 45) / (360 + 45)
        double finalGrade = 20.0 * (totalScore + 45) / (360 + 45);

        return new StudentResult(studentId, examType, area, mathCorrect, mathIncorrect, scienceCorrect,
                scienceIncorrect, humanitiesCorrect, humanitiesIncorrect, aptitudeCorrect, aptitudeIncorrect,
                mathScore, scienceScore, humanitiesScore, aptitudeScore, totalScore, finalGrade);
    }

    /**
     * Writes results to a CSV file
     */
    private static void writeResultsToCsv(List<StudentResult> results, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Write header
            writer.write("StudentId,ExamType,Area,MathCorrect,MathIncorrect,ScienceCorrect,ScienceIncorrect," +
                    "HumanitiesCorrect,HumanitiesIncorrect,AptitudeCorrect,AptitudeIncorrect," +
                    "MathScore,ScienceScore,HumanitiesScore,AptitudeScore,TotalScore,FinalGrade");
            writer.newLine();

            // Write data rows
            for (StudentResult result : results) {
                writer.write(String.format("%s,%c,%c,%d,%d,%d,%d,%d,%d,%d,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f",
                        result.getStudentId(), result.getExamType(), result.getArea(),
                        result.getMathCorrect(), result.getMathIncorrect(),
                        result.getScienceCorrect(), result.getScienceIncorrect(),
                        result.getHumanitiesCorrect(), result.getHumanitiesIncorrect(),
                        result.getAptitudeCorrect(), result.getAptitudeIncorrect(),
                        result.getMathScore(), result.getScienceScore(),
                        result.getHumanitiesScore(), result.getAptitudeScore(),
                        result.getTotalScore(), result.getFinalGrade()));
                writer.newLine();
            }
        }
    }
}
