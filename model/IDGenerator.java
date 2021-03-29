package model;

/**
 * IDGenerator class is meant to generate the ID for each order in ascending order
 */

public class IDGenerator {

    private static int id = 1;

    public static int getId() {
        return id++;
    }
}
