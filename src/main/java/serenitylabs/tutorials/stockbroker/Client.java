package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
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

        Money bought = Money.parse("USD 0.00");
        Money sold = Money.parse("USD 0.00");

        if (orders.isEmpty()) {
            return BUY + bought + SEPERATOR + SELL + sold;
        }

        List<Order> orderList = parser.parse(orders);

        for (Order order : orderList) {

            if (order.type().equals(OrderType.Buy)) {
                bought = bought.plus(order.price().multipliedBy(order.quantity()));
            }

            if (order.type().equals(OrderType.Sell)) {
                sold = sold.plus(order.price().multipliedBy(order.quantity()));

            }
        }

        return BUY + bought + SEPERATOR + SELL + sold;

    }
}
