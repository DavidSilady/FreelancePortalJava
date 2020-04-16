package model;

public class Review {
    private String userEmail;
    private String gigName;
    private int rating;
    private String content;

    public Review(String userEmail,String gigName,int rating,String content){
        this.userEmail = userEmail;
        this.gigName = gigName;
        this.rating = rating;
        this.content = content;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getGigName(){
        return gigName;
    }

    public String getRatingAsString() {
        return String.valueOf(rating);
    }

    public String getContent() {
        return content;
    }
}
