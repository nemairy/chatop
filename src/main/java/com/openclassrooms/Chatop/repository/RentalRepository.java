package com.openclassrooms.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Chatop.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long > {
}
