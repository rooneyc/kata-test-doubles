package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);
        //And
        Order order = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);
        //And
        Order order = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);
        //And
        Order firstOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));
        //And
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
    }

    @Test
    public void should_be_able_to_place_a_multi_order() throws Exception {

        //Given
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);
        //And
        Order firstOrder = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        Order secondOrder = new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy);
        Order thirdOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(thirdOrder);

        //When
        OrderSummaryInterface orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.sellTotal()).isEqualTo(Money.parse("USD 43894.40"));
        //And
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 10603.00"));

    }

}
