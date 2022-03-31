package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Datetime should be in DD-MM-YYYY HH:mm format.";

    /*
     * Datetime should be in dd-MM-yyyy HH:mm format.
     */
    public static final String VALIDATION_REGEX =
            "^([012][1-9]|3[01])-([0][1-9]|1[012])-([0-9][0-9][0-9][0-9])\\s([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$";

    public final String value;

    public DateTime() {
        value = "";
    }

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = dateTime;
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the datetime as a {@code LocalDateTime} object.
     */
    public LocalDateTime getDateTime() {
        if (value.equals("")) {
            return LocalDateTime.parse("01-01-0001 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    //    public boolean isBefore(DateTime other) {
    //        if (parse(value).isBefore(parse(other.value))) {
    //            return true;
    //        }
    //        return false;
    //    }
    //
    //    public boolean isAfter(DateTime other) {
    //        if (parse(value).isAfter(parse(other.value))) {
    //            return true;
    //        }
    //        return false;
    //    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
