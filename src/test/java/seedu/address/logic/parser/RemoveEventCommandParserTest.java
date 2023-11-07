package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemoveEventCommand;

public class RemoveEventCommandParserTest {
    private RemoveEventCommandParser parser = new RemoveEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // dated event in user
        assertParseSuccess(parser, "user en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 Meeting", null));

        // dated event in friend
        assertParseSuccess(parser, "1 en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 Meeting", Index.fromOneBased(1)));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user en/CS2103 Meeting";

        // multiple event name
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));

        // multiple fields repeated
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));


        //valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));
    }

    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format("Command format is invalid! \n"
                + RemoveEventCommand.MESSAGE_USAGE);

        String expectedMessage2 = String.format("Invalid index!\n"
                + "Index can only be 'user' or a positive integer! \n");

        // missing event name prefix
        assertParseFailure(parser, "user",
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "user CS2103 Meeting",
                expectedMessage);

        //wrong index
        assertParseFailure(parser, "wrong en/CS2103 Meeting",
                expectedMessage2);
    }

}
