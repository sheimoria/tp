package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class GetPreferenceCommand extends Command {

    public static final String COMMAND_WORD = "getPreference";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets a preference from a client"
            + "using a specific preference key.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_PREFERENCE_KEY + "PREFERENCE_KEY (one word with only alphabets)]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_PREFERENCE_KEY + "Drink";

    public static final String MESSAGE_SUCCESS = "Preference retrieved for %s: [%s: %s]";
    public static final String MESSAGE_FAILURE = "Unable to retrieve preference [%s] for %s as it does not exist.";

    private final Index index;
    private final String preferenceKey;

    /**
     * Creates a GetPreferenceCommand to get the specified preference
     */
    public GetPreferenceCommand(Index index, String preferenceKey) {
        requireNonNull(index);
        requireNonNull(preferenceKey);

        this.index = index;
        this.preferenceKey = preferenceKey;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person toGetPreference;
        try {
            toGetPreference = lastShownList.get(index.getZeroBased());
            String name = toGetPreference.getName().toString();
            String preferenceDetails = toGetPreference.getPreferenceMap().getPreference(preferenceKey);

            if (preferenceDetails == null) {
                return new CommandResult(String.format(MESSAGE_FAILURE, preferenceKey, name));
            } else {
                return new CommandResult(String.format(MESSAGE_SUCCESS, name, preferenceKey, preferenceDetails));
            }
        } catch (IndexOutOfBoundsException ie) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
