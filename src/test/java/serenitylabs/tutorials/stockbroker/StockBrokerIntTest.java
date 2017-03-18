package serenitylabs.tutorials.stockbroker;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class StockBrokerIntTest {


    @Test
    public void should_be_able_to_place_an_empty_order() {

        //Given
        StockBrokerTerminal terminal = new StockBrokerTerminal(mock(Client.class));

        //When
        String output = terminal.execute("");

        //Then
        assertThat(output).isEqualTo("Buy: USD 0.00, Sell: USD 0.00");

    }

}
