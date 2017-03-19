package serenitylabs.tutorials.stockbroker;

import org.junit.Test;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest {

    @Test
    public void should_ask_order_parser_to_parse_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        OrderParser mockParser = mock(OrderParser.class);
        Client client = new Client(null, mockParser);

        //When
        client.place(order);

        //Then
        verify(mockParser).parse(order);
    }

    @Test
    public void should_ask_stock_broker_to_place_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        OrderParser mockParser = mock(OrderParser.class);
        StockBroker mockBroker = mock(StockBroker.class);
        Client client = new Client(mockBroker, mockParser);

        //When
        client.place(order);

        //Then
        verify(mockBroker).place(any());
    }
}
