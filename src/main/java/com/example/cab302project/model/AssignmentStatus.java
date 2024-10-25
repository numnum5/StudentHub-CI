package com.example.cab302project.model;

/**
 * Enum class for storing assignment statuses.
 * This enumeration defines the possible statuses that an assignment can have.
 */
public enum AssignmentStatus {
    /** Indicates that the assignment is urgent and needs immediate attention. */
    URGENT,

    /** Indicates that work on the assignment is currently in progress. */
    IN_PROGRESS,

    /** Indicates that the assignment has been completed. */
    COMPLETED,

    /** Indicates that the assignment is pending and has not yet been started. */
    PENDING,

    /** Indicates that the assignment is overdue and has not been submitted by the due date. */
    OVERDUE
}