package com.model;


public class Driver {
    public Driver() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
    }
    
    public static void main(String[] args) {
        /*
        boolean successful = facade.createAccount("mozartrella", "b35tPun", "Student");
        successful = facade.createAccount("Briethoven", "2ndB35tPun", "Student");

        createAccountScenario(facade);
        makeSongScenario(facade);
        */
        
    }
    
    private static void createAccountScenario(OracleMusicAppFacade facade) {
        for (Account account: facade.getAccounts()){
            System.out.println(account.getUsername());
        }
        boolean accountCreated = facade.createAccount("ffredickson", "Password123", "Student");
        if (!accountCreated){
            System.out.println("Account creation failed, this username is already taken");
        }
        accountCreated = facade.createAccount("ffred", "GreatPassword123", "Student");
        if (accountCreated){
            System.out.println("Account was created successfully!");
        }
        facade.logout();
        DataWriter.savedAccounts(AccountList.getInstance());
        boolean loginSuccessful = facade.login("ffred", "Greatpassword123");
        if (loginSuccessful){
            System.out.println("Your login was successful! Welcome, Fred");
            }else {
                System.out.println("Login failed");
        }
        facade.logout();
}

}
    private static void makeSongScenario() {
        System.out.println("\n Logged in as Fellica Fredrickson");
        if(!facade.login("FellicaFredrickson, pword")){
            System.out.println("login unsuccessful");
            return;
        }
    }

        Instrument guitar = new Guitar();
        Song newSong = new Song("A Horses Journey", " Fellica Fredickson",guitar, new ArrayList<>());

    
 private static void playSongScenario() {

    }
}
