package com.company;

import java.util.Scanner;

class LogOut implements Customer{
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

    public void logout() {
        System.out.println("You have logged out.");
    }
}
