package com.ss17.repo.productcart;

import com.ss17.entity.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCartRepoImpl implements ProductCartRepo{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCart> findAll(){
        Session session = sessionFactory.openSession();
        List<ProductCart> productCarts = session.createQuery("from ProductCart", ProductCart.class).getResultList();
        session.close();
        return productCarts;
    }

    @Override
    public void save(ProductCart productCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(productCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ProductCart productCart){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(productCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public ProductCart findById(int id){
        Session session = sessionFactory.openSession();
        ProductCart productCart = session.get(ProductCart.class, id);
        session.close();
        return productCart;
    }

    @Override
    public ProductCart findByCustomerIdAndProductId(int customerId, int productId) {
        Session session = sessionFactory.openSession();
        ProductCart result = session.createQuery(
                        "from ProductCart where customerId = :customerId and productId = :productId", ProductCart.class)
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void update(ProductCart productCart) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(productCart);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<ProductCart> findByCustomerId(int customerId){
        Session session = sessionFactory.openSession();
        List<ProductCart> productCarts = session.createQuery(
                "from ProductCart where customerId = :customerId", ProductCart.class)
                .setParameter("customerId", customerId)
                .getResultList();
        session.close();
        return productCarts;
    }
}
