package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary implements OrderSummaryInterface {

    private Money buyTotal = Money.parse("USD 0.00");
    private Money sellTotal = Money.parse("USD 0.00");
    private List<Order> failedOrders = new ArrayList<>();

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
        return failedOrders;
    }

    void setBuyTotal(Money buyTotal) {
        this.buyTotal = buyTotal;
    }

    void setSellTotal(Money sellTotal) {
        this.sellTotal = sellTotal;
    }
}
