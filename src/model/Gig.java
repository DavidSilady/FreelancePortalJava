package model;

import javax.persistence.*;

@Entity
@Table (name = "gigs")
public class Gig implements Listable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "freelancer_id")
    private int freelancerID;

    @Column(name = "category_id")
    private int categoryID;

    @Column(name = "gig_name")
    private String gigName;

    @Transient
    private String category;

    @Transient
    private String freelancerAlias;

    @Transient
    private int numSold;

    @Transient
    private double avgRating;

    public Gig () {
    }

    public Gig(int freelancerID, int categoryID, String gigName) {
        this.freelancerID = freelancerID;
        this.categoryID = categoryID;
        this.gigName = gigName;
    }

    public Gig(int id, int freelancerID, String category, String gigName) {
        this.id = id;
        this.freelancerID = freelancerID;
        this.category = category;
        this.gigName = gigName;
    }

    public Gig(int id, int freelancerID, String category, String gigName, String freelancerAlias){
        this.id = id;
        this.freelancerID = freelancerID;
        this.category = category;
        this.gigName = gigName;
        this.freelancerAlias = freelancerAlias;
        this.numSold = 0;
        this.avgRating = 1;
    }
    
    public Gig(int id, int freelancerID, String category, String gigName, String freelancerAlias, int numSold, double avgRating){
        this.id = id;
        this.freelancerID = freelancerID;
        this.category = category;
        this.gigName = gigName;
        this.freelancerAlias = freelancerAlias;
        this.numSold = numSold;
        this.avgRating = avgRating;
    }

    public Gig(String name,String category){
        this.gigName = name;
        this.category = category;
        this.numSold = 0;
        this.avgRating = 1;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getFreelancerId () {
        return freelancerID;
    }

    public void setFreelancerId (int freelancerId) {
        this.freelancerID = freelancerId;
    }

    public int getCategoryId () {
        return categoryID;
    }

    public void setCategoryId (int categoryId) {
        this.categoryID = categoryId;
    }

    public String getGigName () {
        return gigName;
    }

    public void setGigName (String gigName) {
        this.gigName = gigName;
    }


    public String getCategory() {
        return category;
    }
    public String getFreelancerAlias() { return freelancerAlias; }
    
    public String getAvgRating () {  // TBD
        return String.format("%.1f", avgRating);
    }
    public String getNumSold () {
        return String.valueOf(this.numSold);
    }
    
    @Override
    public String getListablePaneName () {
        return "listings/gigListing";
    }
}
