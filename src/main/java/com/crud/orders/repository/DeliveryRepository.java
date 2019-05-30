package com.crud.orders.repository;

import com.crud.orders.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Override
    List<Delivery> findAll();

    @Override
    Delivery findOne(Long id);

    @Override
    Delivery save(Delivery delivery);

    @Override
    void delete(Delivery delivery);
}
