package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class StockBrokerTest {

    @Test
    public void should_handle_attempt_to_place_an_empty_buy_order() throws Exception {

        //Given
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary orderSummary = broker.place(new ArrayList<>());

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 0.00"));
    }

    @Test
    public void should_handle_attempt_to_place_an_empty_sell_order() throws Exception {

        //Given
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary orderSummary = broker.place(new ArrayList<>());

        //Then
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 0.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order_scenario1() throws Exception {

        //Given
        Order order = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary summary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(summary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order_scenario2() throws Exception {

        //Given
        Order order = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary summary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(summary.buyTotal()).isEqualTo(Money.parse("USD 3614.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order_scenario1() throws Exception {

        //Given
        Order order = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary summary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(summary.sellTotal()).isEqualTo(Money.parse("USD 43894.4"));
    }

    @Test
    public void should_be_able_to_place_a_double_sell_order() throws Exception {

        //Given
        List<Order> orders = Arrays.asList(
            new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell),
            new Order("ORCL", 1000, Money.parse("USD 42.69"), OrderType.Sell)
        );
        StockBroker broker = new StockBroker(mock(StockExchange.class));

        //When
        OrderSummary summary = broker.place(orders);

        //Then
        assertThat(summary.sellTotal()).isEqualTo(Money.parse("USD 86584.40"));

    }

}