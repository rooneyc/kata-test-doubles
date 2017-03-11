package serenitylabs.tutorials.stockbroker;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClientTest {

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //When
        Client client = new Client(null, null);

        //Then
        assertThat(client.place("")).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //When
        Client client = new Client(null, null);

        //Then
        assertThat(client.place("GOOG 300 829.08 B")).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //When
        Client client = new Client(null, null);

        //Then
        assertThat(client.place("GOOG 300 829.08 S")).isEqualTo("Buy: USD 0.00, Sell: USD 248724.00");
    }

    @Test
    public void should_be_able_to_place_a_multi_order() throws Exception {

        //When
        Client client = new Client(null, null);

        //Then
        assertThat(client.place("ZNGA 1300 2.78 B,AAPL 50 139.78 B,FB 320 137.17 S")).isEqualTo("Buy: USD 10603.00, Sell: USD 43894.40");
    }

}
