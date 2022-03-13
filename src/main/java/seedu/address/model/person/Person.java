package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private DateTime lastContacted;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Policy> policies = new HashSet<>();
    private Note note = new Note("");
    private final PreferenceMap preferences = new PreferenceMap();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor for AddPolicyCommand.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Policy> policies) {
        requireAllNonNull(name, phone, email, address, tags, policies);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.policies.addAll(policies);
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor for NoteCommand.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Note note) {
        requireAllNonNull(name, phone, email, address, tags, note);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.note = note;
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor for all commands.
     */
    public Person(Name name, Phone phone, Email email, Address address, DateTime lastContacted, Set<Tag> tags,
                  Set<Policy> policies, Note note, PreferenceMap preferences) {
        requireAllNonNull(name, phone, email, address, tags, note);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.lastContacted = lastContacted;
        this.tags.addAll(tags);
        this.policies.addAll(policies);
        this.note = note;
        this.preferences.addAllPreferences(preferences);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public DateTime getLastContacted() {
        return lastContacted;
    }

    public PreferenceMap getPreferenceMap() {
        return this.preferences;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable policy set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Policy> getPolicies() {
        return policies;
    }

    /**
     * Returns a note
     */
    public Note getNote() {
        return note;
    }
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Last Contacted: ")
                .append(getLastContacted())
                .append("; Policies: ")
                .append(getPolicies().size())
                .append("; Note: ")
                .append(getNote())
                .append("; Preferences: ")
                .append(getPreferenceMap().size());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
