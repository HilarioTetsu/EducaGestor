package com.springboot.educagestor.app.models.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IPersonaDao;
import com.springboot.educagestor.app.models.entity.Persona;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IPersonaDao personaDao;
	
	private Logger logger= LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Persona persona= personaDao.findByEmail(username);
		
		if (persona ==null) {
			logger.info("persona es null al buscar por email");
		}
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(persona.getRole()));
		
		return new User(username, persona.getPassword(), persona.getEnabled(), true, true, true, authorities);
	}

}
