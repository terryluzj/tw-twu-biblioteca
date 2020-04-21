package com.twu.biblioteca.handler;

import org.junit.Assert;
import org.junit.Test;

public class WelcomeHandlerTest extends InputHandlerTest {

    protected final WelcomeHandler welcomeHandler = new WelcomeHandler();

    @Test
    public void testRetrieveOptions() {
        String[] options = welcomeHandler.retrieveOptions();
        Assert.assertArrayEquals(new String[]{"List of books"}, options);
    }

    @Test
    public void testParseInput() {
        String[] result = welcomeHandler.parseInput("1");
        Assert.assertArrayEquals(new String[]{"1"}, result);
    }

    @Test
    public void testPrintWelcomeMessage() {
        welcomeHandler.printHeading();
        Assert.assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outputStream.toString());
    }
}
