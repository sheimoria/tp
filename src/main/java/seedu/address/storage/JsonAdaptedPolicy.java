package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    private final String policy;
    private final String company;
    private final String policyManager;
    private final String premium;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    JsonAdaptedPolicy(@JsonProperty("policy") String policy, @JsonProperty("company") String company,
                      @JsonProperty("policyManager") String policyManager, @JsonProperty("premium") String premium) {
        this.policy = policy;
        this.company = company;
        this.policyManager = policyManager;
        this.premium = premium;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policy = source.getName().toString();
        company = source.getCompany().toString();
        policyManager = source.getPolicyManager().toString();
        premium = source.getPremium().toString();
    }

    /**
     * Converts this Jackson-friendly adapted Policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Policy toModelType() throws IllegalValueException {
        if (!Name.isValidName(policy)
                || !Name.isValidName(company)
                || !Name.isValidName(policyManager)
                || !Premium.isValidPremium(premium)
        ) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Policy(new Name(policy), new Name(company), new Name(policyManager), new Premium(premium));
    }

}
