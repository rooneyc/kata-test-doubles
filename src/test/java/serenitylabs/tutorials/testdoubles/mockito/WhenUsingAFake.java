package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Ignore;
import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class WhenUsingAFake {

    @Test
    public void we_want_to_control_the_behaviour_of_the_mock_depending_on_the_parameters() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        System system = new System(authoriser);
        doReturn(true).when(authoriser).authorise("bob", "SecretPassword");

        //When
        system.login("bob", "SecretPassword");
        system.login("kevin", "OtherSecretPassword");

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(1);

    }
}
