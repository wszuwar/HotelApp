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

    public List<Order> getAllOrders(){
        return repository.findAll();
    }

    public Order getOrderById(final Long id){
        return repository.findOne(id);
    }
    public Order saveOrder(final Order order){
        return repository.save(order);
    }

    public Optional<Order> getOrder(final  Long id){
        return repository.findById(id);
    }

    public void deleteOrder(final Long id){
        repository.delete(id);
    }


}
