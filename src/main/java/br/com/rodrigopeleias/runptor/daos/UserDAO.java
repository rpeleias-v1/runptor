package br.com.rodrigopeleias.runptor.daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.rodrigopeleias.runptor.models.User;

public class UserDAO {

	@Inject
	private EntityManager manager;
	
	public List<User> all() {
		return manager.createQuery("select u from User u", User.class).getResultList();
	}

	public void save(User User) {
		manager.persist(User);
	}

	public User findById(Integer id) {
		return manager.find(User.class, id);
	}

	public void remove(User User) {
		manager.remove(User);
	}

	public void update(User User) {
		manager.merge(User);
	}
	
	public User findByUsername(String username) {
		try {
			TypedQuery<User> query = manager.createQuery("select u from User u where h.username = :username", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
	}
}