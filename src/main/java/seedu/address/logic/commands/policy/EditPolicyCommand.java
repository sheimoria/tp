package seedu.address.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;
import seedu.address.model.policy.exceptions.InvalidPolicyIndexException;

/**
 * Edits a client identified using it's displayed index from the address book.
 */
public class EditPolicyCommand extends Command {

    public static final String COMMAND_WORD = "editPolicy";

    public static final String MESSAGE_SUCCESS = "Edited Policy: %s from %s's policy list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the policy identified by the client index number used in the displayed client list and "
            + "policy index number used in the displayed client card.\n"
            + "Parameters: INDEX pi/POLICY_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";


    private final Index clientIndex;
    private final Index policyIndex;
    private final EditPolicyDescriptor editPolicyDescriptor;

    /**
     * @param clientIndex index of client whose policy is to be edited
     * @param policyIndex index of the policy to be edited
     * @param editPolicyDescriptor details to edit the policy with
     */
    public EditPolicyCommand(Index clientIndex, Index policyIndex, EditPolicyDescriptor editPolicyDescriptor) {
        requireNonNull(clientIndex);
        requireNonNull(policyIndex);
        requireNonNull(editPolicyDescriptor);

        this.clientIndex = clientIndex;
        this.policyIndex = policyIndex;
        this.editPolicyDescriptor = editPolicyDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEditPolicy = lastShownList.get(clientIndex.getZeroBased());
        Policy editedPolicy;
        try {
            Name editedPolicyName = editPolicyDescriptor.getName().orElse(clientToEditPolicy.getPolicy(
                    policyIndex.getZeroBased()).getName());

            Name editedCompany = editPolicyDescriptor.getCompany().orElse(clientToEditPolicy.getPolicy(
                    policyIndex.getZeroBased()).getCompany());

            Name editedPolicyManager = editPolicyDescriptor.getPolicyManager().orElse(clientToEditPolicy.getPolicy(
                    policyIndex.getZeroBased()).getPolicyManager());

            Premium editedPremium =
                    editPolicyDescriptor.getPremium().orElse(clientToEditPolicy.getPolicy(
                            policyIndex.getZeroBased()).getPremium());

            editedPolicy = new Policy(editedPolicyName, editedCompany, editedPolicyManager, editedPremium);
            model.setClient(clientToEditPolicy, clientToEditPolicy.setPolicy(policyIndex.getZeroBased(), editedPolicy));
        } catch (InvalidPolicyIndexException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }
        assert editedPolicy != null;
        return new CommandResult((String.format(MESSAGE_SUCCESS, editedPolicy, clientToEditPolicy.getName())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && clientIndex.equals(((EditPolicyCommand) other).clientIndex)
                && policyIndex.equals(((EditPolicyCommand) other).policyIndex)
                && editPolicyDescriptor.equals(((EditPolicyCommand) other).editPolicyDescriptor));
    }

    /**
     * Stores the details to edit the policy with. Each non-empty field value will replace the
     * corresponding field value of the policy.
     */
    public static class EditPolicyDescriptor {
        private Name name;
        private Name company;
        private Name policyManager;
        private Premium premium;

        public EditPolicyDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, company, policyManager, premium);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setCompany(Name company) {
            this.company = company;
        }

        public Optional<Name> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setPolicyManager(Name policyManager) {
            this.policyManager = policyManager;
        }

        public Optional<Name> getPolicyManager() {
            return Optional.ofNullable(policyManager);
        }

        public void setPremium(Premium premium) {
            this.premium = premium;
        }

        public Optional<Premium> getPremium() {
            return Optional.ofNullable(premium);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPolicyDescriptor)) {
                return false;
            }

            // state check
            EditPolicyDescriptor e = (EditPolicyDescriptor) other;

            return getName().equals(e.getName())
                    && getCompany().equals(e.getCompany())
                    && getPolicyManager().equals(e.getPolicyManager())
                    && getPremium().equals(e.getPremium());
        }
    }
}
