package data;

import data.interfaces.IDB;

import java.net.ConnectException;
import java.sql.*;
public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:postgresql://localhost:5432/ShelterHunt";
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionURL, "postgres", "ugotitright");

            return con;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("AN EXCEPTION OCCURRED OVER HERE!");
            return null;
        }
    }
}
