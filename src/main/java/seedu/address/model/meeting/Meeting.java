package seedu.address.model.meeting;


import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
* Represents a meeting with a Person in the address book.
*/
public class Meeting {

    public static final String DATETIME_MESSAGE_CONSTRAINTS =
            "Date times should consist of dates and times in the format of YYYY-MM-DD HH:mm";
    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;
    public final Person person;

    /**
    * Constructs a {@code Meeting}.
     * @param startDateTime A date time object representing the start of the meeting.
     * @param endDateTime A date time object representing the end of the meeting.
    */
    public Meeting(LocalDateTime startDateTime, LocalDateTime endDateTime, Person person) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.person = person;
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
            return true;
        }
    }

    /**
     * Returns true if a string is in the right format to convert into LocalDate.
     */
    public static boolean isValidDate(String stringDate) {
        try {
            LocalDate.parse(stringDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a string is in the right format to convert into LocalTime.
     */
    public static boolean isValidTime(String stringTime) {
        try {
            LocalTime.parse(stringTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given start time is before a given end time.
     */
    public static boolean isValidMeeting(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.compareTo(endDateTime) < 0;
    }

    /**
     * Returns the name of the person.
     */
    public Name getName() {
        return person.getName();
    }

    /**
     * Returns the start datetime of the meeting.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end datetime of the meeting.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }


    @Override
    public String toString() {
        return String.format("Meeting with %s from %s to %s",
                person.getName().toString(),
                startDateTime.toString(),
                endDateTime.toString());
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
        return otherMeeting.startDateTime.equals(startDateTime) && otherMeeting.endDateTime.equals(endDateTime)
                && otherMeeting.person.equals(person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, person);
    }
}
