package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

interface Customer {
    void setID();
    void setPassword();
}

public class Main {

    private static final items[] ITEMS = new items[] {
            new items("Apples", 0.50),
            new items("Oranges", 0.50),
            new items("Pineapples", 1.99),
            new items("Strawberrys", 1.49),
            new items("Blueberrys", 1.49),
            new items("Popcorn", 2.00),
            new items("Doritos", 1.99),
            new items("Kit Kat", 1.49),
            new items("Peanut Butter", 3.00),
            new items("Milk", 2.99),
            new items("Frozen Pizza", 5.99)
    };

    public static void main(String[] args) {
        new frame(Arrays.asList(ITEMS));

        ArrayList<AccountType> accountDB = new ArrayList<AccountType>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your ID: ");
        String id = sc.nextLine();

        boolean flag = false;
        while(true){
            flag = false;
            for(AccountType a: accountDB){
                if(a.getCustomer().getId().equals(id)){
                    System.out.println("ID already exists, please enter a different ID: ");
                    id = sc.nextLine();
                    flag = true;
                    break;
                }
            }

            if(!flag)
                break;
        }

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        System.out.print("Enter your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter your Address: ");
        String address = sc.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();

        System.out.print("Enter your Credit Card Number: ");
        String creditCardNumber = sc.nextLine();

        CreateAccount account = new CreateAccount(id, password, name, address, phone, creditCardNumber);

        int acType = -1;
        System.out.print("Choose your account type...");
        System.out.print("Enter 1 for Regular OR 2 for Premium: ");
        acType = sc.nextInt();

        while(acType != 1 && acType != 2){
            System.out.println("Invalid input. Please try again: ");
            acType = sc.nextInt();
        }

        accountDB.add(new AccountType(account, acType));

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select the program to run, or enter 0 to exit.");
            System.out.println("1: Bank Staff");
            System.out.println("2: Customer");
            System.out.println("3: Request Order");
            System.out.println("4: Process Order");
            System.out.println("0: Exit");

            String choice = scanner.nextLine();
            switch(choice){
                case("0"):
                    return;
                case("1"):
                    BankStaffActions.mainMenu();
                    break;
                case("2"):
                    CustomerActions.getAccount();
                    break;
                case("3"):
                    ViewInvoice.requestOrder();
                    break;
                case("4"):
                    ProcessOrder.processOrder();
                    break;
                default:
                    //go to main menu
            }
        }
    }
}
