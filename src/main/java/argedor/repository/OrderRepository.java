package argedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import argedor.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	
	//Order findOne(String id);

}
