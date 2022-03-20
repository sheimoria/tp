package seedu.address.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * View all policies purchased by a client identified using it's displayed index from the address book.
 */
public class ViewPolicyCommand extends Command {

    public static final String COMMAND_WORD = "viewPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all policies purchased by the client identified by the index number "
            + "used in the displayed client list\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_POLICY_SUCCESS = "Policies purchased by %s: %s";

    private final Index targetIndex;

    public ViewPolicyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToViewPoliciesOf = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(
                String.format(
                        MESSAGE_VIEW_POLICY_SUCCESS,
                        clientToViewPoliciesOf.getName(),
                        clientToViewPoliciesOf.displayPolicyList()
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPolicyCommand // instanceof handles nulls
                && targetIndex.equals(((ViewPolicyCommand) other).targetIndex)); // state check
    }
}
