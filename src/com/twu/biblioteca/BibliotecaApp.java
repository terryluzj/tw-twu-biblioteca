package com.twu.biblioteca;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.handlers.*;
import com.twu.biblioteca.handlers.item.BookListHandler;
import com.twu.biblioteca.handlers.item.MovieListHandler;
import com.twu.biblioteca.handlers.operation.*;
import java.util.HashMap;

public class BibliotecaApp {

    protected void start() {
        Library library = new Library("biblioteca");

        UserAuthHandler userAuthHandler = new UserAuthHandler(library);
        UserProfileHandler userProfileHandler = new UserProfileHandler(library);
        userAuthHandler.loadUserData();

        WelcomeHandler welcomeHandler = new WelcomeHandler(library);

        BookListHandler bookListHandler = new BookListHandler(library);
        bookListHandler.loadItemData();

        MovieListHandler movieListHandler = new MovieListHandler(library);
        movieListHandler.loadItemData();

        CheckoutHandler checkoutHandler = new CheckoutHandler(library);
        ReturnHandler returnHandler = new ReturnHandler(library);

        HashMap<String, InputHandler> delegateHandlerMap = new HashMap<>();
        delegateHandlerMap.put("List of books", bookListHandler);
        delegateHandlerMap.put("List of movies", movieListHandler);
        delegateHandlerMap.put("Return books", returnHandler);
        delegateHandlerMap.put("Return movies", returnHandler);
        delegateHandlerMap.put("View my profile", userProfileHandler);

        welcomeHandler.delegateTo(delegateHandlerMap);
        userAuthHandler.delegateTo(welcomeHandler);
        bookListHandler.delegateTo(checkoutHandler);
        movieListHandler.delegateTo(checkoutHandler);

        userAuthHandler.run();
    }

    // Main thread runner method
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();
    }
}
