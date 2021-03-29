package model;

import controller.IRestaurantProcessing;
import java.io.*;
import java.util.*;

public class Restaurant extends Observable implements IRestaurantProcessing, java.io.Serializable {

    private ArrayList<MenuItem> menu;
    private ArrayList<Order> ordersList;
    private Map<Order, ArrayList<MenuItem>> map;

    public Restaurant() {
        this.menu = new ArrayList<MenuItem>();
        this.ordersList = new ArrayList<Order>();
        this.map = new HashMap<Order, ArrayList<MenuItem>>();
    }

    public ArrayList<MenuItem> getMenu() {
        return this.menu;
    }

    public Map<Order, ArrayList<MenuItem>> getMap() {
        return map;
    }

    public void setMap(Map<Order, ArrayList<MenuItem>> map) {
        this.map = map;
    }

    public String[] getItems() {
        String[] listOfItems = new String[menu.size()];
        for(int i = 0; i < menu.size(); i++) {
            listOfItems[i] = menu.get(i).getName();
        }
        return listOfItems;
    }

    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }

    public void createMenuItem(MenuItem item) {
        assert item != null;
        menu.add(item);
    }

    public void deleteMenuItem(MenuItem item) {
        assert item != null;
        menu.remove(item);
    }

    public void editMenuItem(MenuItem item, String name, double price) {
        menu.remove(item);
        assert item != null;
        assert name != null;
        assert price != 0;
        MenuItem updatedItem = new MenuItem(name, price);
        menu.add(updatedItem);

    }

    public void createOrder(Order order, ArrayList<MenuItem> items) {
        assert order != null;
        assert items != null;
        map.put(order, items);
    }

    public double computePrice(Order order) {
        assert order != null;
        double price = 0;
        if (this.ordersList.contains(order)) {
            ArrayList<MenuItem> items = this.map.get(order);
            for (MenuItem item : items) {
                price += item.getPrice();
            }
        }
        return price;
    }

    public void printBill(String toPrint, Order order) {
        assert toPrint != null;
        assert order != null;
        String nameOfBill = "bill" + order.getOrderID() + ".txt";
        File outputFile = new File(nameOfBill);
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
            System.out.println(toPrint);
        }
        catch (Exception e) {
            System.out.println("NU");
        }
    }




}

