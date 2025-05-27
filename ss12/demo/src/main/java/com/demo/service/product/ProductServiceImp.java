package com.demo.service.product;

import com.demo.dao.product.ProductDAO;
import com.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts(){
        return productDAO.findAllProducts();
    }

    @Override
    public Product getProductById(int id){
        return productDAO.findProductById(id);
    }

    @Override
    public boolean createProduct(Product product){
        return productDAO.saveProduct(product);
    }

    @Override
    public boolean updateProduct(Product product){
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id){
        return productDAO.deleteProduct(id);
    }
}
