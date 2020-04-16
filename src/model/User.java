package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String registrationDate;

    public String getName(){ return this.name; }
    public String getSurname(){ return this.surname;}
    public int getId () { return id; }
    public String getEmail () { return email; }
    public String getPassword () { return password; }
    public String getRegistrationDate () { return registrationDate; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    public User(String email, String password){
        this.password = password;
        this.email = email;
    }

    public User(String name, String surname, String email, String password, String registration_date){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registrationDate = registration_date;
    }
    
    public User(int id, String name, String surname, String email, String password, String registration_date){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registrationDate = registration_date;
    }
    
    public String getMenuSceneName() {
        return "userMenu";
    }

    public boolean isAlreadyRegistered () throws Exception{
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("user_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "users",  new ArrayList<String>(), "WHERE email = '" + this.email + "'");
        return ! result.isEmpty();
    }

    public boolean register() throws Exception {
        if (isAlreadyRegistered()){
            throw new InstanceAlreadyExistsException();
        }

        ArrayList <String> userData = new ArrayList<String>();
        userData.add(name);
        userData.add(surname);
        userData.add(email);
        userData.add(password);
        userData.add(registrationDate);

        return DatabaseDriver.dbInsert("users", "(name, surname, email, password, registration_date)", userData);
    }

    public boolean verify(){
        try {
            //ArrayList<String> joins = new ArrayList<String>();
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("user_id");
            colNames.add("name");
            colNames.add("surname");
            colNames.add("email");
            colNames.add("password");
            colNames.add("registration_date");

            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "users",  new ArrayList<String>(), " WHERE email = '" + this.email + "'");
            if (result.isEmpty()){
                return false;
            }

            String temp_id = result.get(0).get(0);
            String temp_name = result.get(0).get(1);
            String temp_surname = result.get(0).get(2);
            String temp_email = result.get(0).get(3);
            String temp_password = result.get(0).get(4);
            String temp_date = result.get(0).get(5);

            if (this.password.equals(temp_password)){
                this.id = Integer.parseInt(temp_id);
                this.name = temp_name;
                this.surname = temp_surname;
                this.email = temp_email;
                this.registrationDate = temp_date;
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("category_name");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(columns, "categories", new ArrayList<String>(), " ORDER BY category_name");
        ArrayList<String> categories = new ArrayList<>();
        for (ArrayList<String> row : result) {
            categories.add(row.get(0));
        }
        return categories;
    }

    public ObservableList<Gig> findGigByCategory(String category) {
            ArrayList<String> colNames = new ArrayList<String>();
            ArrayList<String> joins = new ArrayList<String>();
            colNames.add("gig_id");
            colNames.add("gig_name");
            colNames.add("category_name");
            colNames.add("alias");
            colNames.add("freelancer_id");
            joins.add("INNER JOIN categories AS c ON g.category_id = c.category_id");
            joins.add("INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id");

            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames,"gigs AS g", joins, " WHERE category_name = '" + category + "'");
            ObservableList<Gig> gigs = FXCollections.observableArrayList();
            for (ArrayList<String> row : result) {
                String temp_id = row.get(0);
                String temp_gigName = row.get(1);
                String temp_categoryName = row.get(2);
                String temp_alias = row.get(3);
                String temp_freelancerID = row.get(4);
                gigs.add(new Gig(Integer.parseInt(temp_id), Integer.parseInt(temp_freelancerID), temp_categoryName, temp_gigName , temp_alias));
            }
            return gigs;
    }
}
