package app;

import controller.MainController;

public class Application {

    public static void main(String[] args) {

        MainController myRestaurant = new MainController();
        myRestaurant.start(args[0]);

    }
}
