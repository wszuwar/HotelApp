package com.crud.orders.controller;


import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.Order;
import com.crud.orders.model.OrderDto;
import com.crud.orders.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private DbService service;

    @Autowired
    private OrderMapper mapper;

    @RequestMapping(value = "views/allOrders")
    public ModelAndView getAll(){
        List<OrderDto> list = mapper.mapToOrderDtoList(service.getAllOrders());
        return new ModelAndView("allOrders","list",list);
    }
    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public String newOrderRegistration(ModelMap model){
        OrderDto orderDto = new OrderDto();
        model.addAttribute("order", orderDto);
        return "addOrder";
    }
    @RequestMapping(value = "saveOrder", method = RequestMethod.POST)
    public String saveOrderRegistration(OrderDto orderDto){
        service.saveOrder(mapper.mapToOrder(orderDto));
        return "redirect:/views/allOrders";
    }



    @ModelAttribute("departments")
    public List<String> initializeDepartments(){
        List<String> departments = new ArrayList<>();
        departments.add("Breakfast");
        departments.add("Lunch&Banket");
        departments.add("K&T");
        departments.add("Breakfast Service");
        departments.add("Dishwash");
        return departments;
    }
    @ModelAttribute("suppliers")
    public List<String> initializeSupplier(){
        List<String> suppliers = new ArrayList<>();
        suppliers.add("Asko");
        suppliers.add("Bama");
        suppliers.add("Others");
        return suppliers;
    }


}
