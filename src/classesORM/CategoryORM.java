package classesORM;

import javax.persistence.*;

@Entity
@Table (name = "categories")
public class CategoryORM {
	@Id @GeneratedValue
	@Column (name = "id")
	private int id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "description")
	private String description;
	
	public CategoryORM () {
	}
	
	public CategoryORM (int id, String categoryName, String description) {
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getCategoryName () {
		return categoryName;
	}
	
	public void setCategoryName (String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
}
