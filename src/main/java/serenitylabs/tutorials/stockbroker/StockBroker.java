package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;

import java.util.List;

public class StockBroker {
    private final StockExchange exchange;

    public StockBroker(StockExchange exchange) {
        this.exchange = exchange;
    }

    public OrderSummary place(List<Order> orders) {
        return new OrderSummary() {
            @Override
            public Money buyTotal() {
                return Money.parse("USD 0.00");
            }

            @Override
            public Money sellTotal() {
                return null;
            }

            @Override
            public List<Order> failedOrders() {
                return null;
            }
        };
    }
}
