package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest {

    @Test
    public void should_ask_order_parser_to_parse_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        OrderParser parser = mock(OrderParser.class);
        StockBroker broker = mock(StockBroker.class);
        Client client = new Client(broker, parser);

        //When
        client.place(order);

        //Then
        verify(parser).parse(order);
    }

    @Test
    public void should_ask_stock_broker_to_place_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        OrderParser parser = mock(OrderParser.class);
        given(parser.parse(order)).willReturn(Collections.singletonList(parsedOrder));
        StockBroker broker = mock(StockBroker.class);
        Client client = new Client(broker, parser);

        //When
        client.place(order);

        //Then
        verify(broker).place(Collections.singletonList(parsedOrder));
    }

}
