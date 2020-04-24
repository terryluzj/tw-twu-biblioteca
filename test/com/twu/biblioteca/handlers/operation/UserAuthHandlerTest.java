package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.handlers.InputHandlerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAuthHandlerTest extends InputHandlerTest {
    protected final Library library = new Library("Test Library");
    protected final UserAuthHandler userAuthHandler = new UserAuthHandler(
        library
    );

    @Before
    public void loadUserData() {
        userAuthHandler.loadUserData();
    }

    @Test
    public void testProperty() {
        Assert.assertEquals(1002, userAuthHandler.getUserPoolSize());
    }

    @Test
    public void testRetrieveOptionsMethod() {
        String[] options = userAuthHandler.retrieveOptions();
        Assert.assertArrayEquals(new String[] {  }, options);
    }

    @Test
    public void testParseInputMethod() {
        userAuthHandler.parseInput("");
    }

    @Test
    public void testPrintHeadingMethod() {
        userAuthHandler.printHeading();
        Assert.assertEquals(
            "Hi! please enter your credentials so we can log you in.\n",
            outputStream.toString()
        );
    }

    @Test
    public void testFindUserMethod() {
        Assert.assertEquals(
            "Elsbeth Wasselin",
            userAuthHandler.findUser("66689-694305").getName()
        );
        Assert.assertNull(userAuthHandler.findUser("49738-13182"));
    }

    @Test
    public void testAuthenticateUserMethod() {
        Assert.assertEquals(
            "Dory Stellin",
            userAuthHandler
                .authenticateUser("49288-0741216", "ug9FRPy")
                .getName()
        );
        Assert.assertNull(
            userAuthHandler.authenticateUser("49288-0741216", "ug9FRPs")
        );
    }
}
