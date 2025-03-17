package com.api.supremeAdmision.model;

import java.util.List;

public class Exam {
    private int id;
    private String type;
    private String documentId;
    private String areaId;
    private int admisionProcessId;
    private List<QuestionExam> questions;
    
    public Exam() {}
    
    public Exam(int id, String type, String documentId, String areaId, int admisionProcessId) {
        this.id = id;
        this.type = type;
        this.documentId = documentId;
        this.areaId = areaId;
        this.admisionProcessId = admisionProcessId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getAdmisionProcessId() {
        return admisionProcessId;
    }

    public void setAdmisionProcessId(int admisionProcessId) {
        this.admisionProcessId = admisionProcessId;
    }

    public List<QuestionExam> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionExam> questions) {
        this.questions = questions;
    }
}
