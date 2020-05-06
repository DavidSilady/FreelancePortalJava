package model;

public class PastPurchase {
    private String gig_name;
    private String service;
    private String alias;
    private int price;
    private String date;
    private int gigID;

    public PastPurchase(String gig_name,String service,String alias,int price,String date,int gigID){
        this.gig_name = gig_name;
        this.service = service;
        this.alias = alias;
        this.price = price;
        this.date = date;
        this.gigID = gigID;
    }

    public String getGigName(){
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

    public int getGigID(){
        return gigID;
    }
}
