package seedu.address.logic.commands.policy;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class DeletePolicyCommand extends EditCommand {

    public static final String COMMAND_WORD = "deletePolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the policy identified by the client index number used in the displayed client list and "
            + "policy index number used in the displayed client card.\n"
            + "Parameters: INDEX pi/POLICY_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_POLICY_SUCCESS = "Deleted Policy: %s from %s's policy list";

    public DeletePolicyCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor, true);
    }
}
