package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.List;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    public String place(String orders) {

        List<Order> orderList = parser.parse(orders);
        OrderSummary summary = broker.place(orderList);

        return "Buy: USD 0.00, Sell: USD 0.00";
    }
}
