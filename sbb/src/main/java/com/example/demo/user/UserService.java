package com.example.demo.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String password, String email) {
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // encode password
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		this.userRepository.save(user);
		return user;
	}

}
