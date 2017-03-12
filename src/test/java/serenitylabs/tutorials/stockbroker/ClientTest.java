package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ClientTest {

    @Test
    public void should_be_able_to_place_an_empty_order() throws Exception {

        //Given
        String orderString = "";
        OrderParser orderParser = createMockedParser(orderString);

        List<Order> orderList = new ArrayList<>();
        OrderSummary stubbedOrderSummary = new OrderSummary();
        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B";
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        OrderParser orderParser = createMockedParser(orderString, parsedOrder);

        List<Order> orderList = new ArrayList<>();
        orderList.add(parsedOrder);
        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD 248724.00"));
        stubbedOrderSummary.setSellTotal(Money.parse("USD 0.00"));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order() throws Exception {

        //Given
        String orderString = "FB 320 137.17 S";
        Order parsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        OrderParser orderParser = createMockedParser(orderString, parsedOrder);

        List<Order> orderList = new ArrayList<>();
        orderList.add(parsedOrder);
        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD 0.00"));
        stubbedOrderSummary.setSellTotal(Money.parse("USD 43894.40"));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 0.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_double_order() throws Exception {

        //Given
        String orderString = "GOOG 300 829.08 B,FB 320 137.17 S";
        Order firstParsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        OrderParser orderParser = createMockedParser(orderString, firstParsedOrder, secondParsedOrder);


        List<Order> orderList = new ArrayList<>();
        orderList.add(firstParsedOrder);
        orderList.add(secondParsedOrder);
        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD 248724.00"));
        stubbedOrderSummary.setSellTotal(Money.parse("USD 43894.40"));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_multi_order() throws Exception {

        //Given
        String orderString = "ZNGA 1300 2.78 B,AAPL 50 139.78 B,FB 320 137.17 S";
        Order firstParsedOrder = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        Order secondParsedOrder = new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy);
        Order thirdParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        OrderParser orderParser = createMockedParser(orderString, firstParsedOrder, secondParsedOrder, thirdParsedOrder);



        List<Order> orderList = new ArrayList<>();
        orderList.add(firstParsedOrder);
        orderList.add(secondParsedOrder);
        orderList.add(thirdParsedOrder);
        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD 10603.00"));
        stubbedOrderSummary.setSellTotal(Money.parse("USD 43894.40"));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 10603.00, Sell: USD 43894.40");
    }

    private OrderParser createMockedParser(String orderString, Order... orders){

        List<Order> orderList = new ArrayList<>();
        orderList.addAll(Arrays.asList(orders));

        OrderParser orderParser = mock(OrderParser.class);
        given(orderParser.parse(orderString)).willReturn(orderList);

        return orderParser;
    }

}
