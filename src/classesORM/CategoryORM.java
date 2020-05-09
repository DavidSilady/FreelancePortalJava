package classesORM;

public class CategoryORM {
	private int id;
	private String categoryName;
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
