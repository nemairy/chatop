package com.openclassrooms.Chatop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Chatop.model.UserN;

@Repository
public interface UserRepository extends JpaRepository<UserN, Long>{
	
	Optional<UserN> findByEmail(String email);

}
