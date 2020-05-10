package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "registration_date")
    private Date registrationDate;


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Date getRegistrationDate () {
        return registrationDate;
    }

    public void setRegistrationDate (Date registrationDate) {
        this.registrationDate = registrationDate;
    }

   public User(){
        ;
    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(String name, String surname, String email, String password, Date registration_date) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registrationDate = registration_date;
    }

    public User(int id, String name, String surname, String email, String password, Date registration_date) {
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
        List results = ORMDatabaseDriver.selectObjects("FROM User WHERE email = '" + this.email + "'" );
        return !results.isEmpty();
    }

    public boolean register() throws Exception {
        if (isAlreadyRegistered()) {
            // throw new InstanceAlreadyExistsException();
            return false;
        }
        this.id = ORMDatabaseDriver.insertObject(this);
        return true;
    }

    public boolean verify() {
       List<User> result = ORMDatabaseDriver.selectObjects("FROM User WHERE email = '" + this.email + "'" );
       if (result.isEmpty())
           return false;
       else if (!(result.get(0).getPassword().equals(this.password)))
           return false;

       this.id = result.get(0).getId();
       this.name = result.get(0).getName();
       this.surname = result.get(0).getSurname();
       this.registrationDate = result.get(0).getRegistrationDate();
       return true;
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
    public void addReview(int gigID, int rating, String content){
        Review review = new Review(this.id,gigID,content,rating);
        review.setId(ORMDatabaseDriver.insertObject(review));
    }

//_____________________________________________________________________BROWSING FREELANCERS_______________________________________________________________
    public ObservableList<Freelancer> loadBestReviewedFreelancers(int quantity) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(
                "SELECT alias,AVG(rating) as average_rating FROM freelancers " +
                        "INNER JOIN gigs ON freelancers.freelance_id = gigs.freelancer_id " +
                        "INNER JOIN reviews ON reviews.gig_id = gigs.id " +
                        "GROUP BY alias " +
                        "HAVING AVG(rating) > (SELECT AVG(reviews.rating) FROM reviews) " +
                        "ORDER BY average_rating DESC " +
                        "LIMIT " + quantity);
        ObservableList<Freelancer> freelancers = FXCollections.observableArrayList();
        for (ArrayList<String> row : result) {
            String temp_alias = row.get(0);
            String temp_avgRating = row.get(1);
            freelancers.add(new Freelancer(temp_alias, Float.parseFloat(temp_avgRating)));
        }
        return freelancers;
    }

    public ObservableList<Freelancer> loadFreelancersWithMostLanguages(int quantity) {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(
                "SELECT DISTINCT temp.alias, temp.languages_known FROM ( " +
                        "SELECT f.alias, fl.freelancer_id, COUNT(fl.freelancer_id) OVER (PARTITION BY fl.freelancer_id) AS languages_known "+
                        "FROM freelancer_languages fl INNER JOIN freelancers f on fl.freelancer_id = f.freelance_id "+
                        "INNER JOIN languages l on fl.language_id = l.id ) temp "+
                "WHERE languages_known > 1 " +
                "ORDER BY temp.languages_known DESC " +
                "LIMIT " + quantity);
        ObservableList<Freelancer> freelancers = FXCollections.observableArrayList();
        for (ArrayList<String> row : result) {
            String temp_alias = row.get(0);
            String temp_languagesNum = row.get(1);
            freelancers.add(new Freelancer(temp_alias, Integer.parseInt(temp_languagesNum)));
        }
        return freelancers;
    }
    //_____________________________________________________________________________________________________________________________________________
    public ObservableList<PastPurchase> loadMyPastPurchases() {
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(
                "SELECT g.gig_name,s.description,f.alias,s.price,o.order_date,s.gig_id FROM services AS s "+
                        "INNER JOIN orders AS o ON s.order_id = o.id " +
                        "INNER JOIN gigs AS g ON s.gig_id = g.id " +
                        "INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id " +
                        "WHERE o.customer_id = " + this.getId());
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

    
    public int getReferencableID() {
        return this.id;
    }
    
    public String getCustomerType() {
        return "customer";
    }
}