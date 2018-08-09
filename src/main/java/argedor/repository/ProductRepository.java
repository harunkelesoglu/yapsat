package argedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import argedor.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
