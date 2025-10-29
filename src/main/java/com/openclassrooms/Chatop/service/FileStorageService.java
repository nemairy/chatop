package com.openclassrooms.Chatop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageService {

	private final Set<String> ALLOWED = Set.of("image/jpeg", "image/png", "image/webp");

	@Value("${app.upload.dir:uploads}")
	private String uploadDir;


	 //Stores the file on disk and returns the public url

	public String storeAndGetPublicUrl(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "picture is required");
		}

		// Basic content type check
		String contentType = file.getContentType();
		if (contentType == null || !ALLOWED.contains(contentType)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only JPEG/PNG/WEBP images are allowed");
		}

		// Ensure folder exists
		Path folder = Paths.get(uploadDir).toAbsolutePath().normalize();
		try {
			Files.createDirectories(folder);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create upload directory");
		}

		// Make a safe unique filename
		String original = file.getOriginalFilename() == null ? "image" : file.getOriginalFilename();
		String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
		String safe = UUID.randomUUID() + ext.replaceAll("[^\\.a-zA-Z0-9_-]", "");

		Path target = folder.resolve(safe).normalize();

		// Save to disk
		try {
			file.transferTo(target.toFile());
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to store file");
		}

		// Build an absolute URL
		//String base = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		return "/uploads/" + safe;
	}

}
