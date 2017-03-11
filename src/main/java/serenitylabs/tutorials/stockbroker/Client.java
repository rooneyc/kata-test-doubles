package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.Arrays;
import java.util.List;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    String place(String orders) {

        if (orders.isEmpty()) {
            return "Buy: USD 0.00, Sell: USD 0.00";
        }

        double bought = 0.00;
        double sold = 0.00;

        List<String> orderParts = Arrays.asList(orders.split("\\s"));

        if ("B".equals(orderParts.get(3))) {
            bought = Integer.parseInt(orderParts.get(1))
                    * Double.parseDouble(orderParts.get(2));
        }

        if ("S".equals(orderParts.get(3))) {
            sold = Integer.parseInt(orderParts.get(1))
                    * Double.parseDouble(orderParts.get(2));
        }

        return "Buy: USD " + String.format("%.2f", bought) +", Sell: USD " + String.format("%.2f", sold);

    }
}
