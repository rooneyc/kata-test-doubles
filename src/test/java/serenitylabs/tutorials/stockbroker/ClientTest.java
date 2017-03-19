package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ClientTest {

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //When
        Client client = new Client(null, null);

        //Then
        assertThat(client.place("")).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_order() throws Exception {

        //Given
        OrderSummary summary = mock(OrderSummary.class);
        given(summary.buyTotal()).willReturn(Money.parse("USD 248724.00"));
        given(summary.sellTotal()).willReturn(Money.parse("USD 0.00"));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(any())).willReturn(summary);
        OrderParser parser = mock(OrderParser.class);

        Client client = new Client(broker, parser);

        //When
        String output = client.place("GOOG 300 829.08 B");

        //Then
        assertThat(output).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

//    @Test
//    public void should_be_able_to_place_a_single_order() throws Exception {
//
//        //Given
//        OrderSummary summary = mock(OrderSummary.class);
//        given(summary.buyTotal()).willReturn(Money.parse("USD 3614.00"));
//        StockBroker broker = mock(StockBroker.class);
//        given(broker).willReturn()
//        Client client = new Client(broker, null);
//
//        //When
//        client.place(orders);
//
//        //Then
//        assertThat(client.place("GOOG 300 829.08 B")).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
//        assertThat(client.place("ZNGA 1300 2.78 B")).isEqualTo("Buy: USD 3614.00, Sell: USD 0.00");
//    }

}
