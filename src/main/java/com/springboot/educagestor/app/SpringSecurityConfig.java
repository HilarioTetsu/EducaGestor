package com.springboot.educagestor.app;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;

import com.springboot.educagestor.app.models.services.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/login").permitAll()
				.antMatchers("/alumno/**").hasAnyRole("ALUMNO")
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/profesor/**").hasAnyRole("PROFESOR")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		
		return http.build();
	}

}
