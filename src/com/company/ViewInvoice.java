package com.company;

import java.util.Scanner;

public class ViewInvoice {
    private static int invoice;
    private static String orderDate;
    private static int items;
    private static int totalAmount;
    private static String paymentInf;

    public ViewInvoice(int invoice, String orderDate, int items, int totalAmount, String paymentInf) {
        ViewInvoice.invoice = invoice;
        ViewInvoice.orderDate = orderDate;
        ViewInvoice.items = items;
        ViewInvoice.totalAmount = totalAmount;
        ViewInvoice.paymentInf = paymentInf;
    }

    public static void requestOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------Order Menu----------");
        System.out.println("Type 1 to process an order, or 0 to exit.");

        String userInput = scanner.nextLine();
        if (userInput.equals("0")) {
            return;
        } else {
            System.out.println("Your orders are listed below: ... ");
            System.out.println("1: Order 1");
            System.out.println("2: Order 2");
            System.out.println("3: Order 3");
            System.out.println("4: Order 4");
            System.out.println("Please select an order from order list by entering 1, 2, 3 or 4. ");

            String userOrderInput = scanner.nextLine();
            if (userOrderInput.equals("1")) {
                System.out.println("Invoice: " + getInvoice());
                System.out.println("Order date: " + getOrderDate());
                System.out.println("Items: " + getItems());
                System.out.println("Total amount: " + getTotalAmount());
                System.out.println("Payment information: " + getPaymentInf());
            } else if (userOrderInput.equals("2")) {
                System.out.println("Invoice: " + getInvoice());
                System.out.println("Order date: " + getOrderDate());
                System.out.println("Items: " + getItems());
                System.out.println("Total amount: " + getTotalAmount());
                System.out.println("Payment information: " + getPaymentInf());
            } else if (userOrderInput.equals("3")) {
                System.out.println("Invoice: " + getInvoice());
                System.out.println("Order date: " + getOrderDate());
                System.out.println("Items: " + getItems());
                System.out.println("Total amount: " + getTotalAmount());
                System.out.println("Payment information: " + getPaymentInf());
            } else if (userOrderInput.equals("4")) {
                System.out.println("Invoice: " + getInvoice());
                System.out.println("Order date: " + getOrderDate());
                System.out.println("Items: " + getItems());
                System.out.println("Total amount: " + getTotalAmount());
                System.out.println("Payment information: " + getPaymentInf());
            } else {
                System.out.println("Invalid input, please try again.");
                requestOrder();
            }
        }
    }

    public static int getInvoice() {
        return invoice;
    }

    public static String getOrderDate() {
        return orderDate;
    }

    public static int getItems() {
        return items;
    }

    public static int getTotalAmount() {
        return totalAmount;
    }

    public static String getPaymentInf() {
        return paymentInf;
    }
}
