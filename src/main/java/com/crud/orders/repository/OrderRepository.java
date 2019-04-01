package com.crud.orders.repository;

import com.crud.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Order findOne(Long id);

   @Override
   Order save(Order order);

    Optional<Order> findById(Long id);

    @Override
    void delete(Order ord);


}
