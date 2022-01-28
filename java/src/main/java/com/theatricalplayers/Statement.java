package com.theatricalplayers;

import play.Play;
import util.PlayUtils;

import java.util.List;
import java.util.Map;

import static util.PlayConstants.*;
import static util.PlayUtils.*;

public class Statement {

    private Invoice invoice;
    private Map<String, ? extends Play> plays;

    public Statement(Invoice invoice, Map<String, ? extends Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Map<String, ? extends Play> getPlays() {
        return plays;
    }

    public void setPlays(Map<String, ? extends Play> plays) {
        this.plays = plays;
    }

    private Play getPlay(Performance performance) {
        return PlayUtils.getPlay(this.plays, performance);
    }

    public String getStatementTitle() {
        return String.format(STATEMENT_TITLE, this.invoice.getCustomer());
    }

    public String getPlayStats() {
        StringBuilder result = new StringBuilder();

        for (Performance perf : this.invoice.getPerformances()) {
            // print line for this order
            result.append(String.format(PLAY_STATS_TITLE, this.getPlay(perf).getName(),
                    formatUSD(this.getPlay(perf).getProfit(perf.getAudience())), perf.getAudience()));
        }

        return result.toString();
    }

    public String getTotalBill() {
        return String.format(TOTAL_BILL_TITLE, formatUSD(getTotalAmount(this.plays, this.invoice)));
    }

    public String getTotalCredit() {
        return String.format(CREDITS_EARNED_TITLE, getTotalVolume(plays, invoice));
    }
}
