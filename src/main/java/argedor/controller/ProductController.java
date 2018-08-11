package argedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import argedor.model.Product;
import argedor.model.User;
import argedor.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "Add new product.")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized. You have to take a token from /login path."),
			@ApiResponse(code = 403, message = "Forbidden. You just don’t have permission to access this resource.") })
	@PostMapping("/{seller}")
	public void addProduct(@PathVariable String seller, @RequestBody Product product) {

		User user = this.userRepository.findByUsername(seller);
		List<Product> products = user.getProduct();
		products.add(product);
		this.userRepository.save(user);
	}


}
