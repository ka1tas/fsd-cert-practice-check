package com.cts.signup.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.User;

@Component
public class UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	public boolean save(User user) {

		try {
			LOGGER.info("Start of save() method in UserDao");
			LOGGER.debug("{user}", user);
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			LOGGER.info("End of save() method in userDao");
			return true;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
			return false;
		}
	}

	public User getByEmail(String email) {
		User user = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) session.createQuery("from User u where u.email = :email")
					.setParameter("email", email).list();
			if (users.size() > 0) {
				user = users.get(0);
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return user;
	}

	/*
	 * public List<User> findAll() { User user = null; try { Session session =
	 * sessionFactory.openSession(); Transaction transaction =
	 * session.beginTransaction();
	 * 
	 * @SuppressWarnings("unchecked") List<User> users = (List<User>)
	 * session.createQuery("from User").list(); if (users.size() > 0) { user =
	 * users.get(0); } transaction.commit(); return users; } catch
	 * (HibernateException e) { e.printStackTrace();
	 * System.out.println(e.getMessage()); System.out.println("error"); } return
	 * null; }
	 */

}
