package model;

import org.postgresql.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;


public class DatabaseDriver {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "houno";

    public static boolean insert_user(List<String> userData) {

        try {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
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
    
    private ArrayList<String> readConfig(String configFileName) {
        try {
            ArrayList<String> configInfo = new ArrayList<>();
            File configFile = new File(configFileName);
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
