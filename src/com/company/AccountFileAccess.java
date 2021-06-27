package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This class provides an interface between the 
// Accounts file and the program.
// All account data is saved in a plain .txt file.

public class AccountFileAccess {
    String fileName = "src/Accounts.txt";

    List<Account> accounts;
    // This class maintains a copy of all account objects
    // to avoid reading and writing from the file too much

    AccountFileAccess() {
        String line = null;
        accounts = new ArrayList<Account>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    accounts.add(toAccount(line));
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
        }
    }

    public Account getAccount(String accountNum) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accountNum.equals(accounts.get(i).getAccountNumber())) {
                return accounts.get(i);
            }
        }
        return null;
    }

    //rewrite the .txt file to reflect changes to account data
    public void updateAccount(Account account) {
        File inputFile = new File(fileName);
        File tempFile = new File("src/tempAccounts.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith(account.getAccountNumber())) {
                    writer.write(account.toString());
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }
            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(account.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '" + fileName + "'");
        }
    }

    // converts a line of data in the .txt to an account object
    // with the corresponding data.
    private Account toAccount(String accountString) {
        Account acct = null;

        String[] strings = accountString.split(" ");

        String acctNum = strings[0];
        String acctPin = strings[1];
        String acctSSN = strings[2];
        String acctBalance = strings[3];
        String lastDeposit = strings[4];

        if (acctNum.startsWith("0")) {
            try {
                acct = new CheckingAccount(acctPin, acctSSN, acctNum, Double.parseDouble(acctBalance), Double.parseDouble(lastDeposit));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                acct = new SavingsAccount(acctPin, acctSSN, acctNum, Double.parseDouble(acctBalance), Double.parseDouble(lastDeposit));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acct;
    }

    //Get the next sequential checking account number for account creation
    public String getNextCheckingAccountNumber() {
        int maxAcctNum = 0;
        for (int i = 0; i < accounts.size(); i++) {
            String acctNum = accounts.get(i).getAccountNumber();
            if (acctNum.startsWith("0") && Integer.parseInt(acctNum.substring(1)) > maxAcctNum) {
                maxAcctNum = Integer.parseInt(accounts.get(i).getAccountNumber().substring(1));
            }
        }
        return "0" + String.format("%04d", maxAcctNum + 1);
    }

    //Get the next sequential savings account number for account creation
    public String getNextSavingsAccountNumber() {
        int maxAcctNum = 0;
        for (int i = 0; i < accounts.size(); i++) {
            String acctNum = accounts.get(i).getAccountNumber();
            if (acctNum.startsWith("1") && Integer.parseInt(acctNum.substring(1)) > maxAcctNum) {
                maxAcctNum = Integer.parseInt(accounts.get(i).getAccountNumber().substring(1));
            }
        }
        return "1" + String.format("%04d", maxAcctNum + 1);
    }

    //This deletes the account object in the array and in the file
    public void deleteAccountFromFile(String accountNum) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountNum)) {
                accounts.remove(i);
            }
        }

        File inputFile = new File(fileName);
        File tempFile = new File("src/tempAccounts.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith(accountNum)) continue;
                writer.write(currentLine);
                writer.newLine();
            }

            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }
}
