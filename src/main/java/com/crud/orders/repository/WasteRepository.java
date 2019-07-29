package com.crud.orders.repository;

import com.crud.orders.model.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepository extends JpaRepository<Waste, Long> {

}
