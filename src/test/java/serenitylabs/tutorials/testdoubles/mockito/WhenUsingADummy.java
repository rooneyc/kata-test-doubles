package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WhenUsingADummy {

    @Test
    public void we_do_not_worry_about_the_value_it_returns_because_we_know_it_will_never_be_used() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);

        //When
        System system = new System(authoriser);

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(0);

    }

    @Test
    public void we_make_it_return_a_null_so_that_it_does_not_get_used_accidentally() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        System system = new System(authoriser);
        given(authoriser.authorise(anyString(), anyString())).willReturn(null);

        try {
            //When
            system.login("bob", "SecretPassword");
            failBecauseExceptionWasNotThrown(NullPointerException.class);
        } catch (NullPointerException e) {
            //Then
            assertThat(e).isInstanceOf(NullPointerException.class);
        }

    }
}
