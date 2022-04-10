package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null date times
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));
        assertThrows(NullPointerException.class, () -> DateTime.isPastDateTime((null)));

        // invalid date times
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("00-00-0000 00:00")); // all zeros
        assertFalse(DateTime.isValidDateTime("21-03-2022")); // date only
        assertFalse(DateTime.isValidDateTime("21:03")); // time only
        assertFalse(DateTime.isPastDateTime("21-03-2032 21:03")); // future date time

        // valid date time
        assertTrue(DateTime.isValidDateTime("21-03-2022 21:03"));
    }
}
