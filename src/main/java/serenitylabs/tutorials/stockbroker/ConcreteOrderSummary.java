package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;

import java.util.List;

public class ConcreteOrderSummary implements OrderSummary{

    @Override
    public Money buyTotal() {
        return null;
    }

    @Override
    public Money sellTotal() {
        return null;
    }

    @Override
    public List<Order> failedOrders() {
        return null;
    }
}
