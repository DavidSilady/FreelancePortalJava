package classesORM;

import javax.persistence.*;

@Entity
@Table(name = "freelancers")
public class FreelancerORM extends UserORM {
	@Column(name = "freelance_id", unique = true)
	private int freelanceId;
	@Column(name = "alias")
	private String alias;
	@Column(name = "description")
	private String description;
	
	public FreelancerORM () {
	}
	
	public FreelancerORM (int freelanceId, String alias, String description) {
		this.freelanceId = freelanceId;
		this.alias = alias;
		this.description = description;
	}
	
	public int getFreelanceId () {
		return freelanceId;
	}
	
	public void setFreelanceId (int freelanceId) {
		this.freelanceId = freelanceId;
	}
	
	public String getAlias () {
		return alias;
	}
	
	public void setAlias (String alias) {
		this.alias = alias;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
}
