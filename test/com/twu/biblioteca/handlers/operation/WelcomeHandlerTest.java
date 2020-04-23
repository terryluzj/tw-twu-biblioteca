package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.handlers.InputHandlerTest;
import org.junit.Assert;
import org.junit.Test;

public class WelcomeHandlerTest extends InputHandlerTest {

    protected final WelcomeHandler welcomeHandler = new WelcomeHandler(new Library("Test Library"));

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
                + "We currently have " + welcomeHandler.getLibrary().getAvailableBookCount() + " books for you to browse.\n"
                + "Enter a digit corresponding with the option that you want to proceed with.\n",
                outputStream.toString());
    }
}
