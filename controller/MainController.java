package controller;

import view.*;
import model.*;

public class MainController {

    UserGUI user;

    Restaurant restaurant;

    public void start(String name)
    {
        this.restaurant = RestaurantSerializator.DEserialize(name);
        user = new UserGUI(restaurant, name);
        user.setVisible(true);
    }
}
