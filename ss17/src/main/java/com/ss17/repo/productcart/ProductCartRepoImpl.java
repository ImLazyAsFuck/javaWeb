package com.ss17.repo.productcart;

import com.ss17.entity.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCartRepoImpl implements ProductCartRepo{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCart> findByCustomerId(int customerId) {
        List<ProductCart> productCarts = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            productCarts = session.createQuery("FROM ProductCart WHERE customer.id = :customerId", ProductCart.class)
                    .setParameter("customerId", customerId)
                    .getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return productCarts;
    }


    @Override
    public void save(ProductCart productCart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(productCart);
        transaction.commit();
        session.close();
    }


    @Override
    public void delete(ProductCart productCart){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(productCart);
        transaction.commit();
        session.close();
    }

    @Override
    public ProductCart findByCustomerIdAndProductId(int customerId, int productId) {
        Session session = sessionFactory.openSession();
        ProductCart result = session.createQuery(
                        "FROM ProductCart pc WHERE pc.customer.id = :customerId AND pc.product.id = :productId",
                        ProductCart.class
                )
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void deleteCartToEmpty(int customerId){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM ProductCart WHERE customer.id = :customerId")
                    .setParameter("customerId", customerId)
                    .executeUpdate();
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
