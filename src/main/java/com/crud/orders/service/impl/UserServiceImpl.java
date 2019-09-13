package com.crud.orders.service.impl;

import com.crud.orders.model.AppUser;
import com.crud.orders.model.Role;
import com.crud.orders.repository.RoleRepository;
import com.crud.orders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public AppUser saveUser(AppUser user) {
        
        return userRepository.save(user);
    }

    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }


    public AppUser findOneUser(Long id) {
        return userRepository.findOne(id);
    }



    public void deleteUser(AppUser appUser) {
        userRepository.delete(appUser);
    }


}
