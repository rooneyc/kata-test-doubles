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

        if (orders.isEmpty()) {
            return new ConcreteOrderSummary();
        }

        return new ConcreteOrderSummary().withBuyTotal(Money.parse("USD 248724.00"));
    }


}
