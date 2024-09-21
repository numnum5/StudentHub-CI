package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class simulates a Data Access Object (DAO) for managing journal entries.
 */
public class MockJournalDAO implements IJournalDAO {
    private final ArrayList<Journal> journals = new ArrayList<>(); // List to store journal entries
    private int autoIncrementedId = 1; // Auto-incremented ID for new journal entries

    /**
     * Adds a new journal entry to the list and assigns it a unique ID.
     *
     * @param entry The journal entry to be added.
     * @param mood The mood associated with the journal entry.
     * @param userName The username of the person making the entry.
     */
    @Override
    public void addEntry(String entry, String mood, String userName) {
        autoIncrementedId++;
        Journal newJournal = new Journal(entry, mood, userName);
        newJournal.setId(autoIncrementedId);
        journals.add(newJournal);
    }

    /**
     * Updates an existing journal entry in the list.
     *
     * @param updatedJournal The Journal object containing updated information.
     */
    @Override
    public void updateEntry(Journal updatedJournal) {
        for (int i = 0; i < journals.size(); i++) {
            if (journals.get(i).getId() == updatedJournal.getId()) {
                journals.set(i, updatedJournal);
                break;
            }
        }
    }

    /**
     * Deletes a journal entry from the list.
     *
     * @param journal The Journal object to be deleted.
     */
    @Override
    public void deleteEntry(Journal journal) {
        journals.remove(journal);
    }

    /**
     * Retrieves a journal entry by its ID.
     *
     * @param id The unique identifier of the journal entry to be retrieved.
     * @return The Journal object if found, or null if not.
     */
    @Override
    public Journal getEntry(int id) {
        for (Journal journal : journals) {
            if (journal.getId() == id) {
                return journal;
            }
        }
        return null;
    }

    /**
     * Retrieves all journal entries for a specific username.
     *
     * @param userName The username for which to retrieve journal entries.
     * @return A list of Journal objects.
     */
    @Override
    public List<Journal> getAllEntries(String userName) {
        List<Journal> userJournals = new ArrayList<>();
        for (Journal journal : journals) {
            if (journal.getUsername().equals(userName)) {
                userJournals.add(journal);
            }
        }
        return userJournals;
    }

    /**
     * Searches for journal entries that match the given keyword in their entry text or mood.
     *
     * @param keyword The keyword to search for in journal entries.
     * @return A list of matching Journal objects.
     */
    @Override
    public List<Journal> searchEntries(String keyword) {
        List<Journal> matchingJournals = new ArrayList<>();

        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return matchingJournals;
        }

        for (Journal journal : journals) {
            if (journal.getEntry().toLowerCase().contains(keyword.toLowerCase()) ||
                    journal.getMood().toLowerCase().contains(keyword.toLowerCase())) {
                matchingJournals.add(journal);
            }
        }

        return matchingJournals;
    }
}
