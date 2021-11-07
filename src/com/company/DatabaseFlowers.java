package com.company;

import java.sql.*;
import java.util.Scanner;

public class DatabaseFlowers {
    static final String DB_URL = "jdbc:mysql://group-2-database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/Flowers";

    static Connection conn = null;
    static Statement stmt = null;

    public static void insertExample() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Flower Type:");
        String Type = input.next();
        System.out.println("Quantity in stock:");
        int Quantity = checkValidity(input);
        System.out.println("Price (0,00 EUR):");
        double Price = checkValidityPrice(input);

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FlowersInStock(Type,Quantity,Price) values(?,?,?)");
        stmt.setString(1, Type);
        stmt.setInt(2, Quantity);
        stmt.setDouble(3, Price);

        if (stmt.executeUpdate() != 0) {
            System.out.println("Inserted\n");
        }
    }

    public static void updateExample() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter ID No. you want to update quantity in stock:");
        int ID = input.nextInt();
        System.out.println("Update quantity in stock:");
        int Quantity = checkValidity(input);
        //if (QuantityTemp < 0) {
        // System.out.println("Quantity cannot be negative. Try again:");
        //  input.nextInt();
        //}


        PreparedStatement stmt = conn.prepareStatement("UPDATE FlowersInStock SET Quantity = ? WHERE ID = ?");
        stmt.setInt(1, Quantity);
        stmt.setInt(2, ID);
        if (stmt.executeUpdate() != 0) {
            System.out.println("Updated\n");
        }
    }

    static int checkValidity (Scanner input) {
           int QuantityCheck = input.nextInt();
           while (QuantityCheck<0){
               System.out.println("Quantity cannot be negative. Try again:");
               QuantityCheck = input.nextInt();
           }
           return QuantityCheck;
            }

    static double checkValidityPrice (Scanner input) {
       double PriceCheck = input.nextDouble();
       while (PriceCheck<=0){
           System.out.println("Price cannot be negative. Try again:");
           PriceCheck = input.nextDouble();
       }
       return PriceCheck;
    }

    public static void deleteExample() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter ID No.you want to delete record:");
        int ID = input.nextInt();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM FlowersInStock WHERE ID = ?");
        stmt.setInt(1, ID);
        if (stmt.executeUpdate() != 0) {
            System.out.println("Deleted\n");
        }
    }

    public static void joinExample() throws SQLException {
        System.out.println("Here You can compare what is in Stock at the Moment and what is in the next Order Table:");
        String sql = "SELECT o.Type,s.Quantity,o.DesiredQuantity FROM FlowersInStock s JOIN FlowerOrder o on s.Type=o.Type";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString("o.Type") + " quantity in stock: "
                    + rs.getInt("s.Quantity") + ". Quantity you are ordering: " + rs.getInt("o.DesiredQuantity"));
        }
        rs.close();
    }
    //public static void selectExample() throws SQLException {
    //  String sql = "SELECT * FROM shoes WHERE ID=123";
    //String sql = "SELECT * FROM FlowersInStock";
    //ResultSet rs = stmt.executeQuery(sql);

    //while (rs.next()) {
    //  System.out.println(rs.getInt("ID") + " " + rs.getString("Type"));
    //System.out.println(rs.getInt(1) + " " + rs.getString(2));
    //}
    //rs.close();
    //}

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
            //Scanner input = new Scanner(System.in);
            while (true) {
                {
                    System.out.println("Press 1 - for inserting an entry into the database");
                    System.out.println("Press 2 - for updating an entry in the database");
                    System.out.println("Press 3 - for deleting an entry from the database");
                    System.out.println("Press 4 - for comparing Flower Quantities in Stock and in Order DB");
                    System.out.println("Press 5 - for EXIT the database");
                    int operation = input.nextInt();
                    switch (operation) {
                        case 1:
                            insertExample();
                            break;
                        case 2:
                            updateExample();
                            break;
                        case 3:
                            deleteExample();
                            break;
                        case 4:
                            joinExample();
                            break;
                        case 5:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }

                    //selectExample();
                    //insertExample();
                    //updateExample();
                    //deleteExample();
                    //joinExample();
                }
            }
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
