package com.company;

import java.util.Scanner;

// This class provides a user interface for all
// customer actions with an account.

public class CustomerActions {
    static Scanner scanner = new Scanner(System.in);
    static AccountFileAccess accountFile;

    public static void getAccount() {
        accountFile = new AccountFileAccess();
        System.out.println("----------Customer Menu----------");
        System.out.println("Enter an account number, or 0 to exit.");

        String accountNum = scanner.nextLine();
        if (accountNum.equals("0")) return;
        accountMenu(accountFile.getAccount(accountNum));
    }

    public static void accountMenu(Account account) {
        if (account == null) {
            System.out.println("Account not found!");
            getAccount();
            return;
        }

        System.out.println("----------Account Menu----------");
        System.out.println("Choose an operation.");
        System.out.println("1: Deposit Funds");
        System.out.println("2: Withdraw Funds");
        System.out.println("3: View Balance");
        System.out.println("4: Transfer Funds");
        System.out.println("5: View last deposit amount");
        System.out.println("6: Cancel");
        System.out.println("Press any other key to return to the main menu.");

        String choice = scanner.nextLine();
        switch (choice) {
            case ("1"):
                depositFunds(account);
                break;
            case ("2"):
                withdrawFunds(account);
                break;
            case ("3"):
                viewBalance(account);
                break;
            case ("4"):
                transferFunds(account);
                break;
            case ("5"):
                viewLastDeposit(account);
                break;
            case ("6"):
                getAccount();
            default:
                //goto main menu
        }
    }

    private static void depositFunds(Account account) {
        if (!validatePin(account)) {
            return;
        }
        System.out.println("----------Deposit Funds----------");
        System.out.println("Enter amount to deposit: ");
        String amount = scanner.nextLine();

        account.deposit(Double.parseDouble(amount));
        System.out.printf("New balance after deposit: %2f\n", account.getBalance());

        accountFile.updateAccount(account);
        accountMenu(account);
    }

    private static void viewLastDeposit(Account account) {
        System.out.println("----------View Last Deposit----------");
        System.out.printf("Account last deposit: $%2f\n", account.getLastDepositAmount());

        accountMenu(account);
    }

    private static void transferFunds(Account account) {
        if (!validatePin(account)) {
            return;
        }
        System.out.println("----------Transfer Funds----------");
        System.out.println("Enter transfer account: ");
        String transferAccountNum = scanner.nextLine();
        Account transferAccount = accountFile.getAccount(transferAccountNum);
        if (transferAccount == null) {
            System.out.println("Account not found!");
            accountMenu(account);
            return;
        }

        System.out.println("Enter amount to deposit: ");
        String amount = scanner.nextLine();

        if (Double.parseDouble(amount) <= account.getBalance()) {
            account.transfer(transferAccount, Double.parseDouble(amount));
            accountFile.updateAccount(account);
            accountFile.updateAccount(transferAccount);
            System.out.printf("New balance after transfer: %2f\n", account.getBalance());
        } else {
            System.out.println("Insufficient funds for transfer.");
        }

        accountMenu(account);
    }

    private static void viewBalance(Account account) {
        if (!validatePin(account)) {
            return;
        }
        System.out.println("----------View Balance----------");
        System.out.printf("Account balance: $%2f\n", account.getBalance());

        accountMenu(account);
    }

    private static void withdrawFunds(Account account) {
        if (!validatePin(account)) {
            return;
        }
        System.out.println("----------Withdraw Funds----------");
        System.out.println("Enter amount to withdraw: ");
        String amount = scanner.nextLine();

        if (Double.parseDouble(amount) <= account.getBalance()) {
            account.withdraw(Double.parseDouble(amount));
            accountFile.updateAccount(account);
            System.out.printf("New balance after withdrawal: %2f\n", account.getBalance());
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }

        accountMenu(account);
    }

    private static boolean validatePin(Account account) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter PIN.");
            String pin = scanner.nextLine();
            if (account.checkPin(pin)) {
                return true;
            } else {
                System.out.println("Incorrect pin entered.");
            }
        }
        System.out.println("Incorrect pin entered too many times. Returning to main menu.");
        return false;
    }
}
