package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_OPERATOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_VALUE;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Filter clients in the address book.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filterClients";

    public static final String FILTER_ATTRIBUTES = "birthday, age";
    public static final String FILTER_OPERATORS = "equal, greater, lesser, lesserorequal, greaterorequal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters clients in the address book.\n"
            + "Parameters: "
            + "ATTRIBUTE " + PREFIX_FILTER_OPERATOR + "FILTER_OPERATOR "
            + PREFIX_FILTER_VALUE + "FILTER_VALUE\n"
            + "Example: " + COMMAND_WORD + "age "
            + PREFIX_FILTER_OPERATOR + "equal "
            + PREFIX_FILTER_VALUE + "50\n"
            + "Supported attributes: " + FILTER_ATTRIBUTES + "\n"
            + "Supported inequalities: " + FILTER_OPERATORS;

    public static final String MESSAGE_SUCCESS = "Successfully filtered: %s %s %s";
    public static final String MESSAGE_INVALID_FILTER_ATTRIBUTE = "The filter attribute %s is invalid. "
            + "Supported attributes: " + FILTER_ATTRIBUTES;
    public static final String MESSAGE_INVALID_FILTER_OPERATOR = "The filter operator %s is invalid. "
            + "Supported operators: " + FILTER_OPERATORS;
    public static final String MESSAGE_INVALID_FILTER_VALUE = "The filter value %s is invalid, expected an %s "
            + "for the attribute %s.";

    private final String attribute;
    private final String operator;
    private final String value;

    /**
     * Creates a FilterCommand to filter the {@code Client}s by a specified {@code attribute}.
     * @param attribute {@code Client} attribute to filter by
     * @param operator whether the filter should be equal, greater or less than
     * @param value the value to be filtered on
     */
    public FilterCommand(String attribute, String operator, String value) {
        requireNonNull(attribute);
        requireNonNull(operator);
        requireNonNull(value);
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Predicate<Client> predicateLess;
        Predicate<Client> predicateEqual;
        Predicate<Client> predicateGreater;
        switch (attribute) {
        case "age":
            int compareValue;
            try {
                compareValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new CommandException(String.format(MESSAGE_INVALID_FILTER_VALUE, value, "integer", attribute));
            }
            predicateLess = (client) -> client.getAge() < compareValue;
            predicateEqual = (client) -> client.getAge() == compareValue;
            predicateGreater = (client) -> client.getAge() > compareValue;
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER_ATTRIBUTE, attribute));
        }

        switch (operator) {
        case "greater":
            model.updateFilteredClientList(predicateGreater);
            break;
        case "equal":
            model.updateFilteredClientList(predicateEqual);
            break;
        case "lesser":
            model.updateFilteredClientList(predicateLess);
            break;
        case "lessorequal":
            model.updateFilteredClientList(predicateLess.or(predicateEqual));
            break;
        case "greaterorequal":
            model.updateFilteredClientList(predicateGreater.or(predicateEqual));
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER_OPERATOR, operator));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, attribute, operator, value));
    }
}
