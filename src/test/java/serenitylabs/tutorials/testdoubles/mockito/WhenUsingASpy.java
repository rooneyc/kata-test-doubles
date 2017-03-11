package serenitylabs.tutorials.testdoubles.mockito;

import org.junit.Ignore;
import org.junit.Test;
import serenitylabs.tutorials.testdoubles.Authoriser;
import serenitylabs.tutorials.testdoubles.System;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenUsingASpy {

    @Test
    public void we_want_to_ensure_that_the_spy_was_called() {

        //Given
        Authoriser authoriser = mock(Authoriser.class);
        System system = new System(authoriser);

        //When
        system.login("bob", "SecretPassword");

        ///Then
        verify(authoriser).authorise(anyString(), anyString());

    }
}
