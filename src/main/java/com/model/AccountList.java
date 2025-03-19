package com.model;
import java.util.ArrayList;

public class AccountList 
{
    private AccountList() {

    }

    public static AccountList getInstance() {
        return accountList;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(String username) {
        return null;
    }

    public boolean addAccount(Account account) {
        return true;
    }

    public boolean editAccount(Account account) {
        return true;
    }

    public boolean deleteAccount(Account account) {
        return accounts.remove(account);
    }

    public void save() {
        
    }
}
