package view;

import controller.FileOutput;
import controller.RestaurantSerializator;
import model.MenuItem;
import model.Order;
import model.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 *
 * WaiterGUI class deals with creating the graphical user interface for the waiter
 * Actions that can be done: create order, print bill, view all orders
 */

public class WaiterGUI extends JFrame{

    private Restaurant restaurant;
    private ArrayList<Order> listOfOrders;
    private String name;

    public WaiterGUI(Restaurant restaurant, ArrayList<Order> listOfOrders, String name) {
        this.restaurant = restaurant;
        this.listOfOrders = listOfOrders;
        this.name = name;

        //JFrame waiterPage = new JFrame();
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        //waiterPage.setPreferredSize(new Dimension(595, 575));
        this.setBounds(500, 150, 750, 550);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.PINK);


        JLabel title = new JLabel("WAITER");
        title.setFont(new Font("Arial", Font.PLAIN, 38));
        title.setBounds(280, 60, 400, 100);
        getContentPane().add(title);

        JButton createButton = new JButton("Create Order");
        JButton billButton = new JButton("Compute bill");
        JButton viewButton = new JButton("View all orders");
        JButton backButton = new JButton("Back");

        createButton.setBounds(50, 200, 200, 71);
        billButton.setBounds(260, 200, 200, 71);
        viewButton.setBounds(470, 200, 200, 71);
        backButton.setBounds(570, 380, 100, 25);

        //// BACK BUTTON

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //// CREATE

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame createPage = new JFrame();
                createPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //createPage.setPreferredSize (new Dimension (452, 427));
                createPage.setBounds(500, 150, 750, 550);
                createPage.setLayout(null);
                createPage.getContentPane().setBackground(Color.PINK);

                JLabel titleLabel = new JLabel ("CREATE ORDER");
                titleLabel.setFont(new Font("Arial", Font.PLAIN, 38));

                String[] itemComboItems = restaurant.getItems();

                JButton addItemButton = new JButton ("Add Item");
                JButton createButton = new JButton ("Create");
                JLabel tableLabel = new JLabel ("Table No");
                JTextField tableText = new JTextField (5);
                JComboBox itemCombo = new JComboBox (itemComboItems);
                JLabel itemLabel = new JLabel ("Menu Item");
                JTextArea listArea = new JTextArea (30, 30);

                addItemButton.setBounds (135, 180, 100, 20);
                createButton.setBounds (40, 235, 195, 40);
                tableLabel.setBounds (40, 75, 125, 25);
                tableText.setBounds (135, 75, 100, 25);
                itemCombo.setBounds (135, 135, 100, 25);
                itemLabel.setBounds (40, 135, 100, 25);
                listArea.setBounds (305, 75, 150, 205);

                createPage.add(addItemButton);
                createPage.add(createButton);
                createPage.add(tableLabel);
                createPage.add(tableText);
                createPage.add(itemCombo);
                createPage.add(itemLabel);
                createPage.add(listArea);

                createPage.setVisible(true);


                Order newOrder = new Order(new ArrayList<MenuItem>());

                addItemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = getNameField(itemCombo);
                        listArea.append(itemName + "\n");
                        newOrder.addItemToOrder(find(itemName));
                    }
                });

                createButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newOrder.setTable(getTableNo(tableText));
                        listOfOrders.add(newOrder);
                        restaurant.createOrder(newOrder, newOrder.getListOfItems());
                        RestaurantSerializator.serialize(restaurant, name);
                        ChefGUI chef = new ChefGUI(restaurant, new UserGUI(restaurant, name));
                        Observable obs = new Observable();
                        chef.update(obs, "New order for the chef!");
                        createPage.setVisible(false);
                    }
                });
            }
        });

        //// BILL

        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame billPage = new JFrame();
                billPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //createPage.setPreferredSize (new Dimension (200, 200));
                billPage.setBounds(500, 150, 350, 250);
                billPage.setLayout(null);
                billPage.getContentPane().setBackground(Color.PINK);

                JButton printButton = new JButton ("Print bill");
                JLabel tableLabel = new JLabel("Table");
                JTextField tableText = new JTextField(5);

                tableLabel.setBounds (50, 50, 100, 20);
                tableText.setBounds (120, 50, 100, 20);
                printButton.setBounds (120, 120, 100, 20);

                billPage.add(tableLabel);
                billPage.add(tableText);
                billPage.add(printButton);

                billPage.setVisible(true);

                printButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Order newOrder = findOrder(getTableNo(tableText));
                        FileOutput fileOut = new FileOutput(restaurant);
                        fileOut.toPrint(newOrder, newOrder.getListOfItems());
                        JOptionPane.showMessageDialog(null, "Bill printed successfully!");
                        billPage.setVisible(false);

                    }
                });
            }
        });

        //// VIEW ORDERS

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame viewPage = new JFrame();
                viewPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                viewPage.setBounds(500, 150, 900, 550);
                viewPage.setLayout(null);
                viewPage.getContentPane().setBackground(Color.PINK);
                Map<Order, ArrayList<MenuItem>> myMap = restaurant.getMap();

                String[] collumns = { "Order ID", "Table", "Ordered items", "Total price"};

                DefaultTableModel myModel = new DefaultTableModel();
                myModel.setColumnIdentifiers(collumns);

                Object[] obj = new Object[4];
                for (Order curentOrder : listOfOrders) {
                    obj[0] = curentOrder.getOrderID();
                    obj[1] = curentOrder.getTable();
                    ArrayList<MenuItem> listM = myMap.get(curentOrder);
                    StringBuilder str = new StringBuilder();
                    double totalPrice = 0;
                    Iterator<MenuItem> itr = listM.iterator();
                    while (itr.hasNext()) {
                        MenuItem item = itr.next();
                        str.append(item.toString());
                        if (itr.hasNext())
                            str.append(", ");
                        totalPrice += item.getPrice();
                    }
                    obj[2] = str.toString();
                    obj[3] = totalPrice;
                    myModel.addRow(obj);
                }

                JTable myTable = new JTable(myModel);
                JScrollPane myScrollPane = new JScrollPane();
                myScrollPane.setBounds(50, 50, 800, 400);
                myScrollPane.setViewportView(myTable);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                viewPage.add(myScrollPane);
                viewPage.setVisible(true);

            }
        });

        getContentPane().add(createButton);
        getContentPane().add(billButton);
        getContentPane().add(viewButton);
        getContentPane().add(backButton);

    }

    public String getNameField(JComboBox combo) {
        return combo.getSelectedItem().toString();
    }

    public Order findOrder(int table) {
        Order myOrd = null;
        for(Order ord : listOfOrders) {
            if(ord.getTable() == table)
                myOrd = ord;
        }
        return myOrd;
    }

    public int getTableNo(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }

    public MenuItem find(String name) {
        ArrayList<MenuItem> items = restaurant.getMenu();
        for (MenuItem current : items) {
            if (current.getName().compareTo(name) == 0)
                return current;
        }
        return null;
    }

    public ArrayList<Order> getListOfOrders() {
        return listOfOrders;
    }

}
