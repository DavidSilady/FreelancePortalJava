package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
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
    private static ArrayList<ArrayList<String>> resultToStringMatrix (ResultSet resultSet) throws SQLException {
        ArrayList<ArrayList<String>> stringResult = new ArrayList<ArrayList<String>>();
        while ( resultSet.next() ) {
            ArrayList<String> row = new ArrayList<String>();
            int index = 1;
            while (true) {
                String column = resultSet.getString(index);
                index++;
                row.add(column);
                if (index > resultSet.getMetaData().getColumnCount()) {
                    break;
                }
                // row.add(column);
            }
            stringResult.add(row);
        }
        return stringResult;
    }

    public static ArrayList<ArrayList<String>> executeQuery(String query) {
        try {
            ArrayList<ArrayList<String>> stringResult = new ArrayList<>();
            Connection connection = establishConnection();
            connection.setAutoCommit(false);
            try {
                Statement statement = connection.createStatement();
                System.out.println(query);
                ResultSet resultSet = statement.executeQuery(query);
                stringResult = resultToStringMatrix(resultSet);
                resultSet.close();
                statement.close();
                connection.commit();
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                connection.rollback();
            }
            connection.close();
            return stringResult;
        } catch ( Exception e ) {
            System.out.println(e.getClass().getName()+": "+ e.getMessage());
            //throw e;
            //return new ArrayList<ArrayList<String>>();
            return null;
        }
    }

    public static void executeUpdate (String query) {
        try {
            Connection connection = establishConnection();
            connection.setAutoCommit(false);
            try {
                Statement statement = connection.createStatement();
                // StringBuilder query = buildInsertQuery(tableName, tableColNames, insertData);
                System.out.println(query);
                statement.executeUpdate(query);
                statement.close();
                connection.commit();
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                connection.rollback();
            }
            connection.close();
            //return true;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //return false;
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
