package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;


/**
* Represents a meeting with a Person in the address book.
*/
public class Meeting {

    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;

    /**
    * Constructs a {@code Meeting}.
     * @param startDateTime A date time object representing the start of the meeting.
     * @param endDateTime A date time object representing the end of the meeting.
    */
    public Meeting(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns true if two meetings overlap.
     */
    public boolean isOverlapping(Meeting otherMeeting) {
        if (startDateTime.compareTo(otherMeeting.startDateTime) < 0) {
            return otherMeeting.startDateTime.compareTo(endDateTime) < 0;
        } else if (startDateTime.compareTo(otherMeeting.startDateTime) > 0) {
            return startDateTime.compareTo(otherMeeting.endDateTime) < 0;
        } else {
            return false;
        }
    }

    /**
     * Returns true if a given start time is before a given end time.
     */
    public static boolean isValidTagName(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.compareTo(endDateTime) < 0;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.startDateTime.equals(startDateTime) && otherMeeting.endDateTime.equals(endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }
}
