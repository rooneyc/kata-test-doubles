package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;

import java.util.List;

public class StockBroker {
    private final StockExchange exchange;

    public StockBroker(StockExchange exchange) {
        this.exchange = exchange;
    }

    OrderSummary place(List<Order> orders) {

        OrderSummary orderSummary = new OrderSummary();

        for (Order order : orders) {

            if (order.type().equals(OrderType.Buy)) {
                orderSummary.setBuyTotal(orderSummary.buyTotal().plus(order.price().multipliedBy(order.quantity())));
            }

            if (order.type().equals(OrderType.Sell)) {
                orderSummary.setSellTotal(orderSummary.sellTotal().plus(order.price().multipliedBy(order.quantity())));

            }
        }

        return orderSummary;
    }
}
