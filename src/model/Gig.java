package model;

public class Gig {
    private int id;
    private int freelancerID;
    private String category;
    private String gig_name;

    public Gig(int id, int freelancerID, String category, String gig_name){
        this.id = id;
        this.freelancerID = freelancerID;
        this.category = category;
        this.gig_name = gig_name;
    }
}
