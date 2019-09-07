package com.crud.orders.repository;

import com.crud.orders.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long>{

    @Override
    List<Role> findAll();

    @Override
    Role findOne(Long id);

    @Override
    Role save(Role role);

    Optional<Role> findById(Long id);

    @Override
    void delete(Role role);
}
