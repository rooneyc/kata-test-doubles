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

        int quantity = orders.get(0).quantity();
        Money orderPrice = orders.get(0).price();
        Money totalPrice = orderPrice.multipliedBy(quantity);

        return new ConcreteOrderSummary().withBuyTotal(totalPrice);
    }


}
