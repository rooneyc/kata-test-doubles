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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class StockBrokerTest {

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //Given
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);
        //And
        List<Order> orders = new ArrayList<>();

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 0.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //Given
        Order order = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        //And
        StockExchange exchange = mock(StockExchange.class);
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        Order order = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        StockExchange exchange = mock(StockExchange.class);
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        List<Order> orders = Arrays.asList(
                new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy),
                new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell)
        );
        //And
        StockExchange exchange = mock(StockExchange.class);
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
        //And
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));

    }

    @Test
    public void should_be_able_to_place_a_multi_order() throws Exception {

        //Given
        List<Order> orders = Arrays.asList(
                new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy),
                new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy),
                new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell)
        );
        //And
        StockExchange exchange = mock(StockExchange.class);
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 10603.00"));
        //And
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));

    }

    @Test
    public void should_mention_if_an_order_not_executed() throws Exception {

        //Given
        Order order = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        //And
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(Collections.singletonList(order));

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 0.00"));
        //And
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 0.00"));
        //And
        assertThat(orderSummary.failedOrders()).contains(order);
    }

    @Test
    public void should_mention_if_two_orders_not_executed() throws Exception {

        //Given
        Order successfulOrder1 = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        Order successfulOrder2 = new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy);
        Order failedOrder1 = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        Order failedOrder2 = new Order("ORCL", 1000, Money.parse("USD 42.69"), OrderType.Sell);
        //And
        List<Order> orders = Arrays.asList(successfulOrder1, successfulOrder2, failedOrder1, failedOrder2);
        StockExchange exchange = mock(StockExchange.class);
        given(exchange.execute(successfulOrder1)).willReturn(true);
        given(exchange.execute(successfulOrder2)).willReturn(true);
        given(exchange.execute(failedOrder1)).willReturn(false);
        given(exchange.execute(failedOrder2)).willReturn(false);
        StockBroker broker = new StockBroker(exchange);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 10603.00"));
        //And
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 00.00"));
        //And
        assertThat(orderSummary.failedOrders()).contains(failedOrder1);
        //And
        assertThat(orderSummary.failedOrders()).contains(failedOrder2);
    }

    //Should not be able to change the Buy, Sell or Failed Orders by bypassing OrderSummary

}
