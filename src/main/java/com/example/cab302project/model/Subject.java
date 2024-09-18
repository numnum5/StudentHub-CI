package com.example.cab302project.model;

public class Subject {
    private int id;
    private String unitCode;
    private String name;
    private String description;
    private int semester;
    // Constructor
    public Subject(int semester, String unitCode, String name, String description) {
        this.semester = semester;
        this.unitCode = unitCode;
        this.name = name;
        this.description = description;
    }

    public int getSemester() {return semester;};
    public void setSemester(int semester) { this.semester = semester;}
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", unitCode='" + unitCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
