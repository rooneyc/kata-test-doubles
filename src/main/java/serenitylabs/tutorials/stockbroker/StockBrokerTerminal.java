package serenitylabs.tutorials.stockbroker;

import org.joda.money.CurrencyUnit;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;
import serenitylabs.tutorials.stockbroker.parser.OptimisticOrderParser;
import java.io.Console;

public class StockBrokerTerminal {

    private Client client;

    StockBrokerTerminal(Client client) {
        this.client = client;
    }

    public static void main(String[] args) {
        Console console =  System.console();
        String orders = console.readLine("/ ");
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));
        String output = terminal.execute(orders);
        console.format(output);
    }

    String execute(String orders) {
        return client.place(orders);
    }
}
