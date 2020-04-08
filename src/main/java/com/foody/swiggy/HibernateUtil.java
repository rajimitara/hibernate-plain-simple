package com.foody.swiggy;

import com.foody.swiggy.model.Budget;
import com.foody.swiggy.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    //conventational way of coding for good understanding of hibernate internals

    private static final SessionFactory sessionFactory = buildSessionFactor();

    private static SessionFactory buildSessionFactor(){
        try{
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Budget.class);
            return  configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error creating sessionfactory");
        }
    }
    //single ton class so static returning sessionfactory
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
