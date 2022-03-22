package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;


/**
 * Adds a meeting to the address book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addMeeting";
    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the client specified "
            + "by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "" + PREFIX_START_DATETIME + "START_DATETIME "
            + "" + PREFIX_END_DATETIME + "END_DATETIME "
            + "[" + PREFIX_LABEL + "LABEL]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_START_DATETIME + "2022-01-01,11:00 "
            + PREFIX_END_DATETIME + "2022-01-01:13:00 "
            + PREFIX_LABEL + "Lunch";
    public static final String MESSAGE_OVERLAPPING_MEETING = "There is an existing"
           + " meeting overlapping with this meeting.";
    public static final String MESSAGE_INVALID_DATETIME = "The end time cannot be before the start time.";

    private final Index index;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String label;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Index index, LocalDateTime startDateTime, LocalDateTime endDateTime, String label) {
        requireNonNull(index);
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        requireNonNull(label);

        this.index = index;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.label = label;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToMeet = lastShownList.get(index.getZeroBased());

        Meeting newMeeting = new Meeting(startDateTime, endDateTime, clientToMeet, label);

        if (model.isOverlapping(newMeeting)) {
            throw new CommandException(MESSAGE_OVERLAPPING_MEETING);
        }

        model.addMeeting(newMeeting);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newMeeting));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        AddMeetingCommand otherAmc = (AddMeetingCommand) other;
        return index.equals(otherAmc.index)
                && startDateTime.equals(otherAmc.startDateTime)
                && endDateTime.equals(otherAmc.endDateTime) && label.equals(otherAmc.label);
    }
}
