package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LAST_CONTACTED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LAST_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LAST_CONTACTED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_CONTACTED_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_CONTACTED_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.client.LastContacted.MESSAGE_FUTURE_DATETIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.model.client.LastContacted;

public class ContactedCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedCommand.MESSAGE_USAGE);

    private ContactedCommandParser parser = new ContactedCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LAST_CONTACTED_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + LAST_CONTACTED_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + LAST_CONTACTED_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid last contacted
        assertParseFailure(parser, "1" + INVALID_LAST_CONTACTED_DESC, DateTime.MESSAGE_CONSTRAINTS);
        // valid last contacted followed by invalid last contacted
        assertParseFailure(parser, "1" + LAST_CONTACTED_DESC_AMY + INVALID_LAST_CONTACTED_DESC,
                DateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + LAST_CONTACTED_DESC_AMY;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withLastContacted(VALID_LAST_CONTACTED_AMY).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + LAST_CONTACTED_DESC_AMY + LAST_CONTACTED_DESC_AMY
                + LAST_CONTACTED_DESC_BOB;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withLastContacted(VALID_LAST_CONTACTED_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_CLIENT;
        EditCommand.EditClientDescriptor edc = new EditCommand.EditClientDescriptor();
        LastContacted lastContacted = new LastContacted("21-03-2022 21:03");
        edc.setLastContacted(lastContacted);
        ContactedCommand expectedCommand = new ContactedCommand(targetIndex, edc);
        assertParseSuccess(parser, "1 lc/21-03-2022 21:03", expectedCommand);
    }
}
