//Tested by Sakthi Thanigai

package com.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

public class AccountListTest {

    private AccountList accountList;

    @Before
    public void setUp() {
        accountList = AccountList.getInstance();
    }

    // ─────── Tests for addAccount() ───────

    // Adds a valid student account and checks it exists
    @Test
    public void testAddValidStudentAccount() {
        boolean added = accountList.addAccount("student1", "pass", "Student");
        assertTrue("Should add new student account", added);
    }

    // Adds a valid teacher account and checks it exists
    @Test
    public void testAddValidTeacherAccount() {
        boolean added = accountList.addAccount("teacher1", "pass", "Teacher");
        assertTrue("Should add new teacher account", added);
    }

    // Attempts to add a duplicate username (should fail)
    @Test
    public void testAddDuplicateAccountFails() {
        accountList.addAccount("dupUser", "pass", "Student");
        boolean addedAgain = accountList.addAccount("dupUser", "newpass", "Teacher");
        assertFalse("Should not allow duplicate usernames", addedAgain);
    }

    // Adds account with empty username — test should fail unless validation is added
    @Test
    public void testAddAccountWithEmptyUsername() {
        boolean added = accountList.addAccount("", "pass", "Student");
        assertFalse("Should not allow empty username", added); // FAILS unless validation added
    }

    // Adds account with invalid role — currently treated as teacher
    @Test
    public void testAddAccountWithInvalidRole() {
        boolean added = accountList.addAccount("weirdRole", "pass", "Admin");
        assertTrue("Should default to Teacher or reject role (currently defaults to Teacher)", added);
        Account acc = accountList.getAccount("weirdRole");
        assertTrue("Should be instance of Teacher due to fallback", acc instanceof Teacher);
    }

    // ─────── Tests for getAccount() ───────

    // Get an existing account
    @Test
    public void testGetExistingAccount() {
        accountList.addAccount("existingUser", "1234", "Student");
        Account acc = accountList.getAccount("existingUser");
        assertNotNull("Should find existing account", acc);
    }

    // Try to get a non-existent account
    @Test
    public void testGetNonExistentAccount() {
        Account acc = accountList.getAccount("ghostUser");
        assertNull("Should return null for non-existent user", acc);
    }

    // Try to get account with null username
    @Test
    public void testGetAccountNullUsername() {
        Account acc = accountList.getAccount(null);
        assertNull("Should return null for null username", acc); // might crash if not null-safe
    }

    // Try to get account with empty username
    @Test
    public void testGetAccountEmptyUsername() {
        Account acc = accountList.getAccount("");
        assertNull("Should return null for empty username");
    }

    // Check case sensitivity for usernames
    @Test
    public void testUsernameCaseSensitivity() {
        accountList.addAccount("CaseUser", "pass", "Student");
        Account acc = accountList.getAccount("caseuser");
        assertNull("Should be case-sensitive (CaseUser ≠ caseuser)");
    }

    // ─────── Tests for removeAccount() ───────

    // Remove an existing account
    @Test
    public void testRemoveExistingAccount() {
        accountList.addAccount("toRemove", "pass", "Student");
        Account acc = accountList.getAccount("toRemove");
        accountList.removeAccount(acc);
        assertNull("Account should be removed", accountList.getAccount("toRemove"));
    }

    // Try to remove null (should not crash)
    @Test
    public void testRemoveNullAccount() {
        try {
            accountList.removeAccount(null);
        } catch (Exception e) {
            fail("Should not throw exception when removing null");
        }
    }

    // Remove account twice (second remove does nothing)
    @Test
    public void testRemoveSameAccountTwice() {
        accountList.addAccount("doubleRemove", "pass", "Student");
        Account acc = accountList.getAccount("doubleRemove");
        accountList.removeAccount(acc);
        accountList.removeAccount(acc); // second call should not crash
        assertNull("Account should still be removed", accountList.getAccount("doubleRemove"));
    }

    // Remove account and ensure it’s not in the list
    @Test
    public void testRemovedAccountNotInList() {
        accountList.addAccount("removeMe", "pass", "Student");
        Account acc = accountList.getAccount("removeMe");
        accountList.removeAccount(acc);
        ArrayList<Account> accounts = accountList.getAccounts();
        for (Account a : accounts) {
            assertNotEquals("Removed account should not be in the list", "removeMe", a.getUsername());
        }
    }

    // Try to remove account not in list
    @Test
    public void testRemoveAccountNotInList() {
        Account ghost = new Student("notInList", "1234");
        accountList.removeAccount(ghost); // Should not throw error
        assertNull(accountList.getAccount("notInList"));
    }

    // ─────── Tests for getAccounts() ───────

    // getAccounts should return non-null list
    @Test
    public void testGetAccountsNotNull() {
        assertNotNull("Accounts list should not be null", accountList.getAccounts());
    }

    // getAccounts should include new account
    @Test
    public void testGetAccountsIncludesAdded() {
        accountList.addAccount("listedUser", "pass", "Teacher");
        boolean found = false;
        for (Account a : accountList.getAccounts()) {
            if (a.getUsername().equals("listedUser")) {
                found = true;
                break;
            }
        }
        assertTrue("New account should be in list", found);
    }

    // Test size of list increases after adding account
    @Test
    public void testGetAccountsListSizeIncreases() {
        int sizeBefore = accountList.getAccounts().size();
        accountList.addAccount("newSizeUser", "pass", "Student");
        int sizeAfter = accountList.getAccounts().size();
        assertEquals("List size should increase by 1", sizeBefore + 1, sizeAfter);
    }

    // Test that removed account disappears from getAccounts()
    @Test
    public void testRemovedAccountGoneFromList() {
        accountList.addAccount("removeListTest", "pass", "Teacher");
        Account acc = accountList.getAccount("removeListTest");
        accountList.removeAccount(acc);
        for (Account a : accountList.getAccounts()) {
            assertNotEquals("Removed account should not be in list", "removeListTest", a.getUsername());
        }
    }

    // Check for duplicate usernames in getAccounts (should never happen)
    @Test
    public void testNoDuplicateUsernamesInList() {
        ArrayList<Account> accounts = accountList.getAccounts();
        ArrayList<String> seen = new ArrayList<>();
        for (Account a : accounts) {
            assertFalse("Duplicate username found", seen.contains(a.getUsername()));
            seen.add(a.getUsername());
        }
    }

}