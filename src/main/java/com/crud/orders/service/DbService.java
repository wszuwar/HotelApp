package com.crud.orders.service;

import com.crud.orders.model.Delivery;
import com.crud.orders.model.Order;
import com.crud.orders.repository.DeliveryRepository;
import com.crud.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Order> findAllOrders() {
        return repository.findAll();
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public Order findOneorder(Long id) {
        return repository.findOne(id);
    }

    public void deleteOrder(Order ord) {
        repository.delete(ord);
    }

    public List<Delivery> findAllDeliveryies() {
        return deliveryRepository.findAll();
    }

    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery findOneDelivery(Long id) {
        return deliveryRepository.findOne(id);
    }

    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    @Transactional
    public void createDelivery(Long orderId) {
        Order order = findOneorder(orderId);
        saveDelivery(new Delivery(date(order.getSupplier()), order.getProduct(), order.getDepartment()));
        deleteOrder(order);
    }

    public LocalDate date( String string) {

        LocalDateTime localDate = LocalDateTime.now();
        if (localDate.getHour()<14){
        switch (localDate.getDayOfWeek()) {
            case MONDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(1) : LocalDateTime.now().plusDays(2);
                break;
            case TUESDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(1) : LocalDateTime.now().plusDays(3);
                break;
            case WEDNESDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(1) : LocalDateTime.now().plusDays(2);
                break;
            case THURSDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(1) : LocalDateTime.now().plusDays(4);
                break;
            case FRIDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(1) : LocalDateTime.now().plusDays(5);
                break;
            case SATURDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(4);
                break;
            case SUNDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(3);
                break;
        }
        return localDate.toLocalDate();
        } else {
            switch (localDate.getDayOfWeek()) {
            case MONDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(2);
                break;
            case TUESDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(3);
                break;
            case WEDNESDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(2);
                break;
            case THURSDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(4);
                break;
            case FRIDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(5);
                break;
            case SATURDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(4);
                break;
            case SUNDAY:
                localDate = (string.matches("Bama")) ? LocalDateTime.now().plusDays(2) : LocalDateTime.now().plusDays(3);
                break;
        }

        }return localDate.toLocalDate();
    }
}
