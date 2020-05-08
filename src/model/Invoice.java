package model;

import java.util.ArrayList;

public class Invoice {
	private int id;
	private String date;
	private String billingAddress;
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getDate () {
		return date;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	public String getBillingAddress () {
		return billingAddress;
	}
	
	public void setBillingAddress (String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public Invoice (String date, String billingAddress) {
		this.date = date;
		this.billingAddress = billingAddress;
	}
	
	public void createDBListing() {
		String query = "INSERT INTO orders (payment_method_id, payment_date, billing_address) " +
				"VALUES (1, '"
				+ this.date + "', '"
				+ this.billingAddress +
				"') RETURNING id";
		ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(query);
		if (result == null || result.isEmpty())
			return;
		this.id = Integer.parseInt(result.get(0).get(0));
	}
}
