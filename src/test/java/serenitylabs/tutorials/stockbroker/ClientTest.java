package serenitylabs.tutorials.stockbroker;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Client client = new Client(null, null);

        //When
        String orderSummary = client.place("GOOG 300 829.08 B");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        Client client = new Client(null, null);

        //When
        String orderSummary = client.place("FB 320 137.17 S");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        Client client = new Client(null, null);

        //When
        String orderSummary = client.place("GOOG 300 829.08 B,FB 320 137.17 S");

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 43894.40");
    }

}
