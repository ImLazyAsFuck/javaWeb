package com.ss17.repo.product;

import com.ss17.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(int page, int pageSize) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Product", Product.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    @Override
    public List<Product> findAll(){
        Session session = sessionFactory.openSession();
        return session.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(int id){
        Session session = sessionFactory.openSession();
        return session.get(Product.class, id);
    }

    @Override
    public long countAll(){
        Session session = sessionFactory.openSession();
        return session.createQuery("select count(p.id) from Product p", Long.class).getSingleResult();
    }

    @Override
    public boolean isExist(String name){
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count(p.id) from Product p where p.productName=:name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        session.close();
        return count > 0;
    }

    @Override
    public void save(Product product){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Product product){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Product product){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> findByName(String search, int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product p where p.productName like :search", Product.class)
                    .setParameter("search", "%" + search + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }



    @Override
    public long countByName(String search) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select count(p) from Product p where p.productName like :search", Long.class)
                    .setParameter("search", "%" + search + "%")
                    .getSingleResult();
        }
    }

    @Override
    public long countByPrice(Double minPrice, Double maxPrice){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select count(p.id) from Product p where p.price > :minPrice and p.price < :maxPrice", Long.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getSingleResult();
        }
    }

    @Override
    public List<Product> findByPrice(Double minPrice, Double maxPrice, int page, int size){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery(
                    "from Product p where p.price > :minPrice and p.price < :maxPrice", Product.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        }
    }

    @Override
    public long countByNameAndPrice(String search, Double minPrice, Double maxPrice){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery(
                            "select count(p.id) from Product p where p.price > :minPrice and p.price < :maxPrice and p.productName = :search", Long.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("search",  "%" + search + "%")
                    .getSingleResult();
        }
    }

    @Override
    public List<Product> findByNameAndPrice(String search, Double minPrice, Double maxPrice, int page, int size){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery(
                            "from Product p where p.price > :minPrice and p.price < :maxPrice and p.productName like :search", Product.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("search", "%" + search + "%")
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

}
