package serenitylabs.tutorials.stockbroker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;
import serenitylabs.tutorials.stockbroker.parser.SimpleOrderParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class ClientTest {

    @Mock
    private StockExchange exchange;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //Given
        String orderString = "";
        //And
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B";
        //And
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        String orderString = "FB 320 137.17 S";
        //And
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B,FB 320 137.17 S";
        //And
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_multi_order() throws Exception {

        //Given
        String orderString = "ZNGA 1300 2.78 B,AAPL 50 139.78 B,FB 320 137.17 S";
        //And
        given(exchange.execute(any(Order.class))).willReturn(true);
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 10603.00, Sell: USD 43894.40");
    }

    @Test
    public void should_mention_if_an_order_not_executed() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B";
        //And
        given(exchange.execute(any(Order.class))).willReturn(false);
        StockBroker broker = new StockBroker(exchange);
        Client client = new Client(broker, new SimpleOrderParser());

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 0.00, Failed: GOOG");
    }

}
