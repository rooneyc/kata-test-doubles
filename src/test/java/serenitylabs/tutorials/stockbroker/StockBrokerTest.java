package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;

import java.util.ArrayList;

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

}