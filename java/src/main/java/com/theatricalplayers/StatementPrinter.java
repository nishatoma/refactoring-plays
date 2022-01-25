package com.theatricalplayers;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {

            // add volume credits
            volumeCredits += calculateVolumeCredits(getPlay(plays, perf), perf);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(plays, perf).name, frmt.format(calculateAmount(getPlay(plays, perf), perf) / 100), perf.audience);
            totalAmount += calculateAmount(getPlay(plays, perf), perf);
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static Play getPlay(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID);
    }

    private static int calculateVolumeCredits(Play play, Performance perf) {
        var volumeCredits = 0;
        volumeCredits += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) {
            volumeCredits += Math.floor(perf.audience / 5);
        }

        return volumeCredits;
    }

    private static int calculateAmount(Play play, Performance perf) {
        var result = 0;

        switch (play.type) {
            case "tragedy":
                result = 40000;
                if (perf.audience > 30) {
                    result += 1000 * (perf.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.audience > 20) {
                    result += 10000 + 500 * (perf.audience - 20);
                }
                result += 300 * perf.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
