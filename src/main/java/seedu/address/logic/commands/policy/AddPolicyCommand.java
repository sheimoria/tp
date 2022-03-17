package seedu.address.logic.commands.policy;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;

public class AddPolicyCommand extends EditCommand {

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the client identified "
            + "by the index number used in the displayed client list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_POLICY_MANAGER + "POLICY_MANAGER] "
            + "[" + PREFIX_PREMIUM + "PREMIUM]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Investment Plan "
            + PREFIX_COMPANY + "AAM Advisory "
            + PREFIX_POLICY_MANAGER + "Me "
            + PREFIX_PREMIUM + "15";

    public AddPolicyCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor);
    }
}
