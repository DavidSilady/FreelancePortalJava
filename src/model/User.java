package model;
import model.DatabaseDriver;

import java.sql.ResultSet;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String registration_date;

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public User(String email, String password){
        this.password = password;
        this.email = email;
    }

    public boolean verify(){
        try {
            ArrayList<String> joins = new ArrayList<String>();
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("id");
            colNames.add("name");
            colNames.add("surname");
            colNames.add("email");
            colNames.add("password");
            colNames.add("registration_date");
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "users", joins, "WHERE email = '" + this.email + "'");
            String temp_id = result.get(0).get(0);
            String temp_name = result.get(0).get(1);
            String temp_surname = result.get(0).get(2);
            String temp_email = result.get(0).get(3);
            String temp_password = result.get(0).get(4);
            String temp_date = result.get(0).get(5);
/*
            System.out.println("id: " + temp_id);
            System.out.println("name: " + temp_name);
            System.out.println("surname: " + temp_surname);
            System.out.println("email: " + temp_email);
            System.out.println("password: " + temp_password);
            System.out.println("date: " + temp_date);*/
            if (this.password.equals(temp_password)){
                this.id = Integer.parseInt(temp_id);
                this.name = temp_name;
                this.surname = temp_surname;
                this.registration_date = temp_date;
                return true;
            }
            else{
                System.out.println(this.password);
                System.out.println(temp_password);
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }

    public void findGigByCategory(String category) {
        try {
            ArrayList<String> joins = new ArrayList<String>();
            joins.add("INNER JOIN categories AS c ON g.category_id = c.id");
            joins.add("INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id");
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("gig_name");
            colNames.add("category_name");
            colNames.add("alias");
            colNames.add("email");
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames,"gigs AS g", joins, "WHERE category_name = '" + category + "'");
            int matchesNum = 0;
            for (ArrayList<String> line : result){
                matchesNum++;
                System.out.println("match number "+ matchesNum);
                System.out.println( "gig_name = " + line.get(0) );
                System.out.println( "category_name = " + line.get(1));
                System.out.println( "alias = " + line.get(2) );
                System.out.println( "email = " + line.get(3) );
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
