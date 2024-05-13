package com.foodapp.service;

import java.util.List;

import com.foodapp.model.Order;
import com.foodapp.model.User;
import com.request.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest order,User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId) throws Exception;

    public List<Order> getResturantOrder(Long resturantId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
