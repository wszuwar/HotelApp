package com.crud.orders.controller;


import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.OrderDto;
import com.crud.orders.model.Order;
import com.crud.orders.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private DbService service;

    @Autowired
    private OrderMapper mapper;

    @RequestMapping(value = "order/views/allOrders")
    public ModelAndView getAllOrders(){
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders());
        return new ModelAndView("order/views/allOrders","list",list);
    }
    @RequestMapping(value = "/order/addOrder", method = RequestMethod.GET)
    public String newOrderRegistration(ModelMap model){
        OrderDto orderDto = new OrderDto();
        model.addAttribute("order", mapper.mapToOrder(orderDto) );
        return "order/addOrder";
    }
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrderRegistration(@Valid Order orderDto, BindingResult result, ModelMap model,
                                        RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "order/addOrder";
        }
        mapper.mapToOrderDto(service.saveOrder(orderDto));
        return "redirect:/order/views/allOrders";
    }
    @RequestMapping(value = "/order/editOrder/{id}")
    public String editOrder(@PathVariable Long id, ModelMap model){
         mapper.mapToOrderDto(service.findOneorder(id));
        model.addAttribute("orderDto",mapper.mapToOrderDto(service.findOneorder(id)));
        return "order/editOrder";
    }

    @RequestMapping(value = "/editOrderSave", method = RequestMethod.POST)
    public ModelAndView editOrderSave(@ModelAttribute("orderDto") Order o){
        Order orderDto = service.findOneorder(o.getId());


        orderDto.setDepartment(o.getDepartment());
        orderDto.setProduct(o.getProduct());
        orderDto.setSupplier(o.getSupplier());
        orderDto.setStatus(o.getStatus());

        mapper.mapToOrderDto(service.saveOrder(orderDto));
        return new ModelAndView("redirect:/order/views/allOrders");
    }
    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable Long id){
        Order order = service.findOneorder(id);
        service.deleteOrder(order);
       return new ModelAndView("redirect:/order/views/allOrders");
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
