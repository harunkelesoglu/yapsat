package argedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import argedor.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	Role findByRole(String role);

//	Role findByRole(String role);

}
