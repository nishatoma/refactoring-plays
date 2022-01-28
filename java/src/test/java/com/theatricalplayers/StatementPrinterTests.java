package com.theatricalplayers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import play.ComedyPlay;
import play.Play;
import play.TragedyPlay;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        var plays = Map.of(
                "hamlet", new TragedyPlay("Hamlet"),
                "as-like", new ComedyPlay("As You Like It"),
                "othello", new TragedyPlay("Othello"));

        var invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        var statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        var plays = Map.of(
                "henry-v", new TragedyPlay("Henry V"),
                "as-like", new TragedyPlay("As You Like It"));

        var invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        var statementPrinter = new StatementPrinter();

        // With the new implementation, we should not expect an exception!
        Assertions.assertDoesNotThrow(() -> statementPrinter.print(invoice, plays));
    }
}
