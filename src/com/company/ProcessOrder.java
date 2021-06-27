package com.company;

import java.util.Scanner;

public class ProcessOrder {
    // Fields
    private String items;
    private String orderStatus = "ViewInvoice status: Ordered.";
    private static int numOfItems;
    private static int numOfReservedItems;
    private static boolean isInStock = true;

    // Constructor
    public ProcessOrder(String items, String orderStatus, int numOfItems, int numOfReservedItems) {
        this.items = items;
        this.orderStatus = orderStatus;
        ProcessOrder.numOfItems = numOfItems;
        ProcessOrder.numOfReservedItems = numOfReservedItems;
    }

    // Setters and Getters
    public void setItems(String items) {
        this.items = items;
    }

    public static void setOrderStatus(String orderStatus) {
    }

    public void setNumOfItems(int numOfItems) {
        ProcessOrder.numOfItems = numOfItems;
    }

    public void setNumOfReservedItems(int numOfReservedItems) {
        ProcessOrder.numOfReservedItems = numOfReservedItems;
    }

    public String getItems() {
        return items;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public int getNumOfReservedItems() {
        return numOfReservedItems;
    }

    public static void processOrder() {
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
                checkAvailability();
                ChargeAmount.chargeAmount();
            } else if (userOrderInput.equals("2")) {
                checkAvailability();
                ChargeAmount.chargeAmount();
            } else if (userOrderInput.equals("3")) {
                checkAvailability();
                ChargeAmount.chargeAmount();
            } else if (userOrderInput.equals("4")) {
                checkAvailability();
                ChargeAmount.chargeAmount();
            } else {
                System.out.println("Invalid input, please try again.");
                processOrder();
            }
        }
    }

    public static void checkAvailability() {
        if (isInStock) {
            setOrderStatus("ViewInvoice status: Ready.");
            System.out.println("The order status changed to - READY.");
            System.out.println("The items are now reserved.");

            numOfItems--;
            numOfReservedItems++;
            int totalNumOfItems = numOfItems + numOfReservedItems;

            System.out.println("The total amount of items in stock is: " + totalNumOfItems);
        } else {
            System.out.println("Sorry, the items are out of stock and needs to be refilled. Please try again later.");
        }
    }
}
