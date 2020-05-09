package classesORM;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class UserORM {
	@Id @GeneratedValue
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "registration_date")
	private Date registrationDate;
	
	public UserORM () {
	}
	
	
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getSurname () {
		return surname;
	}
	
	public void setSurname (String surname) {
		this.surname = surname;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public Date getRegistrationDate () {
		return registrationDate;
	}
	
	public void setRegistrationDate (Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
