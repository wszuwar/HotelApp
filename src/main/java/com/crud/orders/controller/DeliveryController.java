package com.crud.orders.controller;

import com.crud.orders.mapper.DeliveryMapper;
import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.DeliveryDto;
import com.crud.orders.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeliveryController {
    @Autowired
    private DbService service;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private DeliveryMapper dmapper;

    @Autowired

    @RequestMapping(value = "delivery/views/allDeliveries")
    public ModelAndView getAllDeliverys(){
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        return new ModelAndView("delivery/views/allDeliveries","dlist",dList);
    }
}
