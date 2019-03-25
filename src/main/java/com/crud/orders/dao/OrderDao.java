package com.crud.orders.dao;

import com.crud.orders.model.Order;
import com.crud.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDao {
    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAllBreakfast(){
        return orderRepository.findAll().stream()
                .filter(s-> "Breakfast".contains(s.getDepartment()))
                .collect(Collectors.toList());
    }
    public List<Order> findAllService(){
        return orderRepository.findAll().stream()
                .filter(d-> "Breakfast Service".equals(d.getDepartment()))
                .collect(Collectors.toList());
    }
    public List<Order> findAllDishwash(){
        return orderRepository.findAll().stream()
                .filter(d-> "Dishwash".equals(d.getDepartment()))
                .collect(Collectors.toList());
    }
    public List<Order> findAllKt() {
        return orderRepository.findAll().stream()
                .filter(d -> "K&T".equals(d.getDepartment()))
                .collect(Collectors.toList());
    }
    public List<Order> findAllLunchBanket() {
        return orderRepository.findAll().stream()
                .filter(d -> "Lunch&Banket".equals(d.getDepartment()))
                .collect(Collectors.toList());
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
