package com.ss17.repo.customer;

import com.ss17.entity.Customer;
import com.ss17.model.CustomerRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Customer> findAll(int page, int size){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Customer c",  Customer.class)
                    .setFirstResult((page-1)*size)
                    .setMaxResults(size)
                    .getResultList();
        }
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

    @Override
    public List<Customer> findByRole(CustomerRole role, int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Customer c where c.role = :role", Customer.class)
                    .setParameter("role", role)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    @Override
    public List<Customer> findByName(String name, int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Customer c where c.username like :name", Customer.class)
                    .setParameter("name", "%" + name + "%")
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    @Override
    public List<Customer> findByNameAndRole(String name, CustomerRole role, int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Customer c where c.username like :name and c.role = :role", Customer.class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("role", role)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    @Override
    public long countCustomers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(c) from Customer c", Long.class).uniqueResult();
        }
    }

    @Override
    public long countCByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(c) from Customer c where c.username like :name", Long.class)
                    .setParameter("name", "%" + name + "%")
                    .uniqueResult();
        }
    }

    @Override
    public long countCByRole(CustomerRole role) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(c) from Customer c where c.role = :role", Long.class)
                    .setParameter("role", role)
                    .uniqueResult();
        }
    }

    @Override
    public long countCByNameAndRole(String name, CustomerRole role) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select count(c) from Customer c where c.username like :name and c.role = :role", Long.class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("role", role)
                    .uniqueResult();
        }
    }

    @Override
    public void update(Customer customer){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }catch(Exception ex){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }
}
