package com.foody.swiggy;

import com.foody.swiggy.model.Budget;
import com.foody.swiggy.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SwiggyApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SwiggyApplication.class, args);
		int pageNumber = 3;
		int pageSize = 4;
		// Hibernate

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = new User();

		user.setBirthDate(new Date());
		user.setCreatedDate(new Date());
		user.setCreatedBy("raji");
		user.setEmailAddress("my@cool.com");
		user.setFirstName("Baby");
		user.setLastName("Prash");
		user.setLastUpdatedBy("raji");
		user.setLastUpdatedDate(new Date());
		session.save(user);
		System.out.println("User data persisted "+ user.getUserId());
		session.getTransaction().commit();

		session.beginTransaction();

		Budget budget = new Budget();

		budget.setName("YEARLY");
		budget.setPeriod("5");
		budget.setGoalAmount(BigDecimal.valueOf(5464.343));

		session.save(budget);
		System.out.println("HIBERNATE Budget data persisted Id = "+ budget.getBudgetId());
		session.getTransaction().commit();


		//create criteria

		Criteria criteria = session.createCriteria(User.class);
		criteria.addOrder(Order.desc("firstName"));

		Criterion criterion = Restrictions.ilike("firstName","raji");
		criteria.setFirstResult((pageNumber -1)* pageSize);
		criteria.setMaxResults(pageSize);
		List<User> userList = criteria.add(Restrictions.and(criterion)).list();
		System.out.println("Print User table rows");
		for(User t : userList){
			System.out.println(t.getFirstName());
		}

		session.close();
		//JPA with hibernate as underlying implementation

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("swiggy-persistence-unit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Budget budgetJpa = new Budget();

		budgetJpa.setName("YEARLY");
		budgetJpa.setPeriod("5");
		budgetJpa.setGoalAmount(BigDecimal.valueOf(5464.343));
		entityManager.persist(budgetJpa);
		transaction.commit();
		System.out.println("JPA Budget data persisted Id = "+ budgetJpa.getBudgetId());
		entityManager.close();



	}



}
