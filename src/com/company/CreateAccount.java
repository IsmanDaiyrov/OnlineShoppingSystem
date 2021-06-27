package com.company;

import java.util.Scanner;

class CreateAccount implements Customer {
    private String id;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String creditCardNumber;

    public CreateAccount(String id, String password, String name, String address, String phone, String ccno) {
        id = this.id;
        password = this.password;
        name = this.name;
        address = this.address;
        phone = this.phone;
        ccno = this.creditCardNumber;
    }

    public String getId() {
        return this.id;
    }

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
}