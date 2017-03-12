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

        List<Order> orderList = new ArrayList<>();

        if (orders.isEmpty()) {
            return orderList;
        }

        List<String> orderStrings = Arrays.asList(orders.split(","));

        for (String orderString : orderStrings) {

            List<String> orderParts = Arrays.asList(orderString.split("\\s"));

            final String stockSymbol = orderParts.get(0);
            final int quantity = Integer.parseInt(orderParts.get(1));
            final Money price = Money.parse("USD " + orderParts.get(2));
            final OrderType orderType = OrderType.bySymbol(orderParts.get(3));

            Order order = new Order(stockSymbol, quantity, price, orderType);

            orderList.add(order);
        }

        return orderList;
    }
}
