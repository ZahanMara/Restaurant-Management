package model;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Order class provides the structure for the command placed by a client
 * An Order object has as attributes "orderID"(which is unique),
 * "table"(which is the number of the table where client stay)
 * and "listOfItems"(the list of ordered items desired by the client)
 */

public class Order implements java.io.Serializable {

    private int OrderID;
    private int table;
    private ArrayList<MenuItem> listOfItems;

    public Order(ArrayList<MenuItem> listOfItems) {
        this.OrderID = IDGenerator.getId();
        this.listOfItems = listOfItems;
    }

    public Order(int table, ArrayList<MenuItem> listOfItems)
    {
        this.OrderID = IDGenerator.getId();
        this.table = table;
        this.listOfItems = listOfItems;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setListOfItems(ArrayList<MenuItem> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getTable() {
        return table;
    }

    public ArrayList<MenuItem> getListOfItems() {
        return listOfItems;
    }

    public void addItemToOrder(MenuItem item) {
        listOfItems.add(item);
    }

    public int hashCode() {
        return (this.OrderID % 5 + this.table) % 5;
    }

}