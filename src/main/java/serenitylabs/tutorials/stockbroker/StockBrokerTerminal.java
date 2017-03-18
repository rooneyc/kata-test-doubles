package serenitylabs.tutorials.stockbroker;

import org.joda.money.CurrencyUnit;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;
import serenitylabs.tutorials.stockbroker.parser.OptimisticOrderParser;
import java.io.Console;

public class StockBrokerTerminal {

    StockBrokerTerminal(Client client) {

    }

    public static void main(String[] args) {
        Console console =  System.console();
        String input = console.readLine("/ ");
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));
        String output = terminal.execute(input);
        console.format(output);
    }

    String execute(String input) {
        return "Buy: USD 0.00, Sell: USD 0.00";
    }
}
