package com.openclassrooms.Chatop.service.api;

import com.openclassrooms.Chatop.DTOs.MessageDto;

public interface MessageService {

	String messageCreate(String authorEmail, MessageDto in);
}
