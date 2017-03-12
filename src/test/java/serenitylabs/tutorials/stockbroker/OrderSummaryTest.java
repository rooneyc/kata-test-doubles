package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderSummaryTest {

    @Test
    public void should_only_be_able_to_update_failedOrders_directly() throws Exception {

        //Given
        Order order = new Order("ORCL", 1000, Money.parse("USD 42.69"), OrderType.Sell);
        OrderSummary summary = new OrderSummary();

        //When
        summary.failedOrders().add(order);

        //Then
        assertThat(summary.failedOrders()).doesNotContain(order);

    }

}