package com.example.cab302project.model;

/**
 * The Subject class represents a subject in the academic system.
 * It contains details such as unit code, name, description, semester, and a unique ID.
 */
public class Subject {
    private int id;                // Unique identifier for the subject
    private String unitCode;       // Code representing the subject
    private String name;           // Name of the subject
    private String description;    // Description of the subject
    private int semester;          // Semester in which the subject is offered

    /**
     * Constructor to initialize a Subject object with provided values.
     *
     * @param semester    The semester in which the subject is offered.
     * @param unitCode    The code representing the subject.
     * @param name        The name of the subject.
     * @param description A brief description of the subject.
     */
    public Subject(int semester, String unitCode, String name, String description) {
        this.semester = semester;
        this.unitCode = unitCode;
        this.name = name;
        this.description = description;
    }

    // Getter and setter for semester
    public int getSemester() {
        return semester; // Returns the semester in which the subject is offered
    }

    public void setSemester(int semester) {
        this.semester = semester; // Sets the semester for the subject
    }

    // Getters and Setters for other fields
    public int getId() {
        return id; // Returns the unique ID of the subject
    }

    public void setId(int id) {
        this.id = id; // Sets the unique ID of the subject
    }

    public String getUnitCode() {
        return unitCode; // Returns the unit code of the subject
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode; // Sets the unit code for the subject
    }

    public String getName() {
        return name; // Returns the name of the subject
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the subject
    }

    public String getDescription() {
        return description; // Returns the description of the subject
    }

    public void setDescription(String description) {
        this.description = description; // Sets the description for the subject
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", unitCode='" + unitCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}'; // Provides a string representation of the Subject object
    }
}
