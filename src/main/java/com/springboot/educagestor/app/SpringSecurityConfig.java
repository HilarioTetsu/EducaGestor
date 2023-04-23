package com.springboot.educagestor.app;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig{
/*
	@Bean
	public UserDetailsService userDetailsService()throws Exception{
				
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User
	            .withUsername("hilaro")
	            .password(passwordEncoder().encode("1234"))
	            .roles("ALUMNO")
	            .build());
		
		
		return manager;
	}
	
	@Bean 
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
*/
	
}
