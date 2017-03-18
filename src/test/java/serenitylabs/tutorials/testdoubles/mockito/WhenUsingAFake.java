package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WhenUsingAFake {

    @Test
    public void we_want_to_control_the_behaviour_of_the_mock_depending_on_the_parameters() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        given(authoriser.authorise("bob", "SecretPassword")).willReturn(Boolean.TRUE);
        System system = new System(authoriser);

        //When
        system.login("bob", "SecretPassword");
        system.login("kevin", "OtherSecretPassword");

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(1);

    }
}
