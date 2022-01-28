package util;

public final class PlayConstants {

    // cannot instantiate PlayConstants
    private PlayConstants() {}

    // Number Constants
    public static final int TRAGEDY_BASE_AMOUNT = 40000;
    public static final int TRAGEDY_AUDIENCE_LIMIT = 30;
    public static final int TRAGEDY_BONUS_AMOUNT = 1000;
    public static final int COMEDY_BASE_AMOUNT = 30000;
    public static final int COMEDY_AUDIENCE_LIMIT = 20;
    public static final int COMEDY_BONUS_AMOUNT = 10000;
    public static final int COMEDY_BASE_MULTIPLIER = 300;
    public static final int COMEDY_BONUS_MULTIPLIER = 500;
    public static final int USD_FORMAT = 100;
    public static final double COMEDY_ATTENDEES = 5.0;

    // String constants
    public static final String STATEMENT_TITLE = "Statement for %s\n";
    public static final String PLAY_STATS_TITLE = "  %s: %s (%s seats)\n";
    public static final String TOTAL_BILL_TITLE = "Amount owed is %s\n";
    public static final String CREDITS_EARNED_TITLE = "You earned %s credits\n";
}
