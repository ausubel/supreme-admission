package org.example;

public class StudentResult {
    private final String studentId;
    private final char examType;
    private final char area;
    private final int mathCorrect;
    private final int mathIncorrect;
    private final int scienceCorrect;
    private final int scienceIncorrect;
    private final int humanitiesCorrect;
    private final int humanitiesIncorrect;
    private final int aptitudeCorrect;
    private final int aptitudeIncorrect;
    private final double mathScore;
    private final double scienceScore;
    private final double humanitiesScore;
    private final double aptitudeScore;
    private final double totalScore;
    private final double finalGrade;

    public StudentResult(String studentId, char examType, char area, int mathCorrect, int mathIncorrect,
                         int scienceCorrect, int scienceIncorrect, int humanitiesCorrect, int humanitiesIncorrect,
                         int aptitudeCorrect, int aptitudeIncorrect, double mathScore, double scienceScore,
                         double humanitiesScore, double aptitudeScore, double totalScore, double finalGrade) {
        this.studentId = studentId;
        this.examType = examType;
        this.area = area;
        this.mathCorrect = mathCorrect;
        this.mathIncorrect = mathIncorrect;
        this.scienceCorrect = scienceCorrect;
        this.scienceIncorrect = scienceIncorrect;
        this.humanitiesCorrect = humanitiesCorrect;
        this.humanitiesIncorrect = humanitiesIncorrect;
        this.aptitudeCorrect = aptitudeCorrect;
        this.aptitudeIncorrect = aptitudeIncorrect;
        this.mathScore = mathScore;
        this.scienceScore = scienceScore;
        this.humanitiesScore = humanitiesScore;
        this.aptitudeScore = aptitudeScore;
        this.totalScore = totalScore;
        this.finalGrade = finalGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public char getExamType() {
        return examType;
    }

    public char getArea() {
        return area;
    }

    public int getMathCorrect() {
        return mathCorrect;
    }

    public int getMathIncorrect() {
        return mathIncorrect;
    }

    public int getScienceCorrect() {
        return scienceCorrect;
    }

    public int getScienceIncorrect() {
        return scienceIncorrect;
    }

    public int getHumanitiesCorrect() {
        return humanitiesCorrect;
    }

    public int getHumanitiesIncorrect() {
        return humanitiesIncorrect;
    }

    public int getAptitudeCorrect() {
        return aptitudeCorrect;
    }

    public int getAptitudeIncorrect() {
        return aptitudeIncorrect;
    }

    public double getMathScore() {
        return mathScore;
    }

    public double getScienceScore() {
        return scienceScore;
    }

    public double getHumanitiesScore() {
        return humanitiesScore;
    }

    public double getAptitudeScore() {
        return aptitudeScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getFinalGrade() {
        return finalGrade;
    }
}