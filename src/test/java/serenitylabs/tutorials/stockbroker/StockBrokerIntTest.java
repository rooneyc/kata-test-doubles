package serenitylabs.tutorials.stockbroker;

import org.joda.money.CurrencyUnit;
import org.junit.Test;
import serenitylabs.tutorials.stockbroker.exchange.StockExchange;
import serenitylabs.tutorials.stockbroker.parser.OptimisticOrderParser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockBrokerIntTest {

    @Test
    public void should_be_able_to_place_an_empty_order() {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(null, null));

        //When
        String output = terminal.execute("");

        //Then
        assertThat(output).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");

    }

    @Test
    public void should_be_able_to_place_a_single_buy_order_scenario1() throws Exception {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));

        //When
        String output = terminal.execute("GOOG 300 829.08 B");

        //Then
        assertThat(output).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_buy_order_scenario2() throws Exception {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));

        //When
        String output = terminal.execute("ZNGA 1300 2.78 B");

        //Then
        assertThat(output).isEqualTo("Buy: USD 3614.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_sell_order_scenario1() throws Exception {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));

        //When
        String output = terminal.execute("FB 320 137.17 S");

        //Then
        assertThat(output).isEqualTo("Buy: USD 0.00, Sell: USD 43894.40");
    }

    @Test
    public void should_be_able_to_place_a_double_sell_order() throws Exception {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));

        //When
        String output = terminal.execute("FB 320 137.17 S,ORCL 1000 42.69 S");

        //Then
        assertThat(output).isEqualTo("Buy: USD 0.00, Sell: USD 86584.40");
    }

    @Test
    public void should_be_able_to_place_a_double_buy_order() throws Exception {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(new StockBroker(new StockExchange()), new OptimisticOrderParser(CurrencyUnit.of("USD"))));

        //When
        String output = terminal.execute("ZNGA 1300 2.78 B,AAPL 50 139.78 B");

        //Then
        assertThat(output).isEqualTo("Buy: USD 10603.00, Sell: USD 0.00");
    }

}
