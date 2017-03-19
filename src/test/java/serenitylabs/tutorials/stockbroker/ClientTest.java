package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest {

    @Test
    public void should_ask_stock_broker_to_place_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        StockBroker broker = mock(StockBroker.class);
        Client client = new Client(broker, null);

        //When
        String summary = client.place(order);

        //Then
        verify(broker).place(Collections.singletonList(parsedOrder));
    }


}
