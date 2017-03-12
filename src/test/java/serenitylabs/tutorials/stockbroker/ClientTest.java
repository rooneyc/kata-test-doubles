package serenitylabs.tutorials.stockbroker;

import org.joda.money.Money;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.exchange.OrderType;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        OrderParser orderParser = createMockedParser(orderString, new ArrayList<>());
        StockBroker broker = createMockedBroker("0.00", "0.00", new ArrayList<>());
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
        OrderParser orderParser = createMockedParser(orderString, Collections.singletonList(parsedOrder));
        StockBroker broker = createMockedBroker("248724.00", "0.00", Collections.singletonList(parsedOrder));
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
        OrderParser orderParser = createMockedParser(orderString, Collections.singletonList(parsedOrder));
        StockBroker broker = createMockedBroker("0.00", "43894.40", Collections.singletonList(parsedOrder));
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
        List<Order> orders = Arrays.asList(
                new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy),
                new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell)
        );
        Order firstParsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        Order secondParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        OrderParser orderParser = createMockedParser(orderString, orders);
        StockBroker broker = createMockedBroker("248724.00", "43894.40", orders);
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
        List<Order> orders = Arrays.asList(
                new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy),
                new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy),
                new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell)
        );

        Order firstParsedOrder = new Order("ZNGA", 1300, Money.parse("USD 2.78"), OrderType.Buy);
        Order secondParsedOrder = new Order("AAPL", 50, Money.parse("USD 139.78"), OrderType.Buy);
        Order thirdParsedOrder = new Order("FB", 320, Money.parse("USD 137.17"), OrderType.Sell);
        //And
        OrderParser orderParser = createMockedParser(orderString, orders);
        StockBroker broker = createMockedBroker("10603.00", "43894.40", orders);

        Client client = new Client(broker, orderParser);

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
        Order parsedOrder = new Order("GOOG", 300, Money.parse("USD 829.08"), OrderType.Buy);
        //And
        OrderParser orderParser = createMockedParser(orderString, Collections.singletonList(parsedOrder));
        StockBroker broker = createMockedBroker("248724.00", "0.00", Collections.singletonList(parsedOrder));
        OrderSummary subbedOrderSummary = new OrderSummary();
        subbedOrderSummary.setBuyTotal(Money.parse("USD 248724.00"));
        subbedOrderSummary.setSellTotal(Money.parse("USD 0.00"));
        subbedOrderSummary.addFailedOrder(parsedOrder);
        given(broker.place(Collections.singletonList(parsedOrder))).willReturn(subbedOrderSummary);
        Client client = new Client(broker, orderParser);

        //When
        String orderSummary = client.place(orderString);

        //Then
        assertThat(orderSummary).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    private OrderParser createMockedParser(String orderString, List<Order> orderList){

        OrderParser orderParser = mock(OrderParser.class);
        given(orderParser.parse(orderString)).willReturn(orderList);

        return orderParser;
    }

    private StockBroker createMockedBroker(String buyTotal, String sellTotal, List<Order> orderList){

        OrderSummary stubbedOrderSummary = new OrderSummary();
        stubbedOrderSummary.setBuyTotal(Money.parse("USD " + buyTotal));
        stubbedOrderSummary.setSellTotal(Money.parse("USD " + sellTotal));

        StockBroker broker = mock(StockBroker.class);
        given(broker.place(orderList)).willReturn(stubbedOrderSummary);

        return broker;
    }

}
