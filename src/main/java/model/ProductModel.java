package model;
import java.sql.Timestamp;
import java.util.List;

public class ProductModel {

	private Integer id;
	private Integer partNumber;
	private String name;
	private Double price;
	private Integer quantity;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Boolean published;
	private List<String> urlImgs;
	


	public ProductModel() {
	
	}
	
	public ProductModel(Integer id, Integer partNumber, String name, Double price, Integer quantity,
			Timestamp createdAt, Timestamp updatedAt, Boolean published, List<String> urlImgs) {
		
		this.id = id;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.published = published;
		this.urlImgs = urlImgs;
	}
	
	public ProductModel(Integer partNumber, String name, Double price, Integer quantity) {
		
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(Integer partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	public List<String> getUrlImgs() {
		return urlImgs;
	}

	public void setUrlImgs(List<String> urlImgs) {
		this.urlImgs = urlImgs;
	}

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", partNumber=" + partNumber + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + 
				", published=" + published +", url=" + urlImgs + "]";
	}

	
}
