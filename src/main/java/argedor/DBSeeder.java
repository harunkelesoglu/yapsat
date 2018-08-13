package argedor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import argedor.model.Order;
import argedor.model.Product;
import argedor.model.Role;
import argedor.model.User;
import argedor.repository.OrderRepository;
import argedor.repository.RoleRepository;
import argedor.repository.UserRepository;

@Component
public class DBSeeder implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Product product1 = new Product("Patetesli Kol Böreği", "kolboreg.jpg",
				"Niğdenin meşhur yeni mahsül patatesleri ile yapılmış enfes kol böreği ile midenizi doldurun");
		Product product2 = new Product("tiramisu", "tiramisu.jpg", "mükkemmel kremalı ağzınıza layık :)");
		Product product3 = new Product("Yaprak sarması", "sarma",
				"Niğdenin meşhur yeni mahsül patatesleri ile yapılmış enfes kol böreği ile midenizi doldurun");
		
		Order order1 = new Order("ayseteyze", "kharun", product1, false);
		Order order2 = new Order("fatmahanim", "kharun", product2, false);
		Order order3 = new Order("ayseteyze", "demo", product3, false);

		Role buyer = new Role("BUYER");
		Role seller = new Role("SELLER");

		Set<Role> role1 = new HashSet<>();
		Set<Role> role2 = new HashSet<>();
		role1.add(buyer);
		role2.add(seller);

		User user1 = new User("Harun", "KELEŞOĞLU", "kharun", bCryptPasswordEncoder.encode("admin"), "harun@demo.com",
				new float[] { 23.345f, 34.2342f }, role1, null, Arrays.asList(order1, order2));
		User user2 = new User("demo", "demo", "demo", bCryptPasswordEncoder.encode("demo"), "demo@demo.com",
				new float[] { 23.345f, 34.2342f }, role1, null, Arrays.asList(order3));
		User user3 = new User("Ayse", "teyze", "ayseteyze", bCryptPasswordEncoder.encode("admin"), "demo2@demo.com",
				new float[] { 23.345f, 34.2342f }, role2, Arrays.asList(product1, product3), null);
		User user4 = new User("Fatma", "hanim", "fatmahanim", bCryptPasswordEncoder.encode("admin"), "demo3@demo.com",
				new float[] { 23.345f, 34.2342f }, role2, Arrays.asList(product2), null);
//
//		this.orderRepository.deleteAll();
//		this.roleRepository.deleteAll();
//		this.userRepository.deleteAll();
//
//		List<Order> orders = Arrays.asList(order1, order2, order3);
//		List<Role> roles = Arrays.asList(buyer, seller);
//		List<Product> products = Arrays.asList(product1, product2, product3);
//		List<User> users = Arrays.asList(user1, user2, user3, user4);
//
//		this.orderRepository.saveAll(orders);
//		this.roleRepository.saveAll(roles);
//		this.userRepository.saveAll(users);
	}
}
