package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Ignore;
import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WhenUsingAStub {

    @Test
    public void we_want_to_control_the_returned_value_to_make_the_system_under_test_accept_the_login_attempt() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        given(authoriser.authorise(anyString(), anyString())).willReturn(Boolean.TRUE);
        System system = new System(authoriser);

        //When
        system.login("bob", "SecretPassword");

        //Then
        assertThat(system.numberOfActiveUsers()).isEqualTo(1);

    }

    @Test
    @Ignore("Implement me, please ...")
    public void we_want_to_control_the_returned_value_to_make_the_system_under_test_reject_the_login_attempt() {

    }
}
