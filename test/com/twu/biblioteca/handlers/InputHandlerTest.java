package com.twu.biblioteca.handlers;

import com.twu.biblioteca.components.User;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputHandlerTest {
    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected final PrintStream originalOutputStream = System.out;
    protected final User newUser = new User(
        "12345",
        "Terry",
        "Lu",
        "terrylu@mail.com"
    );

    @Before
    public void setOutputStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testComposeOptionsMethod() {
        Assert.assertEquals(
            String.join("\n", "[1] Option A", "[2] Option B"),
            InputHandler.composeOptionString(true, "Option A", "Option B")
        );
        Assert.assertEquals(
            String.join(
                "\n",
                "[1] Another option A",
                "[2] New option B",
                "[3] Option C",
                "[4] Option D"
            ),
            InputHandler.composeOptionString(
                true,
                "Another option A",
                "New option B",
                "Option C",
                "Option D"
            )
        );
        Assert.assertEquals(
            String.join("\n", "Option A", "Option B"),
            InputHandler.composeOptionString(false, "Option A", "Option B")
        );
    }

    @Test
    public void testPrintFallbackOptionMethod() {
        InputHandler.printFallbackOption();
        Assert.assertEquals(
            "\n(Type " +
            InputHandler.EXIT_FLAG +
            " to exit the program, or " +
            InputHandler.RETURN_FLAG +
            " to return to previous section)\n",
            outputStream.toString()
        );
    }

    @Test
    public void testInitiateUserSessionMethod() {
        InputHandler.SIGNED_IN_AS = newUser;
        InputHandler.initiateUserSession();
        Assert.assertEquals(1, InputHandler.USER_SESSIONS.size());

        InputHandler.SIGNED_IN_AS =
            new User("67890", "Terry", "Low", "terrylow@mail.com");
        InputHandler.initiateUserSession();
        Assert.assertEquals(2, InputHandler.USER_SESSIONS.size());

        InputHandler.SIGNED_IN_AS = newUser;
        InputHandler.initiateUserSession();
        Assert.assertEquals(2, InputHandler.USER_SESSIONS.size());
    }

    @Test
    public void testGetCurrentUserSessionMethod() {
        InputHandler.SIGNED_IN_AS = newUser;
        InputHandler.initiateUserSession();
        Assert.assertNotNull(
            InputHandler
                .getCurrentUserSession()
                .get(InputHandler.CHECKOUT_ITEMS_KEY)
        );
    }

    @Test
    public void testGetCurrentUserCheckoutItemMethod() {
        Assert.assertNotNull(InputHandler.getCurrentUserCheckoutItems());
    }

    @After
    public void resetOutputStream() {
        System.setOut(originalOutputStream);
    }
}
