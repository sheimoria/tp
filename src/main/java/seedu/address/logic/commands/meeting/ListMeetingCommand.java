package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Lists all clients in the address book to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meetings";
    public static final String MESSAGE_SUCCESS = "Listed all meetings";
    public static final String MESSAGE_SUCCESS_WITH_CLIENT = "Listed all meetings with:\n%s";
    private final Index index;

    /**
     * Command to view all meetings.
     */
    public ListMeetingCommand() {
        this.index = null;
    }

    /**
     * Command to view all meetings with the client specified by the index.
     * @param index of the client to view meetings with.
     */
    public ListMeetingCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (index == null) {
            model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
            return new CommandResult(MESSAGE_SUCCESS,
                    false,
                    false,
                    false,
                    true,
                    false,
                    null);
        } else {
            Client client = model.getFilteredClientList().get(index.getZeroBased());
            model.updateFilteredMeetingList(meeting -> meeting.getClient().equals(client));
            return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_CLIENT, client.toString()),
                    false,
                    false,
                    false,
                    true,
                    false,
                    null
            );
        }
    }
}
