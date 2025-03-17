package com.api.supremeAdmision.model;

public class Subject {
    private int id;
    private String name;
    private int disciplineId;
    
    public Subject() {}
    
    public Subject(int id, String name, int disciplineId) {
        this.id = id;
        this.name = name;
        this.disciplineId = disciplineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }
}
