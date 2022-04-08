package seedu.address.logic.commands.policy;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;
import static seedu.address.testutil.TypicalPolicies.INVESTMENT;
import static seedu.address.testutil.TypicalPolicies.TRUST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.exceptions.DuplicatePolicyException;

public class AddPolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPolicyCommand(null, INSURANCE));
    }

    @Test
    public void constructor_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPolicyCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void execute_policyAcceptedByModel_success() {
        Policy policyToAdd = TRUST;
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(INDEX_SECOND_CLIENT, policyToAdd);
        String expectedMessage = String.format(AddPolicyCommand.MESSAGE_SUCCESS, policyToAdd);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(addPolicyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePolicy_failure() {
        Policy policyToAdd = INVESTMENT;
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(INDEX_SECOND_CLIENT, policyToAdd);
        String expectedMessage = new DuplicatePolicyException().getMessage();

        assertCommandFailure(addPolicyCommand, model, expectedMessage);

    }

    @Test
    public void execute_emptyAddressBook_failure() {
        Model model = new ModelManager();
        Policy policyToAdd = TRUST;
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(INDEX_SECOND_CLIENT, policyToAdd);
        String expectedMessage = String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "add policy to");

        assertCommandFailure(addPolicyCommand, model, expectedMessage);
    }
}
