package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_LAST_CONTACTED = new Prefix("lc/");
    public static final Prefix PREFIX_DATETIME = new Prefix("dt/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_START_DATETIME = new Prefix("ms/");
    public static final Prefix PREFIX_END_DATETIME = new Prefix("me/");
    public static final Prefix PREFIX_LABEL = new Prefix("l/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");
    public static final Prefix PREFIX_POLICY_MANAGER = new Prefix("pm/");
    public static final Prefix PREFIX_PREMIUM = new Prefix("$/");
    public static final Prefix PREFIX_NOTE = new Prefix("nt/");
    public static final Prefix PREFIX_PREFERENCE_KEY = new Prefix("pk/");
    public static final Prefix PREFIX_PREFERENCE_DETAIL = new Prefix("pd/");
    public static final Prefix PREFIX_POLICY_INDEX = new Prefix("pi/");
}
