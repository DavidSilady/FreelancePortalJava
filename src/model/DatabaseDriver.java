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
    private static StringBuilder buildSelectQuery( ArrayList<String> tableColNames,String tableName, ArrayList <String> joinStatements, String condition){
        StringBuilder query = new StringBuilder("SELECT "); // tableColNamesString + " FROM " + tableName + " ") ;
        for (String colName: tableColNames){
            query.append(colName).append(',');
        }
        query.delete(query.length() - 1, query.length());
        query.append(" FROM " + tableName + " ");
        for (String joinStatement : joinStatements) {
            query.append(joinStatement).append(" ");
        }
        query.append(condition);
        return query;
    }


    public static ArrayList<ArrayList<String>> dbSelect( ArrayList<String> tableColNames,String tableName, ArrayList <String> joinStatements, String condition) throws Exception {
        try {
            Connection connection = establishConnection();
            Statement statement = connection.createStatement();
            StringBuilder query = buildSelectQuery(tableColNames,tableName, joinStatements,condition);
            System.out.println(query);
            ResultSet result = statement.executeQuery(query.toString());
            ArrayList<ArrayList<String>> resultAsString = new ArrayList<ArrayList<String>>();
            while ( result.next() ) {
                ArrayList<String> listLine = new ArrayList<String>();
                for (String tableCol : tableColNames){
                    listLine.add(result.getString(tableCol));
                }
                resultAsString.add(listLine);
            }
            result.close();
            statement.close();
            connection.close();
            return resultAsString;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            throw e;
           // return "error";
        }
    }
    



    public static boolean dbInsert (String tableName, String tableColNames, ArrayList <String> insertData) {
        try {
            Connection connection = establishConnection();
            Statement statement = connection.createStatement();
            StringBuilder query = buildInsertQuery(tableName, tableColNames, insertData);
            System.out.println(query);
            statement.executeUpdate(query.toString());
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

    private static StringBuilder buildInsertQuery(String tableName, String tableColNames, ArrayList <String> insertData){
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + tableColNames + " VALUES (");
        for (String data : insertData) {
            query.append("'").append(data).append("', ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(");");
        return query;
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
