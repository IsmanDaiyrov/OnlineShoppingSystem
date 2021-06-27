package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cart {
    private static double DISCOUNT = 0.50;
    private boolean my_discount;
    private List<itemOrder> my_item_list = new ArrayList<itemOrder>();

    public cart() {
        my_discount = false;
    }

    public final void add(final itemOrder the_item_order) {

        for (int i = 0; i < my_item_list.size(); i++) {
            if (((itemOrder) my_item_list.get(i)).getItem().equals(the_item_order.getItem())) {
                my_item_list.set(i, the_item_order);
                return;
            }
        }

        my_item_list.add(the_item_order);

    }

    public void setDiscount(final boolean the_discount) {
        my_discount = the_discount;
    }

    public double getTotal() {

        double total = 0.0;

        for (final Iterator<itemOrder> iterator = my_item_list.iterator(); iterator.hasNext(); ) {
            final itemOrder next_item = (itemOrder) iterator.next();
            total = total + next_item.getPrice();
        }

        if (my_discount) {
            total = total * DISCOUNT;
        }
        return total;
    }

}