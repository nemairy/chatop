package com.openclassrooms.Chatop.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.Chatop.model.UserN;
import com.openclassrooms.Chatop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserN user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		
		return User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.build();
	}

}
