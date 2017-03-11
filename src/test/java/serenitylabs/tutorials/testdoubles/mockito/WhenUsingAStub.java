package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class WhenUsingAStub {

    @Mock
    private Authoriser authoriser;

    private System system;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        system = new System(authoriser);
    }

    @Test
    public void we_want_to_control_the_returned_value_to_make_the_system_under_test_accept_the_login_attempt() {

        //Given
        given(authoriser.authorise(anyString(), anyString())).willReturn(true);

        //When
        system.login("bob", "SecretPassword");

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(1);
    }

    @Test
    public void we_want_to_control_the_returned_value_to_make_the_system_under_test_reject_the_login_attempt() {

        //Given
        given(authoriser.authorise(anyString(), anyString())).willReturn(false);

        //When
        system.login("bob", "SecretPassword");

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(0);
    }
}
