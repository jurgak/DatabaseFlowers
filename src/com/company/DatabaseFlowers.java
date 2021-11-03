package com.company;

import java.sql.*;

public class DatabaseFlowers {
    static final String DB_URL = "jdbc:mysql://group-2-database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/Flowers";
    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "Riga1234";
    static Connection conn = null;
    static Statement stmt = null;

    public static void insertExample() throws SQLException {
        String sql = "INSERT INTO FlowersInStock(ID,Type,Quantity,Price) VALUES (1,'Rose',34, 3)";
        System.out.println("Inserted: " + stmt.executeUpdate(sql));
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

    public static void insertWithValue(int ID, String Type, int Quantity, int Price) throws SQLException {
        String sql = "INSERT INTO FlowersInStock(ID,Type,Quantity,Price) VALUES (+" +
                ID + ",'" + Type + "'," + Quantity + "','" + Price + "')";
        stmt.execute(sql);
    }

    public static void main(String[] args){
        try {
            //Return connection instance
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");

            //Create statement object
            stmt = conn.createStatement();

            //selectExample();
            insertExample();
            //updateExample();
            //deleteExample();
            //joinExample();
           // insertWithValue('1', "Rose",'1000', '1');

        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

}
