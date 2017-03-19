package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;
import static serenitylabs.tutorials.stockbroker.exchange.OrderType.Buy;
import static serenitylabs.tutorials.stockbroker.exchange.OrderType.Sell;

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

        ConcreteOrderSummary summary = new ConcreteOrderSummary();

        for (Order order : orders) {

            int quantity = order.quantity();
            Money price = order.price();
            Money subtotal = price.multipliedBy(quantity);

            if (Buy.equals(order.type())) {
                return new ConcreteOrderSummary().withBuyTotal(subtotal);
            }

            if (Sell.equals(order.type())) {
                Money sellTotal = summary.sellTotal().plus(subtotal);
                summary = summary.withSellTotal(sellTotal);
            }
        }

        return summary;

    }


}
