package com.ss17.service.productcart;

import com.ss17.entity.ProductCart;
import com.ss17.repo.productcart.ProductCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartServiceImpl implements ProductCartService{
    @Autowired
    private ProductCartRepo productCartRepo;

    @Override
    public List<ProductCart> findAll(){
        return productCartRepo.findAll();
    }

    @Override
    public void save(ProductCart productCart){
        productCartRepo.save(productCart);
    }

    @Override
    public void delete(ProductCart productCart){
        productCartRepo.delete(productCart);
    }

    @Override
    public ProductCart findById(int id){
       return productCartRepo.findById(id);
    }

    @Override
    public ProductCart findByCustomerIdAndProductId(int customerId, int productId){
        return productCartRepo.findByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public void update(ProductCart productCart){
        productCartRepo.update(productCart);
    }

    @Override
    public List<ProductCart> findByCustomerId(int customerId){
        return productCartRepo.findByCustomerId(customerId);
    }
}
