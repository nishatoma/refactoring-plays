package com.theatricalplayers;

import play.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static util.PlayConstants.*;
import static util.PlayUtils.*;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, ? extends Play> plays) {
        return getPlainTextStatement(invoice, plays);
    }

    private String getPlainTextStatement(Invoice invoice, Map<String, ? extends Play> plays) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.getCustomer()));

        for (var perf : invoice.getPerformances()) {
            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", getPlay(plays, perf).name,
                    formatUSD(getPlay(plays, perf).getProfit(perf.getAudience())), perf.getAudience()));
        }

        result.append(String.format("Amount owed is %s\n", formatUSD(getTotalAmount(plays, invoice))));
        result.append(String.format("You earned %s credits\n", getTotalVolume(plays, invoice)));
        return result.toString();
    }
}
