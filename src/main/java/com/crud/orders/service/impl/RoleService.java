package com.crud.orders.service.impl;

import com.crud.orders.model.Role;
import com.crud.orders.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role saveRole(String role){
        Role r = new Role();
        r.setRole(role);
        return roleRepository.save(r);
    }

    public Role findOneRole(Long id){
        return roleRepository.findOne(id);
    }

    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

}

