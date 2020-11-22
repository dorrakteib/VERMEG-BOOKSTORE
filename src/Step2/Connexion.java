package Step2;

import java.sql.*;

public class Connexion {
    Connection co;
    final String base="bookstore";
    final String user="root";
    final String pwd="";

    public Connection connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            co = DriverManager.getConnection("jdbc:mysql://localhost/"+base+
                    "?serverTimezone=UTC", user, pwd);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return co;
    }

    public void disconnect() {
        try {
            co.close();
            System.out.println("The connection with the database has been closed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An issue happened while closing the connection with the database");
        }

    }

}
