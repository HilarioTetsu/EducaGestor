package com.springboot.educagestor.app;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig{

	@Bean
	public UserDetailsService userDetailsService()throws Exception{
				
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User
	            .withUsername("hilaro")
	            .password(passwordEncoder().encode("1234"))
	            .roles("ALUMNO")
	            .build());
		
		manager.createUser(User
		        .withUsername("admin")
		        .password(passwordEncoder().encode("1234"))
		        .roles("ADMIN")
		        .build());
		
		
		return manager;
	}
	
	@Bean 
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  
         http.authorizeRequests()
         .antMatchers("/","/css/**","/js/**","/images/**","/login").permitAll().
         antMatchers("/alumno/**").hasAnyRole("ALUMNO")
         .anyRequest().authenticated()
         .and().formLogin().loginPage("/login").permitAll()
         .and().logout().permitAll()
         .and().exceptionHandling().accessDeniedPage("/error_403");
  
         return http.build();
     }

	
}
