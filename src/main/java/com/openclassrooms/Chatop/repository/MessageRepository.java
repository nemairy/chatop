package com.openclassrooms.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Chatop.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
