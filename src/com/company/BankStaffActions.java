package com.company;

import java.util.Scanner;

// This class provides a user interface for all
// bank staff actions with an account.

public class BankStaffActions {
	static Scanner scanner = new Scanner(System.in);
	static AccountFileAccess accountFile;
	
	public static void mainMenu(){
		accountFile = new AccountFileAccess();
		System.out.println("----------Bank Staff Menu----------");
		System.out.println("Choose an operation.");
		System.out.println("1: Open Account");
		System.out.println("2: Close an Account");
		System.out.println("Press any other key to return to the main menu.");
		
		String choice = scanner.nextLine();
		switch(choice){
		case("1"):
			openAccount();
			break;
		case("2"):
			closeAccount();
			break;
		default:
			//goto main menu
		}
	}
	
	private static void closeAccount() {
		System.out.println("----------Close Account----------");
		System.out.println("Enter account number: ");
		String accountNum = scanner.nextLine();
		
		accountFile.deleteAccountFromFile(accountNum);
		System.out.println("Account deleted.");
		mainMenu();
	}

	public static void openAccount(){
		String choice = "";
		
		while(!(choice.equals("1") || choice.equals("2"))){
			System.out.println("----------Open Account----------");
			System.out.println("Choose an account type.");
			System.out.println("1: Savings");
			System.out.println("2: Checking");
			
			choice = scanner.nextLine();
			
			switch(choice){
			case("1"):
				addSavingsAccount();
				break;
			case("2"):
				addCheckingAccount();
				break;
			default:
				System.out.println("Invalid account type");
			}
		}
	}
	
	private static void addSavingsAccount(){
		System.out.println("Enter customer SSN.");
		String ssn = scanner.nextLine();
		System.out.println("Enter customer pin.");
		String pin = scanner.nextLine();
		
		Account account;
		
		try {
			account = new SavingsAccount(pin, ssn, accountFile.getNextSavingsAccountNumber() , 0, 0);
			accountFile.addAccount(account);
		} catch (pinException e) {
			System.out.println("Invalid pin. please try again with a pin with 4 digits.");
			addSavingsAccount();
			return;
		} catch (ssnException e) {
			System.out.println("Invalid SSN. please try again with a SSN with 9 digits.");
			addSavingsAccount();
			return;
		}
		
		System.out.println("New account created! Account number: " + account.getAccountNumber());
		mainMenu();
	}
	
	private static void addCheckingAccount(){
		System.out.println("Enter customer SSN.");
		String ssn = scanner.nextLine();
		System.out.println("Enter customer pin.");
		String pin = scanner.nextLine();
		
		Account account;
		
		try {
			account = new CheckingAccount(pin, ssn, accountFile.getNextCheckingAccountNumber() , 0, 0);
			accountFile.addAccount(account);
		} catch (pinException e) {
			System.out.println("Invalid pin. please try again with a pin with 4 digits.");
			addCheckingAccount();
			return;
		} catch (ssnException e) {
			System.out.println("Invalid SSN. please try again with a SSN with 9 digits.");
			addCheckingAccount();
			return;
		}
		
		System.out.println("New account created! Account number: " + account.getAccountNumber());
		mainMenu();
	}
}
