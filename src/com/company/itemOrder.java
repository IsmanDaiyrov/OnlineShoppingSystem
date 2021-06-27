package com.company;

public class itemOrder {

  private items my_item;
  private int my_quantity;
  
  public itemOrder(final items the_item_name, final int the_quantity_of_items) {
    my_item = the_item_name;
    my_quantity = the_quantity_of_items;
  }

  public double getPrice() {
    return my_item.priceFor(my_quantity);
  }

  public items getItem() {
    return my_item;
  }

}
