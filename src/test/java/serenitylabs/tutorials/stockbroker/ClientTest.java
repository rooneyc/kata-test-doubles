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
        //And
        OrderParser orderParser = createMockedParser(orderString);
        StockBroker broker = createMockedBroker("0.00", "0.00");
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
        //And
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        //And
        OrderParser orderParser = createMockedParser(orderString, parsedOrder);
        StockBroker broker = createMockedBroker("248724.00", "0.00", parsedOrder);
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
        //And
        Order parsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        OrderParser orderParser = createMockedParser(orderString, parsedOrder);
        StockBroker broker = createMockedBroker("0.00", "43894.40", parsedOrder);
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
        //And
        Order firstParsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        OrderParser orderParser = createMockedParser(orderString, firstParsedOrder, secondParsedOrder);
        StockBroker broker = createMockedBroker("248724.00", "43894.40", firstParsedOrder,secondParsedOrder);
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
        //And
        Order firstParsedOrder = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        Order secondParsedOrder = new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy);
        Order thirdParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        OrderParser orderParser = createMockedParser(orderString, firstParsedOrder, secondParsedOrder, thirdParsedOrder);
        StockBroker broker = createMockedBroker("10603.00", "43894.40", firstParsedOrder, secondParsedOrder, thirdParsedOrder);

        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 10603.00, Sell: USD 43894.40");
    }

    @Test
    public void should_mention_a_failed_order() throws Exception {

    }

    private OrderParser createMockedParser(String orderString, Order... orders){

        List<Order> orderList = new ArrayList<>();
        orderList.addAll(Arrays.asList(orders));

        OrderParser orderParser = mock(OrderParser.class);
        given(orderParser.parse(orderString)).willReturn(orderList);

        return orderParser;
    }

    private StockBroker createMockedBroker(String buyTotal, String sellTotal, Order... orders){

        List<Order> orderList = new ArrayList<>();
        orderList.addAll(Arrays.asList(orders));

        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD " + buyTotal));
        stubbedOrderSummary.setSellTotal(Money.parse("USD " + sellTotal));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        return broker;
    }

}
