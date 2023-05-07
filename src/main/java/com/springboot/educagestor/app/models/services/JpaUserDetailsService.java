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

import com.springboot.educagestor.app.models.dao.IAdministradorDao;
import com.springboot.educagestor.app.models.dao.IPersonaDao;
import com.springboot.educagestor.app.models.entity.Administrador;
import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.util.constants.Constants;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IAdministradorDao adminDao;
	
	private Logger logger= LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities;
		
		Persona persona= personaDao.findByEmail(username);
		
		if (persona ==null) {
			logger.info("Esta persona no existe al buscar por email, buscando en administradores...");
			
			Administrador admin=adminDao.findByEmailOrUsername(username, username);
			
			if (admin!=null) {
				 authorities = Arrays.asList(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
				 return new User(admin.getUsername(), admin.getPassword(), admin.getEnabled(), true, true, true, authorities);
			}
			
			logger.info("Credenciales no existentes en el sistema");
			throw new UsernameNotFoundException("El usuario " + username + " no existe en el sistema.");
		}
		
		 authorities = Arrays.asList(new SimpleGrantedAuthority(persona.getRole()));
		
		return new User(persona.getEmail(), persona.getPassword(), persona.getEnabled(), true, true, true, authorities);
	}

}
