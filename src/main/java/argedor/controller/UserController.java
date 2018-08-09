package argedor.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import argedor.exception.ConflictException;
import argedor.exception.UserNotFoundException;
import argedor.model.User;
import argedor.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user")
@Api(value = "User Controller", description = "Makes the CRUD operations for user,order,product")
public class UserController {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@ApiOperation(value = "Return All Users")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Forbidden. You have to take a token from /login path") })
	@GetMapping
	public List<User> getAllUser() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@ApiOperation(value = "Return user information by username")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Forbidden. You have to take a token from /login path") })
	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) throws Exception {

		User user = this.userRepository.findByUsername(username);
		if (user != null) {
			return user;
		}
		throw new UserNotFoundException(username + " not found");
	}

	@ApiOperation(value = "Add new user")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Forbidden. You have to take a token from /login path") })
	@PostMapping("/register")
	public User addUser(@RequestBody User user) throws Exception {

		String username = user.getUsername();
		if (IsExist(username)) {
			throw new ConflictException(username + " is already exist.");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User addedUser = this.userRepository.save(user);
		return addedUser;
	}

	@ApiOperation(value = "Delete specified user by id ")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Forbidden. You have to take a token from /login path") })
	@DeleteMapping("/{username}")
	public void deleteUser(@PathVariable String username) throws Exception {

		if (IsExist(username)) {
			this.userRepository.deleteByUsername(username);
			return;
		}
		throw new UserNotFoundException(username + " does not already exist");
	}

	@ApiOperation(value = "Update user.Add new order ,delete order, update order status")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Forbidden. You have to take a token from /login path ") })
	@PostMapping("/update")
	public User updateUser(@RequestBody User user) throws Exception {

		String username = user.getUsername();
		if (IsExist(username)) {
			User updatedUser = this.userRepository.save(user);
			return updatedUser;
		}
		throw new UserNotFoundException(username + "is not found");

	}

	public boolean IsExist(String username) {

		User isThere = this.userRepository.findByUsername(username);
		return (isThere != null) ? true : false;
	}

}
