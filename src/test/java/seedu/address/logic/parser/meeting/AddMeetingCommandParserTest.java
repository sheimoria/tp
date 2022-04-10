package seedu.address.logic.parser.meeting;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

public class AddMeetingCommandParserTest {

    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {

        assertParseSuccess(parser,
                VALID_MEETING_INDEX
                        + " " + PREFIX_START_DATETIME + VALID_START_DATETIME_INPUT
                        + " " + PREFIX_END_DATETIME + VALID_END_DATETIME_INPUT
                        + " " + PREFIX_LABEL + VALID_LABEL,
                new AddMeetingCommand(INDEX_FIRST_MEETING,
                        ParserUtil.parseDateTime(VALID_START_DATETIME_INPUT),
                        ParserUtil.parseDateTime(VALID_END_DATETIME_INPUT),
                        VALID_LABEL));
    }

    @Test
    public void parse_invalidArgs_returnsAddMeetingCommand() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    }
}
