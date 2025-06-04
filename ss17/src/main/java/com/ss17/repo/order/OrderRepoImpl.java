package com.ss17.repo.order;


import com.ss17.entity.Order;
import com.ss17.model.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> findAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Order", Order.class).getResultList();
        }
    }

    @Override
    public List<Order> findAll(int page, int size){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Order", Order.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    @Override
    public Order findById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.get(Order.class, id);
        }
    }

    @Override
    public List<Order> findByCustomerId(int id){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Order o where customer=:id", Order.class)
                    .setParameter("id", id)
                    .getResultList();
        }
    }

    @Override
    public List<Order> findByCustomerId(int id, int page, int size){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Order o where customer=:id", Order.class)
                    .setParameter("id", id)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    @Override
    public int countByCustomerId(int customerId){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select count(o.id) from Order o where customer=:id", Order.class)
                    .setParameter("id", customerId)
                    .getFirstResult();
        }
    }

    @Override
    public void save(Order order){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Order order){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Order order){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public List<Order> searchOrders(String receiverName, Date orderDate, OrderStatus status, int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("from Order o where 1=1 ");

            if (receiverName != null && !receiverName.isEmpty()) {
                hql.append("and lower(o.recipientName) like :receiverName ");
            }
            if (orderDate != null) {
                hql.append("and o.orderDate = :orderDate ");
            }
            if (status != null) {
                hql.append("and o.status = :status ");
            }

            Query<Order> query = session.createQuery(hql.toString(), Order.class);

            if (receiverName != null && !receiverName.isEmpty()) {
                query.setParameter("receiverName", "%" + receiverName.toLowerCase() + "%");
            }
            if (orderDate != null) {
                query.setParameter("orderDate", orderDate);
            }
            if (status != null) {
                query.setParameter("status", status);
            }

            query.setFirstResult(page * size);
            query.setMaxResults(size);

            return query.list();
        }
    }


    @Override
    public int countSearchOrders(String receiverName, Date orderDate, OrderStatus status){
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("select count(o) from Order o where 1=1 ");

            if (receiverName != null && !receiverName.isEmpty()) {
                hql.append("and lower(o.recipientName) like :receiverName ");
            }
            if (orderDate != null) {
                hql.append("and o.orderDate = :orderDate ");
            }
            if (status != null) {
                hql.append("and o.status = :status ");
            }

            Query<Long> query = session.createQuery(hql.toString(), Long.class);

            if (receiverName != null && !receiverName.isEmpty()) {
                query.setParameter("receiverName", "%" + receiverName.toLowerCase() + "%");
            }
            if (orderDate != null) {
                query.setParameter("orderDate", orderDate);
            }
            if (status != null) {
                query.setParameter("status", status);
            }

            Long count = query.uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }


}
