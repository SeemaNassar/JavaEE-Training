package model;

public class RulesModel {
	private Integer id;
	private String userType;
	
	public RulesModel() {
		
	}

	public RulesModel(Integer id, String userType) {
		this.id = id;
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "RulesModel [id=" + id + ", userType=" + userType + "]";
	}
	
}
