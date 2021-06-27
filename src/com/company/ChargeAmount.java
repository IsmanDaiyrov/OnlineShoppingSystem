package com.company;

public class ChargeAmount {
    // Fields
    private static int authNumber;
    private static int amountToBeCharged;
    private static int creditCardBalance;
    private static boolean isValid = true;

    // Getters and Setters
    public static int getAuthNumber() {
        return authNumber;
    }

    public static void setAuthNumber(int authNumber) {
        ChargeAmount.authNumber = authNumber;
    }

    public int getAmountToBeCharged() {
        return amountToBeCharged;
    }

    public void setAmountToBeCharged(int amountToBeCharged) {
        ChargeAmount.amountToBeCharged = amountToBeCharged;
    }

    public int getCreditCardBalance() {
        return creditCardBalance;
    }

    public void setCreditCardBalance(int creditCardBalance) {
        ChargeAmount.creditCardBalance = creditCardBalance;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public static void chargeAmount() {
        creditCardBalance = creditCardBalance - amountToBeCharged;
        System.out.println("Verifying your credit card...");
        verifyCreditCard();
        System.out.println("Payment was processed successfully!");
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void verifyCreditCard() {
        if (isValid) {
            setAuthNumber(getRandomNumber(100000, 999999));
            System.out.println("Your credit card is valid.");
            System.out.println("The authorization number is: " + getAuthNumber());
        } else {
            System.out.println("Your credit card is invalid.");
        }
    }
}