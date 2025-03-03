package com.model;
import java.util.ArrayList;

public class AccountList {
private ArrayList<Account> accounts;

public AccountList(){
    this.accounts = new ArrayList<>();
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