package controller;

import java.util.ArrayList;
import model.MenuItem;
import model.Order;

public interface IRestaurantProcessing {

    /**
     * @param  item = MenuItem to be added to the menu list
     * item != null
     *
     */
    public void createMenuItem(MenuItem item);

    /**
     * @param item = MenuItem to be deleted to the menu list
     * item != null
     *
     */
    public void deleteMenuItem(MenuItem item);

    /**
     * @param item = MenuItem to be deleted to the menu list
     *                   and replaced with the new one
     *                   defined by MenuItem(name, price)
     * item != null
     *
     * @param name = new name of the "edited" product
     *  name != null
     *
     * @param price = new price of the "edited" product
     *  price != 0
     *
     */
    public void editMenuItem(MenuItem item, String name, double price);



    /**
     *
     * @param order = the order made by the client
     * @param menuItem = the list of menu items ordered by the client
     *
     *  order != null
     *  menuItem != null
     */
    public void createOrder(Order order, ArrayList<MenuItem> menuItem);


    /**
     * @param order = the order which the total price is meant to be computem
     *  order != null
     */
    public double computePrice(Order order);


    /**
     *
     * @param whatToPrint = text to be printed on the bill
     *  whatToPrint != null
     *
     * @param ord = the order for which the bill is meant to be printed
     *  ord != null
     */
    public void printBill(String whatToPrint, Order ord);
}