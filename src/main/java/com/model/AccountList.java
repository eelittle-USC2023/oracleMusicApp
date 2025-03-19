package com.model;
import java.util.ArrayList;

<<<<<<< HEAD
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
=======
public class AccountList {
private ArrayList<Account> accounts;

public AccountList(){
    this.accounts = new ArrayList<>();
>>>>>>> 101a54ae6f8407bfdb3d122eafb061ced23efb8a
}
public void addAccount(Account account){
     accounts.add(account);
}
public void removeAccount(Account account){
    accounts.remove(account);
}
public Account accountByUsername(String username){
    for (Account account : accounts){
        if (account.getUsername().equals(username)){
            return account;
        }
    }
    return null;
}
public ArrayList<Account> getAccounts(){
    return accounts;
}
@Override
public String toString(){
    return "active Accounts:" + accounts.size();
}
}