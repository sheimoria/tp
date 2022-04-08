package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null dates
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));
        assertThrows(NullPointerException.class, () -> Date.isPastDate((null)));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("00-00-0000")); // all zeros
        assertFalse(Date.isValidDate("31-04-2021")); // non-existent date
        assertFalse(Date.isValidDate("29-02-2022")); // non-existent leap date
        assertFalse(Date.isPastDate("21-03-2103")); // future date

        // valid date time
        assertTrue(Date.isValidDate("21-03-2022"));
        assertTrue(Date.isValidDate("29-02-2020")); // existent leap date
    }
}
