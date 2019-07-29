package com.crud.orders.repository;

import com.crud.orders.model.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasteRepository extends JpaRepository<Waste, Long> {

    @Override
    List<Waste> findAll();

    @Override
    Waste findOne(Long id);

    @Override
    Waste save(Waste waste);

    @Override
    void delete(Waste waste);
}
