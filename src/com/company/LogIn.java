package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class LogIn implements Customer{
    String id;
    String password;

    @Override
    public void setID() {
        Scanner scan = new Scanner(System.in);
        id = scan.nextLine();
    }

    @Override
    public void setPassword() {
        Scanner scan = new Scanner(System.in);
        password = scan.nextLine();
    }

    Scanner scan = new Scanner(System.in);

    public void login() throws FileNotFoundException {
        File myfile = new File("login.txt");
        Scanner input = new Scanner(myfile);
        String read = null;

        while((read = input.nextLine()) != null) {
            String testid = input.nextLine();
            if(testid.equals(id)) {
                String testpassword = input.nextLine();
                if(testpassword.equals(password)) {
                    System.out.println("Welcome!");
                }
            }
            System.out.println("There is no such account.");
        }
    }
}
