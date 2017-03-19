package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest {

    @Test
    public void should_ask_order_parser_to_parse_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        OrderParser mockParser = mock(OrderParser.class);
        StockBroker mockBroker = given(mock(StockBroker.class).place(any())).willReturn(new ConcreteOrderSummary().withBuyTotal(any())).getMock();
        Client client = new Client(mockBroker, mockParser);

        //When
        client.place(order);

        //Then
        verify(mockParser).parse(order);
    }

    @Test
    public void should_ask_stock_broker_to_place_order() throws Exception {

        //Given
        String order = "GOOG 300 829.08 B";
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        OrderParser mockParser = given(mock(OrderParser.class).parse(order)).willReturn(Collections.singletonList(parsedOrder)).getMock();
        StockBroker mockBroker = given(mock(StockBroker.class).place(any())).willReturn(new ConcreteOrderSummary().withBuyTotal(any())).getMock();
        Client client = new Client(mockBroker, mockParser);

        //When
        client.place(order);

        //Then
        verify(mockBroker).place(Collections.singletonList(parsedOrder));
    }

}
