package seedu.address.logic;

import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeletePreferenceCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

public class DeletePreferenceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullClientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePreferenceCommand(null,
                VALID_PREFERENCE_CATEGORY));
    }

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePreferenceCommand(INDEX_FIRST_CLIENT, null));
    }
}
