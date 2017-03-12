package serenitylabs.tutorials.stockbroker.exchange;

import org.joda.money.Money;

import java.util.StringJoiner;

public class Order {
    private final String stockSymbol;
    private final int quantity;
    private final Money price;
    private final OrderType orderType;

    public Order(String stockSymbol, int quantity, Money price, OrderType orderType) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    public String symbol() {
        return stockSymbol;
    }

    public Money price() {
        return price;
    }

    public int quantity() {
        return quantity;
    }

    public OrderType type() { return orderType; }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("stockSymbol=" + stockSymbol)
                .add("quantity=" + quantity)
                .add("price=" + price)
                .add("orderType=" + orderType)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (stockSymbol != null ? !stockSymbol.equals(order.stockSymbol) : order.stockSymbol != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        return orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        int result = stockSymbol != null ? stockSymbol.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        return result;
    }
}
