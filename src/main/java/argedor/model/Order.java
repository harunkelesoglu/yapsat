package argedor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "orders")
public class Order {

	@Id
	private String id;
	private String seller;
	private String buyer;
	private Product product;
	private boolean approved = false;
	
//	For deserialisation purposes Order must have this contructor
	public Order() {
		
	}

	public Order(String seller, String buyer, Product product, boolean approved) {
		this.seller = seller;
		this.buyer = buyer;
		this.product = product;
		this.approved = approved;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
