package com.api.supremeAdmision.model;

public class Career {
    private Integer id;
    private String name;
    private String facultyName;
    private String areaId;

    public Career() {
    }

    public Career(Integer id, String name, String facultyName, String areaId) {
        this.id = id;
        this.name = name;
        this.facultyName = facultyName;
        this.areaId = areaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
