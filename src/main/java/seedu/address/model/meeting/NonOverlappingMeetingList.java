package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.meeting.exceptions.OverlappingMeetingsException;


/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code Client#isSameClient(Client)}. As such, adding and updating of
 * clients uses Client#isSameClient(Client) for equality so as to ensure that the client being added or updated is
 * unique in terms of identity in the UniqueClientList. However, the removal of a client uses Client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isOverlapping(Meeting)
 */
public class NonOverlappingMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains any overlapping meetings.
     */
    public boolean overlaps(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isOverlapping);
    }

    /**
     * Adds a client to the list.
     * The client must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (overlaps(toAdd)) {
            throw new OverlappingMeetingsException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent client from the list.
     * The client must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonOverlappingMeetingList // instanceof handles nulls
                        && internalList.equals(((NonOverlappingMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
