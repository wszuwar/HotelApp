package com.crud.orders.repository;

import com.crud.orders.model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<AppUser, Long>{

    public Optional<AppUser> findByUsername(String name);

    @Override
    public AppUser save(AppUser user);

    @Override
    List<AppUser> findAll();


    @Override
    AppUser findOne(Long id);


    @Override
    void delete(AppUser appUser);

    Optional<AppUser> findById(Long id);
}
