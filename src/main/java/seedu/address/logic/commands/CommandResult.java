package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Tutorial should be shown to the user. */
    private final boolean showTutorial;

    /** The application should exit. */
    private final boolean exit;

    /** Meetings should be shown. */
    private final boolean showMeetings;

    /** Persons should be shown. */
    private final boolean showPersons;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit,
                         boolean showMeetings, boolean showPersons) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTutorial = showTutorial;
        this.exit = exit;
        this.showMeetings = showMeetings;
        this.showPersons = showPersons;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and the showPersons field set to its default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit,
                         boolean showMeetings) {
        this(feedbackToUser, showHelp, showTutorial, exit, showMeetings, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and the showMeetings and showPersons field set to its default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit) {
        this(feedbackToUser, showHelp, showTutorial, exit, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowTutorial() {
        return showTutorial;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowMeetings() {
        return showMeetings;
    }

    public boolean isShowPersons() {
        return showPersons;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && showTutorial == otherCommandResult.showTutorial
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
