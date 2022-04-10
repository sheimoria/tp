package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditCommand.EditClientDescriptor descriptorWithSameValues = new EditCommand.EditClientDescriptor(DESC_AMY);
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, descriptorWithSameValues));
    }

    @Test
    public void constructor_nullEditClientDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(INDEX_FIRST_CLIENT, null));
    }

}
