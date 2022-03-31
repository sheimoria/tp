package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.DateTime;
import seedu.address.model.meeting.Meeting;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class CloseMeetingCommand extends Command {

    public static final String COMMAND_WORD = "closeMeeting";

    public static final String MESSAGE_USAGE = "Parameters:\n• INDEX (must be a positive integer)\n"
            + "Example:\n• " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLOSE_MEETING_SUCCESS = "Closed Meeting: %1$s";

    private final Index targetIndex;

    public CloseMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Meeting meetingToClose = lastShownList.get(targetIndex.getZeroBased());

        Client client = meetingToClose.getClient();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTime endDateTime = new DateTime(meetingToClose.getEndDateTime().format(dtf));
        model.setClient(client, client.updateLastContacted(endDateTime));
        return new CommandResult(String.format(MESSAGE_CLOSE_MEETING_SUCCESS, meetingToClose));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CloseMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((CloseMeetingCommand) other).targetIndex)); // state check
    }
}
