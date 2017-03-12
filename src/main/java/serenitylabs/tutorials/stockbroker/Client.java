package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.List;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    private static final String BUY = "Buy: ";
    private static final String SELL = "Sell: ";
    private static final String SEPERATOR = ", ";

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    String place(String orders) {

        List<Order> orderList = parser.parse(orders);
        OrderSummaryInterface orderSummary = broker.place(orderList);

        return BUY + orderSummary.buyTotal() + SEPERATOR + SELL + orderSummary.sellTotal();

    }
}
