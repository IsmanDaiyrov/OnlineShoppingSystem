package com.company;

import java.text.NumberFormat;

public class items {

    private String names;
    private double price, my_bulk_cost;
    private int my_bulk;
    private NumberFormat my_formatted_numbers;

    public items(final String the_item_name, final double the_item_price) {
        this(the_item_name, the_item_price, 0, 0.0);
    }

    public items(final String the_item_name, final double the_item_price, final int the_quantity_in_bulk, final double the_price_in_bulk) {
        names = the_item_name;
        price = the_item_price;
        my_bulk = the_quantity_in_bulk;
        my_bulk_cost = the_price_in_bulk;
        my_formatted_numbers = NumberFormat.getCurrencyInstance();
    }

    public String toString() {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder().append(names);
        stringbuilder.append("\t");
        stringbuilder.append(my_formatted_numbers.format(price));
        return stringbuilder.toString();
    }

    public double priceFor(final int a_quantity) {

        final int quantity = a_quantity;
        double bulk_num;

        if (quantity >= my_bulk && my_bulk > 0) {
            final int bulk_quantity = quantity / my_bulk;
            final int reg_quantity = quantity % my_bulk;
            bulk_num = bulk_quantity * my_bulk_cost + reg_quantity * price;
        } else {
            bulk_num = quantity * price;
        }
        return bulk_num;
    }

    public boolean equals(final Object the_other) {

        final items check = (items) the_other;
        boolean temporary_checker;

        if (check.names != null && check.names.equals(names) && check.price == price && check.my_bulk == my_bulk && check.my_bulk_cost == my_bulk_cost) {
            temporary_checker = true;
        } else {
            temporary_checker = false;
        }

        return temporary_checker;

    }

    public int hashCode() {
        return names.hashCode();
    }
}