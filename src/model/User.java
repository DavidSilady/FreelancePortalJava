package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String registrationDate;

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User(){
        ;
    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(String name, String surname, String email, String password, String registration_date) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registrationDate = registration_date;
    }

    public User(int id, String name, String surname, String email, String password, String registration_date) {
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

    public boolean isAlreadyRegistered() throws Exception {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT id FROM users WHERE email = '" + this.email + "'");
        return !result.isEmpty();
    }

    public boolean register() throws Exception {
        if (isAlreadyRegistered()) {
            // throw new InstanceAlreadyExistsException();
            return false;
        }
        DatabaseDriver.executeUpdate("INSERT INTO users(name, surname, email, password, registration_date) VALUES('" + name + "','" + surname + "','" + email + "','" + password + "','" + registrationDate + "')");
        return true;
    }

    public boolean verify() {
        try {
            ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT id,name,surname,email,password,registration_date FROM users WHERE email = '" + this.email + "'");
            if (result.isEmpty()) {
                return false;
            }

            String temp_id = result.get(0).get(0);
            String temp_name = result.get(0).get(1);
            String temp_surname = result.get(0).get(2);
            String temp_email = result.get(0).get(3);
            String temp_password = result.get(0).get(4);
            String temp_date = result.get(0).get(5);

            if (this.password.equals(temp_password)) {
                this.id = Integer.parseInt(temp_id);
                this.name = temp_name;
                this.surname = temp_surname;
                this.email = temp_email;
                this.registrationDate = temp_date;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT category_name FROM categories " +
                "GROUP BY category_name ORDER BY category_name");
        ArrayList<String> categories = new ArrayList<>();
        for (ArrayList<String> row : result) {
            categories.add(row.get(0));
        }
        return categories;
    }

    public ArrayList<Listable> findGigByCategory(String category, int page) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT g.id,gig_name,category_name,alias,freelancer_id FROM gigs AS g " +
                " INNER JOIN categories AS c ON g.category_id = c.id INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id " +
                " WHERE category_name = '" + category + "' ORDER BY gig_name LIMIT 16 OFFSET " + page * 16);
        ArrayList<Listable> gigs = new ArrayList<>();
        for (ArrayList<String> row : result) {
            String temp_id = row.get(0);
            String temp_gigName = row.get(1);
            String temp_categoryName = row.get(2);
            String temp_alias = row.get(3);
            String temp_freelancerID = row.get(4);
            gigs.add(new Gig(Integer.parseInt(temp_id), Integer.parseInt(temp_freelancerID), temp_categoryName, temp_gigName, temp_alias));
        }
        return gigs;
    }

    public ArrayList<Listable> loadAllGigs(int page) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT g.id,gig_name,category_name,alias,freelancer_id FROM gigs AS g " +
                " INNER JOIN categories AS c ON g.category_id = c.id INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id ORDER BY gig_name " +
                "limit 16 offset " + page * 16);
        ArrayList<Listable> gigs = new ArrayList<>();
        for (ArrayList<String> row : result) {
            String temp_id = row.get(0);
            String temp_gigName = row.get(1);
            String temp_categoryName = row.get(2);
            String temp_alias = row.get(3);
            String temp_freelancerID = row.get(4);
            gigs.add(new Gig(Integer.parseInt(temp_id), Integer.parseInt(temp_freelancerID), temp_categoryName, temp_gigName, temp_alias));
        }
        return gigs;
    }

    public ObservableList<Freelancer> loadBestReviewedFreelancers(int quantity) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(
                "SELECT alias,AVG(rating)  as average_rating FROM freelancers " +
                        "INNER JOIN gigs ON freelancers.freelance_id = gigs.freelancer_id " +
                        "INNER JOIN reviews ON reviews.gig_id = gigs.id " +
                        "GROUP BY alias " +
                        "HAVING AVG(rating) > (SELECT AVG(reviews.rating) FROM reviews) " +
                        "ORDER BY average_rating DESC " +
                        "LIMIT " + quantity);
        
        ObservableList<Freelancer> freelancers = FXCollections.observableArrayList();;
        for (ArrayList<String> row : result) {
            String temp_alias = row.get(0);
            String temp_avgRating = row.get(1);
            freelancers.add(new Freelancer(temp_alias, Float.parseFloat(temp_avgRating)));
        }
        return freelancers;
    }

}