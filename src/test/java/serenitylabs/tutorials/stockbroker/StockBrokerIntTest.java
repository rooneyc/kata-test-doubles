package serenitylabs.tutorials.stockbroker;

import org.junit.Test;
import serenitylabs.tutorials.stockbroker.parser.OrderParser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

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
    public void should_be_able_to_place_a_single_order_scenario1() throws Exception {

        //Given
        OrderParser parser = mock(OrderParser.class);
        StockBroker broker = mock(StockBroker.class);
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(broker, parser));

        //When
        String output = terminal.execute("GOOG 300 829.08 B");

        //Then
        assertThat(output).isEqualTo("Buy: USD 248724.00, Sell: USD 0.00");
    }

    @Test
    public void should_be_able_to_place_a_single_order_scenario2() throws Exception {

        //Given
        OrderParser parser = mock(OrderParser.class);
        StockBroker broker = mock(StockBroker.class);
        StockBrokerTerminal terminal = new StockBrokerTerminal(new Client(broker, parser));

        //When
        String output = terminal.execute("ZNGA 1300 2.78 Bca");

        //Then
        assertThat(output).isEqualTo("Buy: USD 3614.00, Sell: USD 0.00");
    }

}
