package com.ss17.repo.product;

import com.ss17.entity.Product;
import com.ss17.entity.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(int page, int pageSize){
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
        session.close();
        return products;
    }

    @Override
    public Product findById(int id){
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public long countAll(){
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count(p.id) from Product p", Long.class).getSingleResult();
        session.close();
        return count;
    }


}
