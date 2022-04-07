package seedu.address.logic.commands.policy;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;
import seedu.address.testutil.PolicyBuilder;


public class EditPolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Policy editedPolicy = new PolicyBuilder().build();
    private Client targetClient = model.getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
    private EditPolicyDescriptor editPolicyDescriptor = new EditPolicyDescriptor(editedPolicy);

    @Test
    public void constructor_nullClientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(null, INDEX_FIRST_CLIENT,
                editPolicyDescriptor));
    }

    @Test
    public void constructor_nullPolicyIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(INDEX_SECOND_CLIENT, null,
                editPolicyDescriptor));
    }

    @Test
    public void constructor_nullEditPolicyDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT,
                null));
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        String expectedResult = String.format(EditPolicyCommand.MESSAGE_SUCCESS, editedPolicy, targetClient.getName());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setClient(targetClient, targetClient.setPolicy(INDEX_FIRST_CLIENT.getZeroBased(), editedPolicy));
        assertCommandSuccess(editPolicyCommand, model, expectedResult, expectedModel);
    }
}
