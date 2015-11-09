package br.com.rodrigopeleias.runptor.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.rodrigopeleias.runptor.daos.UserDAO;
import br.com.rodrigopeleias.runptor.models.User;

public class CurrentUserDetailsService implements UserDetailsService {

	private UserDAO userDAO;

	@Inject
	public CurrentUserDetailsService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		return new CurrentUser(user);
	}

}