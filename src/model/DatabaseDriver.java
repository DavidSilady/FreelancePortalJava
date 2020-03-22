package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;


public class DatabaseDriver {
    /*
    Config File Format:
    JDBC_DRIVER
    DB_URL
    USER
    PASS
     */

    public static boolean insert_user(List<String> userData) {

        try {
            Connection connection = null;
            Statement statement = null;

            connection = establishConnection();
            statement = connection.createStatement();

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String sql_code = "INSERT INTO users (name, surname, email, password, registration_date) " +
                    "VALUES ( '" + userData.get(0) + "','" + userData.get(1) + "','" + userData.get(2) + "','" + userData.get(3) + "','" +
            formatter.parse(userData.get(4)) + "')";
            System.out.println(sql_code);

            statement.executeUpdate(sql_code);
            statement.close();
            connection.commit();
            connection.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }
    
    private static Connection establishConnection() throws ClassNotFoundException, SQLException {
        ArrayList<String> configInfo = readConfig("dbConfig");
        assert configInfo != null;
        String JDBC_DRIVER = configInfo.get(0);
        String DB_URL = configInfo.get(1);
        String USER = configInfo.get(2);
        String PASS = configInfo.get(3);
    
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager
                .getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(false);
        return connection;
    }
    
    private static ArrayList<String> readConfig(String configFileName) {
        try {
            ArrayList<String> configInfo = new ArrayList<>();
            File configFile = new File("config/" + configFileName);
            Scanner myReader = new Scanner(configFile);
            while (myReader.hasNextLine()) {
                configInfo.add(myReader.nextLine());
            }
            myReader.close();
            return configInfo;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
