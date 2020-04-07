package com.foody.swiggy;

import com.foody.swiggy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SwiggyApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SwiggyApplication.class, args);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = new User();

		user.setBirthDate(new Date());
		user.setCreatedDate(new Date());
		user.setCreatedBy("raji");
		user.setEmailAddress("my@cool.com");
		user.setFirstName("Raji");
		user.setLastName("Prash");
		user.setLastUpdatedBy("raji");
		user.setLastUpdatedDate(new Date());

		session.save(user);

		session.getTransaction().commit();
		session.close();
	}

}
