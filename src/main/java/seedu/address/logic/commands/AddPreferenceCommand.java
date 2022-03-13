package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class AddPreferenceCommand extends Command {

    public static final String COMMAND_WORD = "addPreference";
    public static final String MESSAGE_SUCCESS = "New preference added: %s: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a preference to"
            + "the person specified by the index number used in the displayed "
            + "person list.\nParameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_PREFERENCE_KEY + "PREFERENCE_KEY (one word with only alphabets)]"
            + "[" + PREFIX_PREFERENCE_DETAIL + "PREFERENCE_DETAILS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PREFERENCE_KEY + "Drink "
            + PREFIX_PREFERENCE_DETAIL + "Bubble Tea";

    private final Index index;
    private final String preferenceKey;
    private final String preferenceDetails;

    /**
     *Creates an AddPreferenceCommand to add the specified preference
     */
    public AddPreferenceCommand(Index index, String preferenceKey, String preferenceDetails) {
        requireNonNull(index);
        requireNonNull(preferenceKey);
        requireNonNull(preferenceDetails);

        this.index = index;
        this.preferenceKey = preferenceKey;
        this.preferenceDetails = preferenceDetails;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddPreferences = lastShownList.get(index.getZeroBased());

        personToAddPreferences.getPreferenceMap().addPreference(preferenceKey, preferenceDetails);

        return new CommandResult((String.format(MESSAGE_SUCCESS, preferenceKey, preferenceDetails)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddPreferenceCommand)) {
            return false;
        }

        AddPreferenceCommand otherApc = (AddPreferenceCommand) other;
        return index.equals(otherApc.index)
                && preferenceKey.equals(otherApc.preferenceKey)
                && preferenceDetails.equals(otherApc.preferenceDetails);
    }
}
