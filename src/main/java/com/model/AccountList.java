package com.model;
import java.util.ArrayList;

public class AccountList 
{
    private ArrayList<Song> songs;
    private static SongList songList;

    private AccountList()
    {

    }
    public static AccountList getInstance()
    {
        AccountList temp = new AccountList();
        return temp;
    }
    public Account getAccount()
    {
        Account temp = new Account("username", "password", "role");
        return temp;
    }
    public boolean addAccount(String username, String password, String role)
    {
        return true;
    }
    public boolean editAccount(Account account)
    {
        return true;
    }
    public boolean deleteAccount(Account account)
    {
        return true;
    }
    public void save()
    {
        
    }
}
