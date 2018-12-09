package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import com.itheima.ssm.service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;


    //查询全部订单---未分页
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        System.out.println("订单管理findAll执行了");
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll();
        mv.addObject("ordersList",all);
        mv.setViewName("orders-list");
        return mv;

    }*/


    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true, defaultValue = "4") Integer size) throws Exception {
        System.out.println("分页订单管理findAll执行了");
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll(page,size);
        //分页bean
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;

    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;

    }


}
