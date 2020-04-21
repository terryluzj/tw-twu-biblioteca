package com.twu.biblioteca;

import com.twu.biblioteca.handler.WelcomeHandler;

public class BibliotecaApp {

    protected void start() {
        WelcomeHandler welcomeHandler = new WelcomeHandler();
        welcomeHandler.run();
    }

    // Main thread runner method
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();
    }
}
