package com.openclassrooms.Chatop.service.api;

import com.openclassrooms.Chatop.DTOs.MessageDto;

public interface MessageService {
	
	String createSimple(String authorEmail, MessageDto in);

}
