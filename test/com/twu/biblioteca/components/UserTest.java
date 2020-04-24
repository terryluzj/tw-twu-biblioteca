package com.twu.biblioteca.components;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    private final User user = new User("12345", "Terry", "Lu", "terrylu@mail.com");

    @Test
    public void testProperty() {
        Assert.assertEquals("Terry Lu", user.getName());
        Assert.assertEquals("terrylu@mail.com", user.getEmail());
        Assert.assertEquals("12345", user.getIdentifier());
    }
}
