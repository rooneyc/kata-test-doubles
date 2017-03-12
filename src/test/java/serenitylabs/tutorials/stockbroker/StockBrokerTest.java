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
        List<Order> orders = new ArrayList<>();

        //When
        OrderSummary orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 0.00"));
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //Given
        StockExchange exchange = mock(StockExchange.class);
        StockBroker broker = new StockBroker(exchange);

        Order order = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        //When
        OrderSummary orderSummary = broker.place(orders);

        //Then
        assertThat(orderSummary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));
    }

}