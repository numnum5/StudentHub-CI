package com.example.cab302project.model;

/**
 * Journal is for adding entries to journal entries
 */
public class Journal {
    private int id;          // Unique identifier for the journal entry
    private String entry;    // The text of the journal entry
    private String mood;     // The mood associated with the entry
    private String username;  // The username of the person making the entry

    /**
     *
     * Constructor of journal
     */
    public Journal(String entry, String mood, String username) {
        this.entry = entry;
        this.mood = mood;
        this.username = username;
    }

    /**
     *
     * Getters
     */
    public int getId() {
        return id;
    }

    public String getEntry() {
        return entry;
    }

    public String getMood() {
        return mood;
    }

    public String getUsername() {
        return username;
    }

    /**
     *
     * Setters
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}