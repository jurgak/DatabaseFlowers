package com.company;

import java.sql.*;
import java.util.Scanner;

public class DatabaseFlowers {
    static final String DB_URL = "jdbc:mysql://group-2-database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/Flowers";
    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "Riga1234";
    static Connection conn = null;
    static Statement stmt = null;

    public static void insertExample() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Flower Type:");
        String Type = input.next();
        System.out.println("Quantity in stock:");
        int Quantity = input.nextInt();
        System.out.println("Price (EUR):");
        double Price = input.nextDouble();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FlowersInStock(Type,Quantity,Price) values(?,?,?)");
        stmt.setString(1, Type);
        stmt.setInt(2, Quantity);
        stmt.setDouble(3, Price);

        if (stmt.executeUpdate() != 0) {
            System.out.println("Inserted");
        }
    }

    public static void deleteExample() throws SQLException {
        String sql = "DELETE FROM FlowersInStock WHERE ID=7";
        System.out.println("Deleted: " + stmt.executeUpdate(sql));
    }

    //public static void selectExample() throws SQLException {
    //String sql = "SELECT * FROM shoes WHERE ID=123";
    //  String sql = "SELECT * FROM FlowersInStock";
    //ResultSet rs = stmt.executeQuery(sql);

    //while (rs.next()) {
    //  System.out.println(rs.getInt("ID") + " " + rs.getString("Type"));
    //System.out.println(rs.getInt(1) + " " + rs.getString(2));
    //}
    //rs.close();
    //}


    public static void updateExample() throws SQLException {
        String sql = "UPDATE FlowersInStock\n" +
                "SET Quantity = 101\n" +
                "WHERE Type = 'Rose'";
        System.out.println("Updated: " + stmt.executeUpdate(sql));
    }
    public static void main(String[] args) {
        try {
            ///Return connection instance
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");

            //Create statement object
            stmt = conn.createStatement();
            System.out.println("Please choose desired action: s for Select");
            //selectExample();
            //insertExample();
            updateExample();
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
