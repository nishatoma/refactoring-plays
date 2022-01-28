package com.theatricalplayers;

import play.Play;

import java.util.Map;

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
}
