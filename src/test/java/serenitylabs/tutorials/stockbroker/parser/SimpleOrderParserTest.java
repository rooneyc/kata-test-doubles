package serenitylabs.tutorials.stockbroker.parser;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.Client;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleOrderParserTest {

    @Test
    public void should_be_able_to_parse_an_empty_order_string() {

        //Given
        SimpleOrderParser orderParser = new SimpleOrderParser();

        //When
        List<Order> orders = orderParser.parse("");

        //Then
        assertThat(orders).isEmpty();
    }


    @Test
    public void should_be_able_to_parse_a_single_buy_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B";
        SimpleOrderParser orderParser = new SimpleOrderParser();
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);

        //When
        List<Order> orders = orderParser.parse(orderString);

        //Then
        assertThat(orders).contains(parsedOrder);
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B,FB 320 137.17 S";
        SimpleOrderParser orderParser = new SimpleOrderParser();
        Order firstParsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);

        //When
        List<Order> orders = orderParser.parse(orderString);

        //Then
        assertThat(orders).contains(firstParsedOrder);
        //And
        assertThat(orders).contains(secondParsedOrder);
    }
}