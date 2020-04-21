package model;

import javafx.beans.property.StringProperty;

public class Gig implements Listable {
    private int id;
    private int freelancerID;
    private String category;
    private String gigName;
    private String freelancerAlias;
    private int numSold;
    private double avgRating;

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
    
    public int getId () {
        return id;
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

    public String getGigName() { return gigName; }
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
