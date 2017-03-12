package serenitylabs.tutorials.stockbroker.parser;

import serenitylabs.tutorials.stockbroker.exchange.Order;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrderParser implements OrderParser {

    @Override
    public List<Order> parse(String orders) {
        return new ArrayList<>();
    }
}
