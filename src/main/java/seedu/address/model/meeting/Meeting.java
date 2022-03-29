package seedu.address.model.meeting;


import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.model.client.Client;
import seedu.address.model.client.Name;


/**
* Represents a meeting with a Client in the address book.
*/
public class Meeting {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String DATETIME_MESSAGE_CONSTRAINTS =
            "Date times should consist of dates and times in the format of dd-MM-yyyy HH:mm";
    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;
    public final Client client;
    public final String label;

    /**
    * Constructs a {@code Meeting}.
     * @param startDateTime A date time object representing the start of the meeting.
     * @param endDateTime A date time object representing the end of the meeting.
     * @param client A client object representing the client being met.
     * @param label A label labelling the meeting.
    */
    public Meeting(LocalDateTime startDateTime, LocalDateTime endDateTime, Client client, String label) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.client = client;
        this.label = label;
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
     * Returns true if the meeting is happening today or in the future.
     */
    public boolean isUpcoming() {
        LocalDateTime yesterday = LocalDate.now().atStartOfDay().minusMinutes(1);

        return startDateTime.isAfter(yesterday);
    }

    /**
     * Returns true if a string is in the right format to convert into LocalDate.
     */
    public static boolean isValidDate(String stringDate) {
        try {
            LocalDate.parse(stringDate, Meeting.DATE_FORMATTER);
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
     * Returns the name of the client.
     */
    public Name getName() {
        return client.getName();
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

    /**
     * Returns the client being met for the meeting.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns the label of the meeting.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Updates the client and returns a new meeting.
     */
    public Meeting updateClient(Client editedClient) {
        return new Meeting(startDateTime, endDateTime, editedClient, label);
    }


    @Override
    public String toString() {
        if (label.equals("")) {
            return String.format("Meeting with %s from %s to %s",
                    client.getName().toString(),
                    startDateTime.format(DATETIME_FORMATTER),
                    endDateTime.format(DATETIME_FORMATTER));
        } else {
            return String.format("%s with %s from %s to %s",
                    label,
                    client.getName().toString(),
                    startDateTime.format(DATETIME_FORMATTER),
                    endDateTime.format(DATETIME_FORMATTER));
        }
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
                && otherMeeting.client.equals(client) && otherMeeting.label.equals(label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, client, label);
    }
}
