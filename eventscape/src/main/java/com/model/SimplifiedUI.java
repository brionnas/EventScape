package com.model;

public class SimplifiedUI {
    public SimplifiedUI() {

    }

    public void run() {
        //scenario1();
        scenario2();
    }

    public void scenario1() {
        Facade facade = Facade.getInstance();

        User user = facade.login("pplante", "123password");

        if(user == null) {
            System.out.println("The user doesn't exist");
            return;
        }

        System.out.println("Successfully logged in");
        System.out.println(user);
    }

    public void scenario2(){
        Facade facade = Facade.getInstance();
        if(facade.addUser("amstih", "Amy", "Smith", "asmith@email.com", "203-445-4433",Utilities.toDate("2000-03-03") , "123")){
            System.out.println("User successfully added");

            facade.logout();

            User user = facade.login("amstih", "123");

            if(user == null) {
                System.out.println("The user doesn't exist");
                return;
            }

            System.out.println("Successfully logged in");
            System.out.println(user);
        } else {
            System.out.println("Sorry");
        }
    
        
    }

    public static void main(String[] args){
        (new SimplifiedUI()).run();
    }
    
}
