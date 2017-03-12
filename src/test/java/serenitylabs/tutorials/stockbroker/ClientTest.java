package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.SimpleOrderParser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ClientTest {

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //Given
        Client client = new Client(null, null);

        //When
        String orderSummary = client.place("");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //Given
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        List<Order> orderList = new ArrayList<>();
        orderList.add(parsedOrder);
        SimpleOrderParser orderParser = mock(SimpleOrderParser.class);
        given(orderParser.parse("GOOG 300 829.08 B")).willReturn(orderList);
        Client client = new Client(null, orderParser);

        //When
        String orderSummary = client.place("GOOG 300 829.08 B");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        Order parsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        List<Order> orderList = new ArrayList<>();
        orderList.add(parsedOrder);
        SimpleOrderParser orderParser = mock(SimpleOrderParser.class);
        given(orderParser.parse("FB 320 137.17 S")).willReturn(orderList);
        Client client = new Client(null, orderParser);

        //When
        String orderSummary = client.place("FB 320 137.17 S");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        Order firstParsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        List<Order> orderList = new ArrayList<>();
        orderList.add(firstParsedOrder);
        orderList.add(secondParsedOrder);
        SimpleOrderParser orderParser = mock(SimpleOrderParser.class);
        given(orderParser.parse("GOOG 300 829.08 B,FB 320 137.17 S")).willReturn(orderList);
        Client client = new Client(null, orderParser);

        //When
        String orderSummary = client.place("GOOG 300 829.08 B,FB 320 137.17 S");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 43894.40");
    }

}
