package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.client.DateTime.MESSAGE_FUTURE_DATETIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.client.DateTime;

public class ContactedCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedCommand.MESSAGE_USAGE);

    private ContactedCommandParser parser = new ContactedCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "lc/21-03-2022 21:03", MESSAGE_INVALID_FORMAT);

        // no last contacted date specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 lc/21-03-2022 21:03", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 lc/21-03-2022 21:03", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string lc/21-03-2022 21:03", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string lc/21-03-2022 21:03", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidDate_failure() {
        // date after today
        assertParseFailure(parser, "1 lc/21-03-2099 21:03", MESSAGE_FUTURE_DATETIME);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        EditCommand.EditClientDescriptor edc = new EditCommand.EditClientDescriptor();
        DateTime lastContacted = new DateTime("21-03-2022 21:03");
        edc.setLastContacted(lastContacted);
        ContactedCommand expectedCommand = new ContactedCommand(targetIndex, edc);
        assertParseSuccess(parser, "1 lc/21-03-2022 21:03", expectedCommand);
    }
}
