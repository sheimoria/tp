package seedu.address.logic.parser.meeting;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListMeetingCommandParser implements Parser<ListMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListMeetingCommand
     * and returns a ListMeetingCommand object for execution.
     * Returns a ListMeetingCommand with no index if no index was specified.
     */
    public ListMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListMeetingCommand(index);
        } catch (ParseException pe) {
            return new ListMeetingCommand();
        }
    }
}
