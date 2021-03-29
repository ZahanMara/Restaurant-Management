package controller;

import model.MenuItem;
import model.Order;
import model.Restaurant;

import java.util.ArrayList;

/**
 * @author Mara
 * This class prepare the text to be written on the bill
 * The bill contains the orderID, number of the table, the items that were ordered and total price
 */

public class FileOutput {

    Restaurant restaurant;

    public FileOutput(Restaurant r) {
        this.restaurant = r;
    }

    public void toPrint(Order order, ArrayList<MenuItem> list) {
        double total = 0;
        String toPrint = "";
        toPrint += "Order ID: " + order.getOrderID() + "\n";
        toPrint += "Table: " + order.getTable() + "\n";
        toPrint += "Ordered items:\n";
        for(MenuItem item : list) {
            toPrint += item + "\n";
            total += item.getPrice();
        }
        toPrint += "Total price: " + total;
        restaurant.printBill(toPrint, order);
    }
}
