package seedu.address.logic.parser.meeting;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.*;

public class ListMeetingCommandParser implements Parser<ListMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListMeetingCommand
     * and returns a ListMeetingCommand object for execution.
     * Returns a ListMeetingCommand with no index if no index was specified.
     */
    public ListMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETINGS_SHOW_ALL);
        boolean isShowAll = true;
        if (!arePrefixesPresent(argMultimap, PREFIX_MEETINGS_SHOW_ALL)) {
            isShowAll = false;
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            index = null;
        }
        return new ListMeetingCommand(index, isShowAll);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
