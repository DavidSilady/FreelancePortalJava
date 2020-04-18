package model;

import javafx.beans.property.StringProperty;

public class Gig {
    private int id;
    private int freelancerID;
    private String category;
    private String gigName;
    private String freelancerAlias;

    public Gig(int id, int freelancerID, String category, String gigName){
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
    }

    public Gig(String name,String category){
        this.gigName = name;
        this.category = category;
    }

    public String getGigName() { return gigName; }
    public String getCategory() {
        return category;
    }
    public String getFreelancerAlias() { return freelancerAlias; }
    
    public String getAvgRating () {  // TBD
        return "4.6";
    }
}
