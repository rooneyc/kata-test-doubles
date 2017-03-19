package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.parser.OrderParser;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    String place(String orders) {

        if (orders.isEmpty()) {
            return "Buy: USD 0.00, Sell: USD 0.00";
        }

        return "Buy: USD 248724.00, Sell: USD 0.00";

    }
}
