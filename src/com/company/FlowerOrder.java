package com.company;
import java.sql.*;
import java.util.Scanner;

public class FlowerOrder {
    static final String DB_URL = "jdbc:mysql://group-2-database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/Flowers";
    //  Database credentials
    static Connection conn = null;
    static Statement stmt = null;
    public static void insert() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Flower Type:");
        String Type = input.next();
        System.out.println("Quantity needed in stock next Month:");
        int DesiredQuantity = input.nextInt();


        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FlowerOrder(Type,DesiredQuantity) values(?,?)");
        stmt.setString(1, Type);
        stmt.setInt(2, DesiredQuantity);


        if (stmt.executeUpdate() != 0) {
            System.out.println("Inserted");
        }
    }
    public static void main(String[] args) {
        try {
            ///Return connection instance
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your username:");
            String USER = input.next();
            System.out.println("Enter your password:");
            String PASS = input.next();
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");

            //Create statement object
            stmt = conn.createStatement();
            System.out.println("Please choose desired action: s for Select");
            //selectExample();
            insert();
           // updateExample();
            //deleteExample();
            //joinExample();

        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {

            }
        }
    }
}
