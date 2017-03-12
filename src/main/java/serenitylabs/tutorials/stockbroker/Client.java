package serenitylabs.tutorials.stockbroker;

import serenitylabs.tutorials.stockbroker.exchange.Order;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import java.util.Iterator;
import java.util.List;

public class Client {
    private final StockBroker broker;
    private final OrderParser parser;

    private static final String BUY = "Buy: ";
    private static final String SELL = "Sell: ";
    private static final String FAILED = "Failed: ";
    private static final String SEPARATOR = ", ";

    public Client(StockBroker broker, OrderParser parser) {
        this.broker = broker;
        this.parser = parser;
    }

    String place(String orders) {

        List<Order> orderList = parser.parse(orders);
        OrderSummaryInterface orderSummary = broker.place(orderList);

        String outputString = BUY + orderSummary.buyTotal() + SEPARATOR + SELL + orderSummary.sellTotal();

        if (orderSummary.failedOrders().isEmpty()) {
            return outputString;
        } else {

            Iterator<Order> itr = orderSummary.failedOrders().iterator();
            StringBuilder failedOrderSymbols = new StringBuilder(itr.next().symbol());

            while (itr.hasNext()){
                failedOrderSymbols.append(SEPARATOR).append(itr.next().symbol());
            }

            return outputString + SEPARATOR + FAILED + failedOrderSymbols;
        }

    }
}
