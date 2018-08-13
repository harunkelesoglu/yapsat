package argedor.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import argedor.exception.NotFoundException;
import argedor.exception.NotFoundException;
import argedor.model.Order;
import argedor.model.Product;
import argedor.model.User;
import argedor.repository.OrderRepository;
import argedor.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/order")
@Api(value = "User Controller", description = "Makes the CRUD operations for orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "Return all orders of seller.")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized. You have to take a token from /login path."),
			@ApiResponse(code = 403, message = "Forbidden. You just don’t have permission to access this resource.") })
	@PostMapping("/all")
	public List<Order> getOrders(@RequestBody Map<String, String> seller) {
		
		List<Order> orders = this.orderRepository.findOrdersBySeller(seller.get("seller"));
		return orders;
	}

	@ApiOperation(value = "Add new order.")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized. You have to take a token from /login path."),
			@ApiResponse(code = 403, message = "Forbidden. You just don’t have permission to access this resource.") })
	@PostMapping
	public Order addOrder(@RequestBody Order order) {

		String buyer = order.getBuyer();
		Order addedOrder = this.orderRepository.save(order);
		User user = this.userRepository.findByUsername(buyer);
		List<Order> orders = user.getOrders();
		orders.add(addedOrder);
		this.userRepository.save(user);
		
		return addedOrder;

	}

	@ApiOperation(value = "Update order status.")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized. You have to take a token from /login path."),
			@ApiResponse(code = 403, message = "Forbidden. You just don’t have permission to access this resource.") })
	@PutMapping
	public void updateOrderStatus(@RequestBody Map<String, String> body) {
		
		String orderId = body.get("id");
		boolean approved = Boolean.valueOf(body.get("approved"));
		
		try {
			Optional<Order> order = this.orderRepository.findById(orderId);
			order.get().setApproved(approved);
			this.orderRepository.save(order.get());
		}catch(Exception e) {
			throw new NotFoundException(orderId+" not found.");
		}

	}

	@ApiOperation(value = "Delete order by id.")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized. You have to take a token from /login path."),
			@ApiResponse(code = 403, message = "Forbidden. You just don’t have permission to access this resource.") })
	@DeleteMapping
	public void deleteOrder(@RequestBody Map<String, String> order) {

		this.orderRepository.deleteById(order.get("orderId"));
		return;
	}

}
