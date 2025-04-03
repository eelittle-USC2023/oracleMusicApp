//Tested by Sakthi Thanigai

package com.model;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class AccountTest {

    // Inner mock class to instantiate the abstract Account class
    private class MockAccount extends Account {
        public MockAccount(String username, String password) {
            super(username, password);
        }
    }

    // ─────── Tests for getUsername ───────

    // Test that a standard username is returned correctly
    @Test
    public void testGetUsernameNormal() {
        Account acc = new MockAccount("user1", "pass");
        assertEquals("user1", acc.getUsername());
    }

    // Test that an empty username is returned correctly
    @Test
    public void testGetUsernameEmpty() {
        Account acc = new MockAccount("", "pass");
        assertEquals("", acc.getUsername());
    }

    // Test that usernames with special characters are handled correctly
    @Test
    public void testGetUsernameSpecialCharacters() {
        Account acc = new MockAccount("user!@#", "pass");
        assertEquals("user!@#", acc.getUsername());
    }

    // Test that numeric usernames are accepted and returned as expected
    @Test
    public void testGetUsernameNumbersOnly() {
        Account acc = new MockAccount("123456", "pass");
        assertEquals("123456", acc.getUsername());
    }

    // Test case sensitivity in usernames
    @Test
    public void testGetUsernameCaseSensitivity() {
        Account acc = new MockAccount("User", "pass");
        assertNotEquals("user", acc.getUsername()); // should be case-sensitive
    }

    // ─────── Tests for getPassword ───────

    // Test that a normal password is returned correctly
    @Test
    public void testGetPasswordNormal() {
        Account acc = new MockAccount("user", "mypassword");
        assertEquals("mypassword", acc.getPassword());
    }

    // Test that an empty password is allowed and returned
    @Test
    public void testGetPasswordEmpty() {
        Account acc = new MockAccount("user", "");
        assertEquals("", acc.getPassword());
    }

    // Test that passwords with special characters are returned correctly
    @Test
    public void testGetPasswordSpecialChars() {
        Account acc = new MockAccount("user", "p@$$w0rd!");
        assertEquals("p@$$w0rd!", acc.getPassword());
    }

    // Test that long passwords are accepted and the length is correct
    @Test
    public void testGetPasswordLength() {
        Account acc = new MockAccount("user", "a".repeat(50));
        assertEquals(50, acc.getPassword().length());
    }

    // Test that password is never null after construction
    @Test
    public void testGetPasswordNotNull() {
        Account acc = new MockAccount("user", "pass");
        assertNotNull(acc.getPassword());
    }

    // ─────── Tests for isCorrectPassword ───────

    // Test that an exact password match returns true
    @Test
    public void testCorrectPasswordExactMatch() {
        Account acc = new MockAccount("user", "1234");
        assertTrue(acc.isCorrectPassword("1234"));
    }

    // Test that an incorrect password returns false
    @Test
    public void testIncorrectPasswordWrongValue() {
        Account acc = new MockAccount("user", "abcd");
        assertFalse(acc.isCorrectPassword("dcba"));
    }

    // Test that password comparison is case-sensitive
    @Test
    public void testCorrectPasswordCaseSensitive() {
        Account acc = new MockAccount("user", "MyPass");
        assertFalse(acc.isCorrectPassword("mypass")); // lowercase
    }

    // Test that passwords with special characters are matched correctly
    @Test
    public void testCorrectPasswordWithSpecialChars() {
        Account acc = new MockAccount("user", "pa$$word!");
        assertTrue(acc.isCorrectPassword("pa$$word!"));
    }

    // Test that passing null throws a NullPointerException (expected bug!)
    @Test(expected = NullPointerException.class)
    public void testCorrectPasswordNullInput() {
        Account acc = new MockAccount("user", "notNull");
        acc.isCorrectPassword(null);
    }
}