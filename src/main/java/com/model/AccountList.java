package com.model;

import java.util.ArrayList;

public class AccountList{
    private ArrayList<Account> accounts;
    private static AccountList accountList;

    /**
     * Private constructor used when getInstance is called for the first time. Gets the accounts from the DataLoader.
     * @author Ethan Little
     */
    private AccountList() {
        accounts = DataLoader.getAccounts();
    }
    /**
     * GetInstance method to maintain Singleton design. 
     * Constructs the current instance of AccountList if it does not already exist, then returns the instance.
     * @return The current instance of AccountList
     * @author Ethan Little
     */
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
    public boolean addAccount(String username, String password, String role){
        for (Account a : accounts) {
            if(a.getUsername().equals(username)) {
                return false;
            }
        }
        Account newAccount;
        if(role.equalsIgnoreCase("Student")){
            newAccount = new Student (username, password);
        } else {
            newAccount = new Teacher (username, password);
        }
        accounts.add(newAccount);
        DataWriter.savedAccounts(this);
        return true;
   }
    public void removeAccount(Account account){
       accounts.remove(account);
    }
   
    public ArrayList<Account> getAccounts(){
       return accounts;
    }
    public void save() {
        DataWriter.savedAccounts(this);
    }

   @Override
   public String toString(){
       return "active Accounts:" + accounts.size();
   }
}