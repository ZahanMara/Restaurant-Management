package view;

import model.MenuItem;
import controller.*;
import model.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * AdminGUI class deals with creating the graphical user interface for the admin
 * Actions that can be done: create item, edit item, remove item, view menu
 */

public class AdminGUI extends JFrame {

    private Restaurant restaurant;
    private String name;

    public AdminGUI(Restaurant restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;

        //JFrame adminPage = new JFrame();
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setBounds(500, 150, 650, 600);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.PINK);

        JLabel title = new JLabel("ADMIN");
        title.setFont(new Font("Arial", Font.PLAIN, 38));
        title.setBounds (240, 60, 300, 100);
        getContentPane().add(title);

        JButton addButton = new JButton("Add Menu Item");
        JButton removeButton = new JButton("Remove Menu Item");
        JButton editButton = new JButton("Edit Menu Item");
        JButton viewButton = new JButton("View Menu");
        JButton backButton = new JButton("Back");

        addButton.setBounds (70, 200, 200, 71);
        removeButton.setBounds (330, 200, 200, 71);
        editButton.setBounds (70, 300, 200, 71);
        viewButton.setBounds (330, 300, 200, 71);
        backButton.setBounds (430, 480, 100, 25);


        //// BACK BUTTON

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //// ADD

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addPage = new JFrame();
                addPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //addPage.setPreferredSize (new Dimension (452, 427));
                addPage.setBounds(500, 150, 500, 450);
                addPage.setLayout(null);
                addPage.getContentPane().setBackground(Color.PINK);

                JLabel titleLabel = new JLabel ("ADD MENU ITEM");
                titleLabel.setFont(new Font("Arial", Font.PLAIN, 35));

                JLabel nameLabel = new JLabel ("Name of the item");
                JLabel priceLabel = new JLabel ("Price of the item");
                JButton addedButton = new JButton ("Add");
                JTextField nameText = new JTextField();
                JTextField priceText = new JTextField();

                addedButton.setBounds (50, 300, 100, 20);
                nameLabel.setBounds (50, 120, 100, 25);
                priceLabel.setBounds (50, 200, 100, 25);
                nameText.setBounds (50, 150, 200, 25);
                priceText.setBounds (50, 230, 200, 25);
                titleLabel.setBounds (50, 45, 300, 30);

                addedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = getNameField(nameText);
                        String itemPrice = getPriceField(priceText);
                        double price = Double.parseDouble(itemPrice);

                        MenuItem newItem = new MenuItem(itemName, price);
                        restaurant.createMenuItem(newItem);
                        RestaurantSerializator.serialize(restaurant, name);
                        JOptionPane.showMessageDialog(null, "Item added successfully!");
                        addPage.setVisible(false);
                    }
                });
                addPage.add(addedButton);
                addPage.add(nameLabel);
                addPage.add(priceLabel);
                addPage.add(nameText);
                addPage.add(priceText);
                addPage.add(titleLabel);
                addPage.setVisible(true);
            }
        });

        //// REMOVE

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame removePage = new JFrame();
                removePage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //removePage.setPreferredSize (new Dimension (452, 427));
                removePage.setBounds(500, 150, 500, 450);
                removePage.setLayout(null);
                removePage.getContentPane().setBackground(Color.PINK);

                JLabel titleLabel = new JLabel ("REMOVE MENU ITEM");
                titleLabel.setFont(new Font("Arial", Font.PLAIN, 35));

                JLabel nameLabel = new JLabel ("Name of the item");
                JButton removedButton = new JButton ("Remove");
                JTextField nameText = new JTextField();

                removedButton.setBounds (50, 250, 100, 20);
                nameLabel.setBounds (50, 150, 100, 25);
                nameText.setBounds (50, 180, 200, 25);
                titleLabel.setBounds (50, 45, 400, 25);

                removedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = getNameField(nameText);

                        MenuItem itemToDelete = find(itemName);
                        restaurant.deleteMenuItem(itemToDelete);
                        RestaurantSerializator.serialize(restaurant, name);
                        JOptionPane.showMessageDialog(null, "Successfully deleted the item!");
                        removePage.setVisible(false);
                    }
                });

                removePage.add(removedButton);
                removePage.add(nameLabel);
                removePage.add(nameText);
                removePage.add(titleLabel);
                removePage.setVisible(true);
            }
        });

        //// EDIT

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editPage = new JFrame();
                editPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //editPage.setPreferredSize (new Dimension (452, 427));
                editPage.setBounds(500, 150, 500, 550);
                editPage.setLayout(null);
                editPage.getContentPane().setBackground(Color.PINK);

                JLabel titleLabel = new JLabel ("EDIT MENU ITEM");
                titleLabel.setFont(new Font("Arial", Font.PLAIN, 35));

                JLabel nameLabel = new JLabel ("Name of the item");
                JLabel newNameLabel = new JLabel("New name");
                JLabel newPriceLabel = new JLabel ("New price");
                JButton editedButton = new JButton ("Edit");
                JTextField nameText = new JTextField();
                JTextField newNameText = new JTextField();
                JTextField newPriceText = new JTextField();

                titleLabel.setBounds (50, 45, 400, 25);
                nameLabel.setBounds (50, 120, 100, 25);
                nameText.setBounds (50, 150, 200, 25);
                newNameLabel.setBounds (50, 200, 100, 25);
                newNameText.setBounds (50, 230, 200, 25);
                newPriceText.setBounds (50, 310, 200, 25);
                newPriceLabel.setBounds (50, 280, 100, 25);
                editedButton.setBounds (50, 375, 100, 20);

                editedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = getNameField(nameText);
                        String itemNewName = getNameField(newNameText);
                        String itemNewPrice = getPriceField(newPriceText);
                        double price = Double.parseDouble(itemNewPrice);

                        MenuItem newItem = new MenuItem(itemNewName, price);
                        restaurant.editMenuItem(find(itemName), itemNewName, price);
                        RestaurantSerializator.serialize(restaurant, name);
                        JOptionPane.showMessageDialog(null, "Item edited successfully!");
                        editPage.setVisible(false);
                    }
                });

                editPage.add(editedButton);
                editPage.add(nameLabel);
                editPage.add(newNameLabel);
                editPage.add(newPriceLabel);
                editPage.add(nameText);
                editPage.add(newNameText);
                editPage.add(newPriceText);
                editPage.add(titleLabel);
                editPage.setVisible(true);
            }
        });

        //// SHOW

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame viewPage = new JFrame();

                String[] collumns = {"Item", "Price"};
                ArrayList<MenuItem> list = restaurant.getMenu();
                viewPage.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                //viewPage.setPreferredSize(new Dimension(452, 427));
                viewPage.setBounds(500, 150, 700, 600);
                viewPage.setLayout(null);
                viewPage.getContentPane().setBackground(Color.PINK);

                DefaultTableModel myModel = new DefaultTableModel();
                myModel.setColumnIdentifiers(collumns);
                Object[] obj = new Object[2];
                for (MenuItem current : list) {
                    obj[0] = current.getName();
                    obj[1] = current.getPrice();
                    myModel.addRow(obj);
                }
                JTable myTable = new JTable(myModel);
                JScrollPane myScrollPane = new JScrollPane();
                myScrollPane.setBounds(35, 90, 600, 400);
                myScrollPane.setViewportView(myTable);
                viewPage.add(myScrollPane);

                viewPage.setVisible(true);
            }
        });


        getContentPane().add(addButton);
        getContentPane().add(removeButton);
        getContentPane().add(editButton);
        getContentPane().add(viewButton);
        getContentPane().add(backButton);
    }


    public String getPriceField(JTextField tf) {
        return tf.getText();
    }

    public String getNameField(JTextField tf) {
        return tf.getText();
    }

    public MenuItem find(String name) {
        ArrayList<MenuItem> items = restaurant.getMenu();
        for (MenuItem current : items) {
            if (current.getName().compareTo(name) == 0)
                return current;
        }
        return null;
    }

}
