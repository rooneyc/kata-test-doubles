package serenitylabs.tutorials.stockbroker.parser;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleOrderParserTest {

    @Test
    public void should_be_able_to_parse_an_order_string() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B";
        SimpleOrderParser orderParser = new SimpleOrderParser();
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);

        //When
        List<Order> orders = orderParser.parse("GOOG 300 829.08 B");

        //Then
        assertThat(orders).contains(parsedOrder);
    }

}