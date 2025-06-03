package com.ss17.repo.customer;

import com.ss17.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerRepoImpl implements CustomerRepo{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll(){
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    @Override
    public Customer findById(int id){
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public void save(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if(customer.getId() == 0){
            session.save(customer);
            transaction.commit();
        }else{
            session.update(customer);
            transaction.commit();
        }
        session.close();
    }

    @Override
    public void delete(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Customer login(String username, String password){
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer where username=:username and password=:password", Customer.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Customer customer = query.uniqueResult();
        session.close();
        return customer;
    }

    @Override
    public boolean isUsernameExist(String username){
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer where username=:username", Customer.class);
        query.setParameter("username", username);
        Customer customer = query.uniqueResult();
        session.close();
        return customer != null;
    }
}
