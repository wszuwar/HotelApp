package com.crud.orders.service;

import com.crud.orders.model.Delivery;
import com.crud.orders.model.Order;
import com.crud.orders.model.OrderDto;
import com.crud.orders.repository.DeliveryRepository;
import com.crud.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private DeliveryRepository deliveryRepository;

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
    public List<Delivery> findAllDeliveryies(){
        return deliveryRepository.findAll();
    }
    public Delivery saveDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }
    public Delivery findOneDelivery(Long id){
        return deliveryRepository.findOne(id);
    }
    public void deleteDelivery(Delivery delivery){
        deliveryRepository.delete(delivery);
    }

    @Transactional
    public void createDelivery(Long orderId)  {
        Order order = findOneorder(orderId);
        saveDelivery(new Delivery(LocalDate.now(), order.getProduct()));
        deleteOrder(order);
    }

}
