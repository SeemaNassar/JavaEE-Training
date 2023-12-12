package model;
import java.util.Date;


public class UserModel {
	private Integer id;
	private Integer ruleId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date birthday;
    
    public UserModel() {
    	
    }
    
	public UserModel( Integer ruleId, String firstName, String lastName, String email, String password,
			Date birthday) {
		
		
		this.ruleId = ruleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	public UserModel( Integer id, Integer ruleId, String firstName, String lastName, String email, String password,
			Date birthday) {
		
		this.id = id;
		this.ruleId = ruleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", ruleId=" + ruleId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", birthday=" + birthday + "]";
	}

	

}
