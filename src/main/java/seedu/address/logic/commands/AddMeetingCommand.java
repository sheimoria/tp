package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;



/**
 * Adds a meeting to the address book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addMeeting";
    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the person specified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_START_DATETIME + "START_DATETIME] "
            + "[" + PREFIX_END_DATETIME + "END_DATETIME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_START_DATETIME + "2022-01-01,11:00 "
            + PREFIX_END_DATETIME + "2022-01-01:13:00";
    public static final String MESSAGE_OVERLAPPING_MEETING = "There is an existing"
           + " meeting overlapping with this meeting.";
    public static final String MESSAGE_INVALID_DATETIME = "The end time cannot be before the start time.";

    private final Index index;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Index index, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        requireNonNull(index);
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);

        this.index = index;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());

        Meeting newMeeting = new Meeting(startDateTime, endDateTime, personToMeet);

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
                && endDateTime.equals(otherAmc.endDateTime);
    }
}
