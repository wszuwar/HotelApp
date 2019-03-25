package com.crud.orders.dao;

import com.crud.orders.model.Order;
import com.crud.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDao {
    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findOne(Long id){
        return orderRepository.findOne(id);
    }

    public void delete(Order order){
        orderRepository.delete(order);
    }

    public List<Order> deleteAll(){
        orderRepository.deleteAll();
        return orderRepository.findAll();
    }
}
