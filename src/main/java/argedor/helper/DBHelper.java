package argedor.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import argedor.model.User;
import argedor.repository.OrderRepository;
import argedor.repository.UserRepository;

public class DBHelper {

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  OrderRepository orderRepository;
	


	
	public boolean orderIsExist(String id) {

		Optional<argedor.model.Order> isThere = orderRepository.findById(id);
		return (isThere != null) ? true : false;
	}
}
