package com.company;

public class SavingsAccount extends Account {
	
	public SavingsAccount(String pin, String ssn, String accountNumber, double balance, double lastDepositAmt) throws pinException, ssnException{
		super(pin, ssn, accountNumber, balance, lastDepositAmt);
	}

}
