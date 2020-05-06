package model;

public class PastPurchase {
    private String gig_name;
    private String service;
    private String alias;
    private int price;
    private String date;

    public PastPurchase(String gig_name,String service,String alias,int price,String date){
        this.gig_name = gig_name;
        this.service = service;
        this.alias = alias;
        this.price = price;
        this.date = date;
    }

    public String getGig_name(){
        return gig_name;
    }

    public String getService() {
        return service;
    }

    public String getPriceAsString() {
        return String.valueOf(price);
    }

    public String getAlias() {
        return alias;
    }

    public String getDate() {
        return date;
    }
}
