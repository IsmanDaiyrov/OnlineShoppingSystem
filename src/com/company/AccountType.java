package com.company;

class AccountType {
    private CreateAccount c;
    private String accountType;
    private int feePending;

    public AccountType(CreateAccount c, int acType) {
        this.c = c;

        if (acType == 1) {
            this.accountType = "Regular AccountType";
            this.feePending = 0;
        } else if (acType == 2) {
            this.accountType = "Premium AccountType";
            this.feePending = 40;

            System.out.println("There is $40 pending fee for Premium AccountType which will be deducted during the first purchase");
        }

        System.out.println("AccountType created!");
    }

    public CreateAccount getCustomer() {
        return c;
    }
}
