package view;

import model.Order;
import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * UserGUI class deals with creating the main frame of the graphical user interface
 * From this frame, the user can go to the Admin operations, Waiter operations or to the Chef
 */

public class UserGUI extends JFrame {

    private JButton waiterButton;
    private JButton adminButton;
    private JButton chefButton;
    private Restaurant restaurant;
    private ArrayList<Order> listOfOrders = new ArrayList<>();
    private String name;


    public UserGUI(Restaurant restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;

        //JFrame startPage = new JFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(709, 374));
        this.setBounds(500, 150, 750, 550);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.PINK);

        JLabel title = new JLabel ("OPERATIONS");
        title.setFont(new Font("Arial", Font.PLAIN, 28));
        title.setBounds (270, 70, 300, 77);
        getContentPane().add(title);

        adminButton = new JButton("Admin");
        waiterButton = new JButton("Waiter");
        chefButton = new JButton("Chef");

        waiterButton.setFont(new Font("ARIAL", Font.PLAIN, 18));
        adminButton.setFont(new Font("ARIAL", Font.PLAIN, 18));
        chefButton.setFont(new Font("ARIAL", Font.PLAIN, 18));

        adminButton.setBounds (75, 210, 125, 91);
        waiterButton.setBounds (295, 210, 125, 91);
        chefButton.setBounds (515, 210, 125, 91);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminGUI adminPage = new AdminGUI(restaurant, name);
                adminPage.setVisible(true);
            }
        });

        waiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaiterGUI waiterPage = new WaiterGUI(restaurant, listOfOrders, name);
                waiterPage.setVisible(true);
            }
        });

        UserGUI myUser = this;
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChefGUI chefPage = new ChefGUI(restaurant, myUser);
                chefPage.setVisible(true);
            }
        });

        getContentPane().add(waiterButton);
        getContentPane().add(adminButton);
        getContentPane().add(chefButton);
    }

    @Override
    public String toString() {
        return "UserGUI{" +
                "waiterButton=" + waiterButton +
                ", adminButton=" + adminButton +
                ", chefButton=" + chefButton +
                ", restaurant=" + restaurant +
                '}';
    }

    public ArrayList<Order> getListOfOrders() {
        return listOfOrders;
    }
}
