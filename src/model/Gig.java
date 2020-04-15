package model;

import javafx.beans.property.StringProperty;

public class Gig {
    int id;
    int freelancerID;
    private String category;
    private String gigName;

    public Gig(int id, int freelancerID, String category, String gigName){
        this.id = id;
        this.freelancerID = freelancerID;
        this.category = category;
        this.gigName = gigName;
    }

    public Gig(String name,String category){
        this.gigName = name;
        this.category = category;
    }

    public String getGigName() {
        return gigName;
    }
    public String getCategory() {
        return category;
    }

}
