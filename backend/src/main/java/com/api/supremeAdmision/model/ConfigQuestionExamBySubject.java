package com.api.supremeAdmision.model;

public class ConfigQuestionExamBySubject {
    private int id;
    private int quantity;
    private int subjectId;
    private Subject subject;
    
    public ConfigQuestionExamBySubject() {}
    
    public ConfigQuestionExamBySubject(int id, int quantity, int subjectId) {
        this.id = id;
        this.quantity = quantity;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
