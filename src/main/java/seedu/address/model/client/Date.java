package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in dd-MM-yyyy format.";

    /*
     * Date should be in dd-MM-yyyy format.
     */
    public static final String VALIDATION_REGEX = "^([012][1-9]|3[01])-([0][1-9]|1[012])-([0-9][0-9][0-9][1-9])$";

    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = parse(date);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String result = value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (result.equals("01-01-0001")) {
            return "-";
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public LocalDate parse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
