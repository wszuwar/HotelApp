package com.crud.orders.service;

import com.crud.orders.model.Order;
import com.crud.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private OrderRepository repository;

    public List<Order> findAllOrders(){
        return repository.findAll();
    }


    public Order saveOrder( Order order){
        return repository.save(order);
    }

    public Order findOneorder(Long id) {
        return repository.findOne(id);
    }

    public void deleteOrder(Order ord){
        repository.delete(ord);
    }


}
