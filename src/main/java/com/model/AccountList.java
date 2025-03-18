package com.model;

import java.util.ArrayList;

public class AccountList{
    private ArrayList<Account> accounts;
    private static AccountList accountList;

    private AccountList() {
        accounts = new ArrayList<Account>();
        accountList = this;
    }

    public static AccountList getInstance() {
        if (accountList == null) {
            accountList = new AccountList();
        }
        return accountList;
    }
    
    public Account getAccount(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}