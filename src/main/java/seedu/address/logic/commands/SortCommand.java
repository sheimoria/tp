package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

import java.util.Comparator;

/**
 * Sorts clients in the address book.
 *
 * birthday
 * premium
 * lastContacted
 * premium due date
 * num policies
 *
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sortClients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts clients in the address book.\n"
            + "Parameters: "
            + "[" + PREFIX_SORT_DIRECTION + "SORT_DIRECTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_DIRECTION + "desc";

    public static final String MESSAGE_SUCCESS = "Successfully sorted";
    public static final String MESSAGE_INVALID_ATTRIBUTE = "This attribute is invalid";
    public static final String MESSAGE_INVALID_SORT_DIRECTION = "Use 'asc' for ascending or 'desc' for descending";


    private final boolean isAscending;
    private final String attribute;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public SortCommand(String attribute, boolean isAscending) {
        requireNonNull(attribute);
        requireNonNull(isAscending);
        this.attribute = attribute;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Client> comparator;
        switch(attribute) {
        case "numPolicies":
            comparator = Comparator.comparingInt(a -> a.getPolicies().asUnmodifiableObservableList().size());
            break;
        default:
            throw new CommandException(MESSAGE_INVALID_ATTRIBUTE);
        }

        if (!isAscending) {
            comparator = comparator.reversed();
        }
        model.updateSortedClientList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute)
                && isAscending == ((SortCommand) other).isAscending);
    }
}
