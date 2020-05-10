package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.management.InstanceAlreadyExistsException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Freelancer extends User {
    private int freelancerID;
    private String alias;
    private String description;
    ArrayList<String> languages;
    private float averageGigRating;
    private int languagesKnown;

    public String getDescription(){ return this.description;}
    public ArrayList<String> getLanguages() { return this.languages;}

    public String getAlias(){
        return alias;
    }
    public String getRatingAsString(){
        String result =  String.valueOf(averageGigRating);
        if (result.length() > 4)
            result = result.substring(0,4);
        return result;
    }

    public String getLanguagesKnownAsString() { return String.valueOf(languagesKnown);}

    public Freelancer(String alias, float averageGigRating){
        this.alias = alias;
        this.averageGigRating = averageGigRating;
    }

    public Freelancer(String alias, int languagesKnown){
        this.alias = alias;
        this.languagesKnown = languagesKnown;
    }

    public Freelancer(User user) {
        super(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getRegistrationDate());
       // System.out.println("Freelancer from user");
        buildFromDB();
        load_my_languages();
    }

    public Freelancer(String email, String password){
        super(email, password);
    }

    public Freelancer(String name, String surname, String email, String password, Date registration_date, String alias){
        super(name, surname, email, password, registration_date);
        this.alias = alias;
    }

    public void saveDescription(String text) {
        this.description = text;
        DatabaseDriver.executeUpdate("UPDATE freelancers SET description = '"+ text +"' WHERE id = " + super.getId());
    }

    private void buildFromDB() {
            ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT freelance_id,alias,description FROM freelancers WHERE id = " + super.getId());
            this.freelancerID = Integer.parseInt(result.get(0).get(0));
            this.alias = result.get(0).get(1);
            this.description = result.get(0).get(2);
    }

    public String getMenuSceneName() {
        return "freelancerMenu";
    }

    public boolean register() throws Exception {
        if (isAlreadyRegistered()){
            return false;
        }
        DatabaseDriver.executeUpdate("INSERT INTO freelancers(name, surname, email, password, registration_date,alias) VALUES('" +super.getName()+ "','" +  super.getSurname() +
                "','" + super.getEmail() + "','" + super.getPassword() + "','" + super.getRegistrationDate() + "','" + alias + "')");
        return true;
    }
/// _________________________________________________________________________________________LANGUAGES__________________________________________________________
    public void load_my_languages() {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT l.language_name FROM freelancer_languages AS fl INNER JOIN languages AS l ON fl.language_id = l.id " +
                "WHERE fl.freelancer_id = " + this.freelancerID + " ORDER BY l.language_name");
        ArrayList<String> new_languages = new ArrayList<String>();
        for (ArrayList<String> row : result) {
            new_languages.add(row.get(0));
        }
        languages = new_languages;
    }

    public int getLanguageIndex(String language) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT id FROM languages  WHERE language_name = '" + language + "'");
        if (result.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(result.get(0).get(0));
        }
    }

    // language list should be hardcoded in the db, as its an enum
    public void addNewLanguage(String new_language) {
        DatabaseDriver.executeUpdate("INSERT INTO languages(language_name) VALUES('"+new_language+"')");
    }

    public boolean languageExists(String language) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT freelancer_id FROM freelancer_languages AS fl INNER JOIN languages AS l ON fl.language_id = l.id WHERE l.language_name = '" + language + "'");
        return !result.isEmpty();
    }

    public void deleteMyLanguage(String language) {
        int language_id = getLanguageIndex(language);
        if (language_id == 0) { return; }
        DatabaseDriver.executeUpdate("DELETE FROM freelancer_languages WHERE freelancer_id = " + this.freelancerID + " AND language_id = " + language_id);
        this.languages.removeIf(i -> i.equals(language));
    }

    public void addMyLanguage(String languageName) {
        if (languageExists(languageName)) {
            // throw new InstanceAlreadyExistsException();
            return;
        }

        ArrayList<String> newLanguages = new ArrayList<String>();
        newLanguages = languages;
        newLanguages.add(languageName);
        languages = newLanguages;

        int languageID = getLanguageIndex(languageName);
        if (languageID == 0) {
            addNewLanguage(languageName);
            languageID = getLanguageIndex(languageName);
        }
        DatabaseDriver.executeUpdate("INSERT INTO freelancer_languages(freelancer_id,language_id) VALUES('" + String.valueOf(this.freelancerID) + "','" + String.valueOf(languageID) + "')");
    }
/// ________________________________________________________________________________________________________________________________________________________________
///  __________________________________________________________________________________GIGS_________________________________________________________________________

    public int getCategoryID(String category) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT id FROM categories WHERE category_name = '" + category + "'");
        return Integer.parseInt(result.get(0).get(0));
    }

    public void addGig(String gigName, String categoryName) {
        int categoryID = getCategoryID(categoryName);
        DatabaseDriver.executeUpdate("INSERT INTO gigs(freelancer_id,category_id,gig_name) VALUES('" + String.valueOf(this.freelancerID) + "','" + String.valueOf(categoryID) + "','" + gigName + "')");
    }

    public ObservableList<Gig> loadMyGigs() {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT g.id,freelancer_id,category_name,gig_name FROM gigs AS g INNER JOIN categories AS c ON g.category_id = c.id " +
                " WHERE freelancer_id = " + this.freelancerID  + " ORDER BY gig_name");
        ObservableList<Gig> my_gigs = FXCollections.observableArrayList();
        for (ArrayList<String> row : result) {
            String temp_id = row.get(0);
            String temp_freelancer_id = row.get(1);
            String temp_categoryName = row.get(2);
            String temp_gigName = row.get(3);
            my_gigs.add(new Gig(Integer.parseInt(temp_id), Integer.parseInt(temp_freelancer_id), temp_categoryName, temp_gigName));
        }
        return my_gigs;
    }

    public void deleteMyGig(String gigName, String category) {
        int categoryID = getCategoryID(category);
        DatabaseDriver.executeUpdate("DELETE FROM gigs WHERE gig_name = '" + gigName + "' AND category_id = " + categoryID + " AND freelancer_id = " + this.freelancerID);
    }

/// ________________________________________________________________________________________________________________________________________________________________
/// _______________________________________________________________________________REVIEWS______________________________________________________________________

    public ObservableList<Review> loadMyReviews(){
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("SELECT email,gig_name,rating,content FROM reviews AS r INNER JOIN users AS u ON r.customer_id = u.id " +
                        "INNER JOIN gigs AS g ON r.gig_id = g.id WHERE g.freelancer_id = " + this.freelancerID + " ORDER BY gig_name");
        ObservableList<Review> my_reviews = FXCollections.observableArrayList();
        for (ArrayList<String> row : result) {
            String temp_user_email = row.get(0);
            String temp_gig_name = row.get(1);
            String temp_rating = row.get(2);
            String temp_content = row.get(3);
            my_reviews.add(new Review(temp_user_email, temp_gig_name,Integer.parseInt(temp_rating), temp_content));
        }
        return my_reviews;
       /* List<Review> result = (ArrayList<Review>) ORMDatabaseDriver.selectObjects("SELECT r.id, r.customer_id,r.gig_id,r.content,r.rating FROM reviews AS r INNER JOIN gigs AS g WHERE g.freelancer_id = " + super.getId());
        for (Review r : result){
            System.out.println(r.getContent());
        }
        return FXCollections.observableList(result);*/
    }

/// ________________________________________________________________________________________________________________________________________________________________
    @Override
    public ObservableList<PastPurchase> loadMyPastPurchases() {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(
                "SELECT g.gig_name,s.description,f.alias,s.price,o.order_date,s.gig_id FROM services AS s "+
                        "INNER JOIN orders AS o ON s.order_id = o.id " +
                        "INNER JOIN gigs AS g ON s.gig_id = g.id " +
                        "INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id " +
                        "WHERE o.freelancer_id = " + this.freelancerID);
        ObservableList<PastPurchase> purchases = FXCollections.observableArrayList();
        for (ArrayList<String> row : result) {
            String temp_gig_name = row.get(0);
            String temp_service = row.get(1);
            String temp_alias = row.get(2);
            String temp_price = row.get(3);
            String temp_date = row.get(4);
            String temp_gig_ID = row.get(5);
            purchases.add(new PastPurchase(temp_gig_name,temp_service,temp_alias, Integer.parseInt(temp_price), temp_date , Integer.parseInt(temp_gig_ID)));
        }
        return purchases;
    }
    
    public void addReview(int gigID, int rating, String reviewText){
        DatabaseDriver.executeUpdate("INSERT INTO reviews(customer_id, gig_id, content, rating) VALUES('" + this.getId() + "','" + gigID + "','" + reviewText + "','" + rating  + "')");
    }
    
    public int getReferencableID() {
        return this.freelancerID;
    }
    
    public String getCustomerType() {
        return "freelancer";
    }
}