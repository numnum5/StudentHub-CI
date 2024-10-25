package com.example.cab302project.model;
<<<<<<< HEAD

/**
 * Journal is for adding entries to journal entries
=======
/**
 * Class representing a journal entry which models the structure of a journal entry
>>>>>>> 66b3a1046336f1e53b3e5c2e2e6d3199ecb68bf3
 */
public class Journal {
    private int id;          // Unique identifier for the journal entry
    private String entry;    // The text of the journal entry
    private String mood;     // The mood associated with the entry
    private String username;  // The username of the person making the entry

    /**
<<<<<<< HEAD
     *
     * Constructor of journal
=======
     * Constructor to initialize a journal entry.
     *
     * @param entry   The text of the journal entry.
     * @param mood    The mood associated with the entry.
     * @param username The username of the person making the entry.
>>>>>>> 66b3a1046336f1e53b3e5c2e2e6d3199ecb68bf3
     */
    public Journal(String entry, String mood, String username) {
        this.entry = entry;
        this.mood = mood;
        this.username = username;
    }

    /**
<<<<<<< HEAD
     *
     * Getters
=======
     * Gets the unique identifier for the journal entry.
     *
     * @return The unique identifier for the journal entry.
>>>>>>> 66b3a1046336f1e53b3e5c2e2e6d3199ecb68bf3
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the text of the journal entry.
     *
     * @return The text of the journal entry.
     */
    public String getEntry() {
        return entry;
    }

    /**
     * Gets the mood associated with the journal entry.
     *
     * @return The mood associated with the entry.
     */
    public String getMood() {
        return mood;
    }

    /**
     * Gets the username of the person who made the journal entry.
     *
     * @return The username of the person making the entry.
     */
    public String getUsername() {
        return username;
    }

    /**
<<<<<<< HEAD
     *
     * Setters
=======
     * Sets the unique identifier for the journal entry.
     *
     * @param id The unique identifier for the journal entry.
>>>>>>> 66b3a1046336f1e53b3e5c2e2e6d3199ecb68bf3
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the text of the journal entry.
     *
     * @param entry The text of the journal entry.
     */
    public void setEntry(String entry) {
        this.entry = entry;
    }

    /**
     * Sets the mood associated with the journal entry.
     *
     * @param mood The mood associated with the entry.
     */
    public void setMood(String mood) {
        this.mood = mood;
    }

    /**
     * Sets the username of the person making the journal entry.
     *
     * @param username The username of the person making the entry.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
