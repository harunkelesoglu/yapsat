package argedor.model;

import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String name;
	private String surname;
	@Indexed(unique = true)
	private String username;
	private String password;
	private String email;
	private float location[];
	@DBRef
	private Set<Role> roles;
	private List<Product> products;
	@DBRef
	private List<Order> orders;

	
	public User() {
		
	}

	public User(String name, String surname, String username, String password, String email, float[] location,
			Set<Role> roles, List<Product> products, List<Order> orders) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.location = location;
		this.roles = roles;
		this.products = products;
		this.orders = orders;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float[] getLocation() {
		return location;
	}

	public void setLocation(float[] location) {
		this.location = location;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRole(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> order) {
		this.orders = order;
	}

}
