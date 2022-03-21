package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_CONTACTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Date;
import seedu.address.model.client.DateTime;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Note;
import seedu.address.model.client.Phone;
import seedu.address.model.client.PreferenceMap;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;
import seedu.address.model.policy.exceptions.DuplicatePolicyException;
import seedu.address.model.policy.exceptions.InvalidPolicyIndexException;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "updateClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed client list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_LAST_CONTACTED + "LAST CONTACTED]\n"
            // + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_LAST_CONTACTED + "21/03/2022 21:03";

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;
    private final boolean isDelete;
    private final boolean isUpdate;

    /**
     * @param index of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(Index index, EditClientDescriptor editClientDescriptor) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
        this.isDelete = false;
        this.isUpdate = false;
    }

    /**
     * @param index of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(Index index, EditClientDescriptor editClientDescriptor, boolean isDelete) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);
        requireNonNull(isDelete);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
        this.isDelete = isDelete;
        this.isUpdate = !isDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient;

        try {
            editedClient = createEditedClient(clientToEdit, editClientDescriptor, isDelete, isUpdate);
        } catch (DuplicatePolicyException dpe) {
            throw new CommandException(dpe.getMessage());
        }

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor,
                                             boolean isDelete, boolean isUpdate) throws
            DuplicatePolicyException, InvalidPolicyIndexException {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Date updatedBirthday = editClientDescriptor.getBirthday().orElse(clientToEdit.getBirthday());
        DateTime updatedLastContacted = editClientDescriptor.getLastContacted().orElse(clientToEdit.getLastContacted());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        Note updatedNote = editClientDescriptor.getNote().orElse(clientToEdit.getNote());
        PreferenceMap updatedPreferences = editClientDescriptor.getPreferenceMap().orElse(clientToEdit
                .getPreferenceMap());
        List<Policy> updatedPolicies = new ArrayList<>();
        updatedPolicies.addAll(clientToEdit.getPolicies());

        if (!isDelete && !isUpdate && (
                editClientDescriptor.getPolicyName().isEmpty()
                    || editClientDescriptor.getCompany().isEmpty()
                    || editClientDescriptor.getPolicyManager().isEmpty()
                    || editClientDescriptor.getPremium().isEmpty()
            )) {
            return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                    updatedLastContacted, updatedTags, updatedPolicies, updatedNote, updatedPreferences);
        }

        Policy updatedPolicy;
        Name updatedPolicyName;
        Name updatedCompany;
        Name updatedPolicyManager;
        Premium updatedPremium;
        if (!isDelete && !isUpdate) {
            updatedPolicyName = editClientDescriptor.getPolicyName().get();
            updatedCompany = editClientDescriptor.getCompany().get();
            updatedPolicyManager = editClientDescriptor.getPolicyManager().get();
            updatedPremium = editClientDescriptor.getPremium().get();
            updatedPolicy = new Policy(updatedPolicyName, updatedCompany, updatedPolicyManager, updatedPremium);
            if (updatedPolicies.contains(updatedPolicy)) {
                throw new DuplicatePolicyException();
            }
            updatedPolicies.add(updatedPolicy);
        } else {
            assert editClientDescriptor.getPolicyIndex().isPresent();
            Index policyIndex = editClientDescriptor.getPolicyIndex().get();
            int index = policyIndex.getZeroBased();
            if (index >= updatedPolicies.size()) {
                throw new InvalidPolicyIndexException();
            }

            if (isDelete) {
                updatedPolicies.remove(index);
            } else if (isUpdate) {
                Policy policyToUpdate = updatedPolicies.get(index);
                updatedPolicyName = editClientDescriptor.getPolicyName().orElse(policyToUpdate.getName());
                updatedCompany = editClientDescriptor.getCompany().orElse(policyToUpdate.getCompany());
                updatedPolicyManager =
                        editClientDescriptor.getPolicyManager().orElse(policyToUpdate.getPolicyManager());
                updatedPremium = editClientDescriptor.getPremium().orElse(policyToUpdate.getPremium());
                updatedPolicy = new Policy(updatedPolicyName, updatedCompany, updatedPolicyManager, updatedPremium);
                updatedPolicies.set(index, updatedPolicy);
            }
        }
        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                updatedLastContacted, updatedTags, updatedPolicies, updatedNote, updatedPreferences);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Date birthday;
        private DateTime lastContacted;
        private Set<Tag> tags;
        private Name policyName;
        private Name company;
        private Name policyManager;
        private Premium premium;
        private Index policyIndex;
        private Note note;
        private PreferenceMap preferences;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setBirthday(toCopy.birthday);
            setLastContacted(toCopy.lastContacted);
            setTags(toCopy.tags);
            setPolicyName(toCopy.policyName);
            setCompany(toCopy.company);
            setPolicyManager(toCopy.policyManager);
            setPremium(toCopy.premium);
            setPolicyIndex(toCopy.policyIndex);
            setNote(toCopy.note);
            setPreferenceMap(toCopy.preferences);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, birthday, lastContacted, tags,
                    policyName, company, policyManager, premium, policyIndex);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Optional<Date> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setLastContacted(DateTime lastContacted) {
            this.lastContacted = lastContacted;
        }

        public Optional<DateTime> getLastContacted() {
            return Optional.ofNullable(lastContacted);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setPolicyName(Name policyName) {
            this.policyName = policyName;
        }

        public Optional<Name> getPolicyManager() {
            return Optional.ofNullable(policyManager);
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

        public Optional<Name> getPolicyName() {
            return Optional.ofNullable(policyName);
        }

        public void setPremium(Premium premium) {
            this.premium = premium;
        }

        public Optional<Premium> getPremium() {
            return Optional.ofNullable(premium);
        }

        public void setPolicyIndex(Index policyIndex) {
            this.policyIndex = policyIndex;
        }

        public Optional<Index> getPolicyIndex() {
            return Optional.ofNullable(policyIndex);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        public void setPreferenceMap(PreferenceMap preferences) {
            this.preferences = preferences;
        }

        public Optional<PreferenceMap> getPreferenceMap() {
            return Optional.ofNullable(preferences);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getBirthday().equals(e.getBirthday())
                    && getLastContacted().equals(e.getLastContacted())
                    && getTags().equals(e.getTags())
                    && getPolicyName().equals(e.getPolicyName())
                    && getCompany().equals(e.getCompany())
                    && getPolicyManager().equals(e.getPolicyManager())
                    && getPremium().equals(e.getPremium())
                    && getPolicyIndex().equals(e.getPolicyIndex())
                    && getNote().equals(e.getNote());
        }
    }
}
