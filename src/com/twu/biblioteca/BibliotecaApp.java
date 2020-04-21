package com.twu.biblioteca;

import com.twu.biblioteca.handler.BookListHandler;
import com.twu.biblioteca.handler.WelcomeHandler;

public class BibliotecaApp {

    protected void start() {
        WelcomeHandler welcomeHandler = new WelcomeHandler();
        BookListHandler bookListHandler = new BookListHandler();
        BookListHandler.loadData();
        welcomeHandler.delegateTo(bookListHandler);
        welcomeHandler.run();
    }

    // Main thread runner method
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();
    }
}
