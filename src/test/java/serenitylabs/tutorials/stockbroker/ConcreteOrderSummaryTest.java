package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcreteOrderSummaryTest {

    @Test
    public void should_have_an_initial_buyTotal_of_zero() throws Exception {

        //When
        ConcreteOrderSummary summary = new ConcreteOrderSummary();

        //Then
        assertThat(summary.buyTotal()).isEqualTo(Money.parse("USD 0.00"));

    }

}