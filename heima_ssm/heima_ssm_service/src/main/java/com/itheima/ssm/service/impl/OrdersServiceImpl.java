package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IordersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IordersDao iordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //分页插件
        PageHelper.startPage(page,size);
        return iordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return iordersDao.findById(ordersId);
    }
}
