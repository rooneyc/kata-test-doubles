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

    @Test
    public void should_have_an_initial_sellTotal_of_zero() throws Exception {

        //When
        ConcreteOrderSummary summary = new ConcreteOrderSummary();

        //Then
        assertThat(summary.sellTotal()).isEqualTo(Money.parse("USD 0.00"));

    }

    @Test
    public void should_be_able_to_set_buyTotal() throws Exception {

        //Given
        ConcreteOrderSummary summary = new ConcreteOrderSummary();

        //When
        summary = summary.withBuyTotal(Money.parse("USD 248724.00"));

        //Then
        assertThat(summary.buyTotal()).isEqualTo(Money.parse("USD 248724.00"));

    }

    @Test
    public void should_be_able_to_set_sellTotal() throws Exception {

        //Given
        ConcreteOrderSummary summary = new ConcreteOrderSummary();

        //When
        summary = summary.withSellTotal(Money.parse("USD 43894.4"));

        //Then
        assertThat(summary.sellTotal()).isEqualTo(Money.parse("USD 43894.4"));

    }

}