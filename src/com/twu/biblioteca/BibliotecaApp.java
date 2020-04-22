package com.twu.biblioteca;

import com.twu.biblioteca.handler.*;

public class BibliotecaApp {

    protected void start() {
        WelcomeHandler welcomeHandler = new WelcomeHandler();

        BookListHandler bookListHandler = new BookListHandler();
        BookListHandler.loadData();

        CheckoutHandler checkoutHandler = new CheckoutHandler();
        ReturnHandler returnHandler = new ReturnHandler();

        welcomeHandler.delegateTo(new InputHandler[] {bookListHandler, returnHandler});
        bookListHandler.delegateTo(checkoutHandler);

        welcomeHandler.run();
    }

    // Main thread runner method
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();
    }
}
