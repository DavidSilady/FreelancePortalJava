package model;

import java.util.ArrayList;

public class Order {
	int id;
	int customerID;
	String orderDate;
	
	public Order(int customerID, String orderDate) {
		this.customerID = customerID;
		this.orderDate = orderDate;
	}
	
	public Order(int id, int customerID, String orderDate) {
		this.id = id;
		this.customerID = customerID;
		this.orderDate = orderDate;
	}
	
	public void createDBListing() {
		String query = "INSERT INTO orders (customer_id, order_date) VALUES (" + this.customerID + ", '" + orderDate + "') RETURNING id";
		ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(query);
		if (result == null || result.isEmpty())
			return;
		this.id = Integer.parseInt(result.get(0).get(0));
	}
}
