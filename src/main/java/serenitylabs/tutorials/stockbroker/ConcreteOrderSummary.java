package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;

import java.util.List;

public class ConcreteOrderSummary implements OrderSummary{

    Money buyTotal = Money.parse("USD 0.00");
    Money sellTotal = Money.parse("USD 0.00");

    @Override
    public Money buyTotal() {
        return buyTotal;
    }

    @Override
    public Money sellTotal() {
        return sellTotal;
    }

    @Override
    public List<Order> failedOrders() {
        return null;
    }

    ConcreteOrderSummary withBuyTotal (Money buyTotal) {
        this.buyTotal = buyTotal;
        return this;
    }

    ConcreteOrderSummary withSellTotal (Money sellTotal) {
        this.sellTotal = sellTotal;
        return this;
    }

}
