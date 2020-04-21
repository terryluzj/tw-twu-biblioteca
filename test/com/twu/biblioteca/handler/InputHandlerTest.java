package com.twu.biblioteca.handler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InputHandlerTest {

    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected final PrintStream originalOutputStream = System.out;

    @Before
    public void setOutputStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testComposeOptions() {
        Assert.assertEquals(
                InputHandler.composeOptionString(true, "Option A", "Option B")
                , String.join("\n", "[1] Option A", "[2] Option B") + "\n"
        );
        Assert.assertEquals(
                InputHandler.composeOptionString(true, "Another option A", "New option B", "Option C", "Option D")
                , String.join("\n", "[1] Another option A", "[2] New option B", "[3] Option C", "[4] Option D") + "\n"
        );
        Assert.assertEquals(InputHandler.composeOptionString(false, "Option A", "Option B"), String.join("\n", "Option A", "Option B") + "\n");
    }

    @Test
    public void testPrintFallbackOption() {
        InputHandler.printFallbackOption();
        Assert.assertEquals("\n(Type " + InputHandler.EXIT_FLAG + " to exit the program, or " + InputHandler.RETURN_FLAG + " to return to previous section)\n", outputStream.toString());
    }

    @After
    public void resetOutputStream() {
        System.setOut(originalOutputStream);
    }
}
