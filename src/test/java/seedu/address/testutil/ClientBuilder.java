package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Date;
import seedu.address.model.client.DateTime;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Note;
import seedu.address.model.client.Phone;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;
import seedu.address.model.policy.UniquePolicyList;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "21-03-1999";
    public static final String DEFAULT_LAST_CONTACTED = "21-03-1999 21:03";

    public static final String DEFAULT_POLICY_NAME = "Big Insurance Policy";
    public static final String DEFAULT_COMPANY = "Big Insurance Company";
    public static final String DEFAULT_POLICY_MANAGER = "Vijay";
    public static final String DEFAULT_PREMIUM = "100";
    public static final String DEFAULT_NOTE = "Test Note";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Date birthday;
    private DateTime lastContacted;
    private Set<Tag> tags;
    private UniquePolicyList policies = new UniquePolicyList();
    private Note note;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Date(DEFAULT_BIRTHDAY);
        lastContacted = new DateTime(DEFAULT_LAST_CONTACTED);
        tags = new HashSet<>();

        Policy defaultPolicy = new Policy(new Name(DEFAULT_POLICY_NAME), new Name(DEFAULT_COMPANY),
                new Name(DEFAULT_POLICY_MANAGER),
                new Premium(DEFAULT_PREMIUM));
        policies.add(defaultPolicy);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        lastContacted = clientToCopy.getLastContacted();
        tags = new HashSet<>(clientToCopy.getTags());
        policies.setPolicies(clientToCopy.getPolicies());
        note = clientToCopy.getNote();
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withAddress() {
        this.address = new Address();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code birthday} of the {@code Client} that we are building.
     */
    public ClientBuilder withBirthday(String birthday) {
        this.birthday = new Date(birthday);
        return this;
    }

    /**
     * Sets the {@code lastContacted} of the {@code Client} that we are building.
     */
    public ClientBuilder withLastContacted(String lastContacted) {
        this.lastContacted = new DateTime(lastContacted);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code policies} into a {@code Set<Policy>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withPolicies(Policy ... policies) {
        this.policies = SampleDataUtil.getPolicyList(policies);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Client} that we are building.
     */
    public ClientBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, address, birthday, lastContacted, tags);
    }

}
