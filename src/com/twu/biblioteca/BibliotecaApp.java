package com.twu.biblioteca;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.handlers.*;
import com.twu.biblioteca.handlers.item.BookListHandler;
import com.twu.biblioteca.handlers.item.MovieListHandler;
import com.twu.biblioteca.handlers.operation.CheckoutHandler;
import com.twu.biblioteca.handlers.operation.ReturnHandler;
import com.twu.biblioteca.handlers.operation.WelcomeHandler;

public class BibliotecaApp {

    protected void start() {
        Library library = new Library("biblioteca");

        WelcomeHandler welcomeHandler = new WelcomeHandler(library);

        BookListHandler bookListHandler = new BookListHandler(library);
        bookListHandler.loadItemData();

        MovieListHandler movieListHandler = new MovieListHandler(library);
        movieListHandler.loadItemData();

        CheckoutHandler checkoutHandler = new CheckoutHandler(library);
        ReturnHandler returnHandler = new ReturnHandler(library);

        welcomeHandler.delegateTo(new InputHandler[] {bookListHandler, movieListHandler, returnHandler});
        bookListHandler.delegateTo(checkoutHandler);
        movieListHandler.delegateTo(checkoutHandler);

        welcomeHandler.run();
    }

    // Main thread runner method
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();
    }
}
