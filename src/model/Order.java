package model;

import java.util.ArrayList;

public class Order {
	private int id;
	private int customerID;
	private String date;
	
	public String getInsertQuery() {
		return "INSERT INTO orders (customer_id, order_date) VALUES (" + this.customerID + ", '" + date + "') RETURNING id";
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getCustomerID () {
		return customerID;
	}
	
	public void setCustomerID (int customerID) {
		this.customerID = customerID;
	}
	
	public String getDate () {
		return date;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	public Order(int customerID, String orderDate) {
		this.customerID = customerID;
		this.date = orderDate;
	}
	
	public Order(int id, int customerID, String orderDate) {
		this.id = id;
		this.customerID = customerID;
		this.date = orderDate;
	}
	
	public void createDBListing() {
		String query = "INSERT INTO orders (customer_id, order_date) VALUES (" + this.customerID + ", '" + date + "') RETURNING id";
		ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(query);
		if (result == null || result.isEmpty())
			return;
		this.id = Integer.parseInt(result.get(0).get(0));
	}
}
