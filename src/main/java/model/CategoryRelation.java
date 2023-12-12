package model;

public class CategoryRelation {
	
	private Integer id;
	private Integer childId;
	private Integer parentId;
	
	public CategoryRelation() {
		// TODO Auto-generated constructor stub
	}

	private CategoryRelation(Integer id, Integer childId, Integer parentId) {
		super();
		this.id = id;
		this.childId = childId;
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "CategoryRelation [id=" + id + ", childId=" + childId + ", parentId=" + parentId + "]";
	}
	
	

}
