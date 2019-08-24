package com.check_stand.service;

import com.check_stand.dao.BaseDao;
import com.check_stand.dao.OrderDao;
import com.check_stand.entity.Order;

import java.util.List;


public class OrderService extends BaseDao {


    private GoodsService goodsService;

    private OrderDao orderDao;

    public OrderService() {
        this.goodsService = new GoodsService();
        this.orderDao = new OrderDao();
    }

    public List<Order> queryOrderByAccount(Integer accountId) {
        return this.orderDao.queryOrderByAccount(accountId);
    }

    /* public Order queryOrderById(String orderId) {
        return this.orderDao.queryOrder(orderId);
    }*/

    public boolean commitOrder(Order order) {
        //提交订单
        return this.orderDao.insertOrder(order);
    }


}
