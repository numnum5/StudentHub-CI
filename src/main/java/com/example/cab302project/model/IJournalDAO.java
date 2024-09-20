package com.example.cab302project.model;

import java.util.List;

/**
 * The IJournalDAO interface defines the methods for managing journal entries.
 */
public interface IJournalDAO {

    /**
     * Adds a new journal entry.
     *
     * @param entry The text of the journal entry.
     * @param mood The mood associated with the journal entry.
     * @param userName The username of the person making the entry.
     */
    void addEntry(String entry, String mood, String userName);

    /**
     * Updates an existing journal entry.
     *
     * @param updatedJournal The Journal object containing updated information.
     */
    void updateEntry(Journal updatedJournal);

    /**
     * Deletes a journal entry.
     *
     * @param journal The Journal object to be deleted.
     */
    void deleteEntry(Journal journal);

    /**
     * Retrieves a journal entry by its ID.
     *
     * @param id The unique identifier of the journal entry.
     * @return The Journal object if found, or null if not.
     */
    Journal getEntry(int id);

    /**
     * Retrieves all journal entries for a specific username.
     *
     * @param userName The username for which to retrieve journal entries.
     * @return A list of Journal objects.
     */
    List<Journal> getAllEntries(String userName);

    /**
     * Searches for journal entries that match the given keyword.
     *
     * @param keyword The keyword to search for in journal entries.
     * @return A list of matching Journal objects.
     */
    List<Journal> searchEntries(String keyword);
}
