package serenitylabs.tutorials.stockbroker.parser;

import org.joda.money.Money;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleOrderParser implements OrderParser {

    @Override
    public List<Order> parse(String orders) {

        List<String> orderParts = Arrays.asList(orders.split("\\s"));

        final String stockSymbol = orderParts.get(0);
        final int quantity =  Integer.parseInt(orderParts.get(1));
        final Money price = Money.parse("USD " + orderParts.get(2));
        final OrderType orderType = OrderType.bySymbol(orderParts.get(3));

        Order order = new Order(stockSymbol, quantity, price, orderType);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        return orderList;
    }
}
