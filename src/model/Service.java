package model;

import java.util.ArrayList;

public class Service implements Listable {
	private int id;
	private int gig_id;
	private int order_id;
	private int invoice_id;
	private double price = 0;
	private String description = "";
	
	public Service() { }
	
	public Service(int gig_id, double price, String description) {
		this.gig_id = gig_id;
		this.price = price;
		this.description = description;
	}
	
	public void setBasicInfo(double price, String description) {
		this.price = price;
		this.description = description;
	}
	
	public void setIDs(int order_id, int invoice_id, int gig_id) {
		this.order_id = order_id;
		this.gig_id = gig_id;
		this.invoice_id = invoice_id;
	}
	
	public void createDBListing() {
		String query = "INSERT INTO services (gig_id, order_id, invoice_id, price, description) VALUES (" +
				this.gig_id +
				", " +
				this.order_id +
				", " +
				this.invoice_id +
				", " +
				Math.ceil(this.price) +
				", '" +
				this.description +
				"')";
		DatabaseDriver.executeUpdate(query);
	}
	
	
	@Override
	public String getListablePaneName () {
		return "listings/serviceListing";
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getGig_id () {
		return gig_id;
	}
	
	public void setGig_id (int gig_id) {
		this.gig_id = gig_id;
	}
	
	public int getOrder_id () {
		return order_id;
	}
	
	public void setOrder_id (int order_id) {
		this.order_id = order_id;
	}
	
	public int getInvoice_id () {
		return invoice_id;
	}
	
	public void setInvoice_id (int invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	public double getPrice () {
		return price;
	}
	
	public void setPrice (double price) {
		this.price = price;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
}
