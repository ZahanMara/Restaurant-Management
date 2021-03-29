package view;

import model.MenuItem;
import model.Order;
import model.Restaurant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * ChefGUI class deals with creating the graphical user interface for chef
 * Action that can be done: view all the orders given to be prepared
 * A notification will appear every time a new order is placed in order to warn the chef about it
 */

public class ChefGUI extends JFrame implements Observer {

    private Restaurant restaurant;
    private UserGUI user;

    public ChefGUI(Restaurant restaurant, UserGUI user) {

        this.restaurant = restaurant;
        this.user = user;

        restaurant.addObserver(this);
        Map<Order, ArrayList<MenuItem>> myMap = restaurant.getMap();

        //JFrame chefPage = new JFrame();

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.PINK);

        JLabel titleLabel = new JLabel("CHEF");
        JButton backButton = new JButton("Back");

        titleLabel.setFont(new Font("Arial", Font.PLAIN, 38));
        titleLabel.setBounds(400, 50, 450, 50);
        backButton.setBounds(750, 550, 100, 50);

        String[] collumns = { "Order ID", "Table", "Ordered items" };

        DefaultTableModel myModel = new DefaultTableModel();
        myModel.setColumnIdentifiers(collumns);

        Object[] obj = new Object[3];
        for (Order curentOrder : user.getListOfOrders()) {
            obj[0] = curentOrder.getOrderID();
            obj[1] = curentOrder.getTable();
            ArrayList<MenuItem> listM = myMap.get(curentOrder);
            StringBuilder str = new StringBuilder();
            Iterator<MenuItem> itr = listM.iterator();
            while (itr.hasNext()) {
                MenuItem item = itr.next();
                str.append(item.getName());
                if (itr.hasNext())
                    str.append(", ");
            }
            obj[2] = str.toString();
            myModel.addRow(obj);
        }

        JTable myTable = new JTable(myModel);
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(50, 100, 800, 400);
        myScrollPane.setViewportView(myTable);
        myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        getContentPane().add(titleLabel);
        getContentPane().add(myScrollPane);
        getContentPane().add(backButton);
    }

    @Override
    public void update(Observable o, Object arg) {
        //this.setVisible(true);
        JOptionPane.showConfirmDialog(null, arg, "NOTIFICATION", 2);

    }
}