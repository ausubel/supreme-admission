package com.api.supremeAdmision.model;

public class QuestionExam {
    private int questionId;
    private int examId;
    private int orderNumber;
    private Question question;
    
    public QuestionExam() {}
    
    public QuestionExam(int questionId, int examId, int orderNumber) {
        this.questionId = questionId;
        this.examId = examId;
        this.orderNumber = orderNumber;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
