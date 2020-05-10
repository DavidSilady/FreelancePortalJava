package model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review implements Listable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id")
    private int customerID;

    @Column(name = "gig_id")
    private int gigID;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Transient
    private String gigName;

    @Transient
    private String authorName;

    public Review () {
    }

    public Review (int id, int customerID, int gig_id, String content, int rating) {
        this.id = id;
        this.customerID = customerID;
        this.gigID = gig_id;
        this.content = content;
        this.rating = rating;
    }

    public Review (int customerID, int gig_id, String content, int rating) {
        this.customerID = customerID;
        this.gigID = gig_id;
        this.content = content;
        this.rating = rating;
    }

    public Review(String authorName,String gigName,int rating,String content){
        this.authorName = authorName;
        this.gigName = gigName;
        this.rating = rating;
        this.content = content;
    }


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getGigID() {
        return gigID;
    }

    public void setGigID(int gigID) {
        this.gigID = gigID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public String getRatingAsString() {
        return String.valueOf(rating);
    }

    public String getAuthorName (){
        return authorName;
    }

    public String getGigName(){
        return gigName;
    }
/*



    public String getContent() {
        return content;
    }*/
    
    @Override
    public String getListablePaneName () {
        return "listings/reviewListing";
    }
}
