package com.theatricalplayers;

public class StatementPrinter {

    public String print(Statement statement) {
        return getPlainTextStatement(statement);
    }

    public String printHtml(Statement statement) {
        return getHtmlStatement(statement);
    }

    private String getPlainTextStatement(Statement statement) {
        return statement.getStatementTitle() + statement.getPlayStats() +
                statement.getTotalBill() +
                statement.getTotalCredit();
    }

    private String getHtmlStatement(Statement statement) {

        StringBuilder sb = new StringBuilder();

        sb.append("<h1>").append(statement.getStatementTitle()).append("</h1>\n");
        sb.append("<p>").append(statement.getPlayStats()).append("</p>\n");
        sb.append("<p>").append(statement.getTotalBill()).append("</p>\n");
        sb.append("<p>").append(statement.getTotalCredit()).append("</p>\n");

        return sb.toString();
    }
}
