package com.cts.signup.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.User;
import com.cts.signup.rest.SignupController;

@Component

public class UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	public boolean save(User user) {

		try {

			LOGGER.info("Start");
			LOGGER.debug("{user}", user);
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			LOGGER.info("End");
			return true;
			} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
			return false;
		}
		
	}

}
