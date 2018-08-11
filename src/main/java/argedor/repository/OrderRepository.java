package argedor.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import argedor.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

	@Query("{'seller': ?0}")
	List<Order> findOrdersBySeller(String seller);
	//Order findOne(String id);	
}
