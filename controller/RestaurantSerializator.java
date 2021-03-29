package controller;

import java.io.*;
import model.*;

public class RestaurantSerializator {

    public static void serialize(Restaurant r, String name) {
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(r);
            out.close();
            fileOut.close();
            //System.out.println("Serialized data is saved in Restaurant.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Restaurant DEserialize(String name) {
        Restaurant r = null;
        try {
            FileInputStream fileIn = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            r = (Restaurant)in.readObject();
            in.close();
            fileIn.close();
            //System.out.println(r);
            return r;
        } catch (IOException i) {
            //System.out.println(i);
            r = new Restaurant();
            serialize(r, name);
            return r;
        } catch (ClassNotFoundException c) {

            //System.out.println("Restaurant class not found");
            //c.printStackTrace();
            return r = new Restaurant();
        }
    }
}