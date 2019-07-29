package com.crud.orders.service;

import com.crud.orders.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasteDbService {

    @Autowired
    private WasteRepository wasteRepository;


}
