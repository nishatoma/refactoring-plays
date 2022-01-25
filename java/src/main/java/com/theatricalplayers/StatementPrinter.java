package com.theatricalplayers;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static util.PlayConstants.*;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        return getPlainTextStatement(invoice, plays);
    }

    private String getPlainTextStatement(Invoice invoice, Map<String, Play> plays) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.customer));

        for (var perf : invoice.performances) {
            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", getPlay(plays, perf).name, formatUSD(calculateAmount(getPlay(plays, perf), perf)), perf.audience));
        }

        result.append(String.format("Amount owed is %s\n", formatUSD(getTotalAmount(plays, invoice))));
        result.append(String.format("You earned %s credits\n", getTotalVolume(plays, invoice)));
        return result.toString();
    }

    private static int getTotalAmount(Map<String, Play> plays, Invoice invoice) {
        var result = 0;

        for (var perf: invoice.performances) {
            result += calculateAmount(getPlay(plays, perf), perf);
        }

        return result;
    }

    private static int getTotalVolume(Map<String, Play> plays, Invoice invoice) {
        var result = 0;

        for (var perf : invoice.performances) {
            // add volume credits
            result += calculateVolumeCredits(getPlay(plays, perf), perf);
        }

        return result;
    }

    private static Play getPlay(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID);
    }

    private static String formatUSD(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / USD_FORMAT);
    }

    private static int calculateVolumeCredits(Play play, Performance perf) {
        var result = 0;
        result += Math.max(perf.audience - TRAGEDY_AUDIENCE_LIMIT, result);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) {
            result += Math.floor(perf.audience / COMEDY_ATTENDEES);
        }

        return result;
    }

    private static int calculateAmount(Play play, Performance perf) {
        var result = 0;

        switch (play.type) {
            case "tragedy":
                result = TRAGEDY_BASE_AMOUNT;
                if (perf.audience > TRAGEDY_AUDIENCE_LIMIT) {
                    result += TRAGEDY_BONUS_AMOUNT * (perf.audience - TRAGEDY_AUDIENCE_LIMIT);
                }
                break;
            case "comedy":
                result = COMEDY_BASE_AMOUNT;
                if (perf.audience > COMEDY_AUDIENCE_LIMIT) {
                    result += COMEDY_BONUS_AMOUNT + COMEDY_BONUS_MULTIPLIER * (perf.audience - COMEDY_AUDIENCE_LIMIT);
                }
                result += COMEDY_BASE_MULTIPLIER * perf.audience;
                break;
            default:
                throw new Error("unknown type: " + play.type);
        }
        return result;
    }

}
