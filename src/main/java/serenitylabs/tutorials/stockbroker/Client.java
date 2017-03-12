package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.List;

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

        List<Order> orderList = parser.parse(orders);

        Money bought = Money.parse("USD 0.00");
        Money sold = Money.parse("USD 0.00");

        for (Order order : orderList) {

            if (order.type().equals(OrderType.Buy)) {
                bought = bought.plus(order.price().multipliedBy(order.quantity()));
            }

            if (order.type().equals(OrderType.Sell)) {
                sold = sold.plus(order.price().multipliedBy(order.quantity()));

            }
        }

        return "Buy: " + bought + ", Sell: " + sold;

    }
}
