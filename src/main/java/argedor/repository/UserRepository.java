package argedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import argedor.model.Order;
import argedor.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

	void deleteByUsername(String username);

}
