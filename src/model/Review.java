package model;

public class Review implements Listable{
    private String authorName;
    private String gigName;
    private int rating;
    private String content;

    public Review(String authorName,String gigName,int rating,String content){
        this.authorName = authorName;
        this.gigName = gigName;
        this.rating = rating;
        this.content = content;
    }

    public String getAuthorName (){
        return authorName;
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
    
    @Override
    public String getListablePaneName () {
        return "listings/reviewListing";
    }
}
