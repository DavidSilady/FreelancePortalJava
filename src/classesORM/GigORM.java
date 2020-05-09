package classesORM;

import javax.persistence.*;

@Entity
@Table (name = "gigs")
public class GigORM {
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "freelancer_id")
	private int freelancerId;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "gig_name")
	private String gigName;
	
	public GigORM () {
	}
	
	public GigORM (int id, int freelancerId, int categoryId, String gigName) {
		this.id = id;
		this.freelancerId = freelancerId;
		this.categoryId = categoryId;
		this.gigName = gigName;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getFreelancerId () {
		return freelancerId;
	}
	
	public void setFreelancerId (int freelancerId) {
		this.freelancerId = freelancerId;
	}
	
	public int getCategoryId () {
		return categoryId;
	}
	
	public void setCategoryId (int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getGigName () {
		return gigName;
	}
	
	public void setGigName (String gigName) {
		this.gigName = gigName;
	}
}
