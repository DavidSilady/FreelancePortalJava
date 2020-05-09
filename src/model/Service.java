package model;

public class Service implements Listable {
	private int id;
	private int gigID;
	private int orderID;
	private int invoiceID;
	private double price = 0;
	private String description = "";
	
	public Service() { }
	
	public String getInsertQuery() {
		return "INSERT INTO services (gig_id, order_id, invoice_id, price, description) VALUES (" +
				this.gigID +
				", " +
				this.orderID +
				", " +
				this.invoiceID +
				", " +
				(int) Math.ceil(this.price) +
				", '" +
				this.description +
				"')";
	}
	
	public Service(int gig_id, double price, String description) {
		this.gigID = gig_id;
		this.price = price;
		this.description = description;
	}
	
	public void setBasicInfo(double price, String description) {
		this.price = price;
		this.description = description;
	}
	
	public void setIDs(int order_id, int invoice_id) {
		this.orderID = order_id;
		this.invoiceID = invoice_id;
	}
	
	public void createDBListing() {
		String query = "INSERT INTO services (gig_id, order_id, invoice_id, price, description) VALUES (" +
				this.gigID +
				", " +
				this.orderID +
				", " +
				this.invoiceID +
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
	
	public int getGigID () {
		return gigID;
	}
	
	public void setGigID (int gigID) {
		this.gigID = gigID;
	}
	
	public int getOrderID () {
		return orderID;
	}
	
	public void setOrderID (int orderID) {
		this.orderID = orderID;
	}
	
	public int getInvoiceID () {
		return invoiceID;
	}
	
	public void setInvoiceID (int invoiceID) {
		this.invoiceID = invoiceID;
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
