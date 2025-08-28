package com.openclassrooms.Chatop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.Chatop.model.UserN;


public interface UserRepository extends JpaRepository<UserN, Long>{
	
	Optional<UserN> findByEmail(String email);

}
