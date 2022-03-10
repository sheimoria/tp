package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Datetime should be in DD/MM/YYYY HH:mm format.";

    /*
     * Datetime should be in DD/MM/YYYY HH:mm format.
     */
    public static final String VALIDATION_REGEX = "^([1-9]|([012][0-9])|(3[01]))\\/([0]{0,1}[1-9]|1[012])"
            + "\\/\\d\\d\\d\\d\\s-\\s([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$";

    public final LocalDateTime value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = parse(dateTime);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
