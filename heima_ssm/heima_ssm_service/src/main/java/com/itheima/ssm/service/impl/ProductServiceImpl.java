package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IproductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IproductService {
    @Autowired
    private IproductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {
        
        return productDao.findAll();
        
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }
}
