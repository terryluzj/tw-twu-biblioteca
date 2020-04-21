package com.twu.biblioteca.handler;

import org.junit.Assert;
import org.junit.Test;

public class WelcomeHandlerTest extends InputHandlerTest {

    protected final WelcomeHandler welcomeHandler = new WelcomeHandler();

    @Test
    public void testRetrieveOptionsMethod() {
        String[] options = welcomeHandler.retrieveOptions();
        Assert.assertArrayEquals(new String[]{"List of books"}, options);
    }

    @Test
    public void testParseInputMethod() {
        String[] result = welcomeHandler.parseInput("1");
        Assert.assertArrayEquals(new String[]{"1"}, result);
    }

    @Test
    public void testPrintWelcomeMessageMethod() {
        welcomeHandler.printHeading();
        Assert.assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n"
                + "Enter a digit corresponding with the option that you want to proceed with.\n",
                outputStream.toString());
    }
}
