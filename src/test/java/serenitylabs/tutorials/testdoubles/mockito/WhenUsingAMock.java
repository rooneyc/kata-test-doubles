package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenUsingAMock {

    @Test
    public void we_want_to_move_the_test_logic_from_the_test_to_the_mock_object() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        System system = new System(authoriser);

        //When
        system.login("bob", "SecretPassword");

        ///Then
        verify(authoriser).authorise("bob", "SecretPassword");

    }
}
