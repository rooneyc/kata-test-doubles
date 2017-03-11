package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.parser.OrderParser;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    public String place(String orders) {

        double bought = 0.00;
        double sold = 0.00;

        return "Buy: USD " + String.format("%.2f", bought) +", Sell: USD " + String.format("%.2f", sold);
    }
}
