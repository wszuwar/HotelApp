package com.crud.orders.controller;


import com.crud.orders.mapper.DeliveryMapper;
import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.Delivery;
import com.crud.orders.model.DeliveryDto;
import com.crud.orders.model.OrderDto;
import com.crud.orders.model.Order;
import com.crud.orders.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class OrderController {
    @Autowired
    private DbService service;

    @Autowired
    private DeliveryMapper dmapper;


    @Autowired
    private OrderMapper mapper;

    @RequestMapping(value = "order/views/allOrders")
    public ModelAndView getAllOrders() {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders());
        return new ModelAndView("order/views/allOrders", "list", list);
    }

    @RequestMapping(value = "order/views/lunchbanket")
    public ModelAndView getAllBanket() {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Lunch&Banket")).collect(Collectors.toList()));
        return new ModelAndView("order/views/lunchbanket", "list", list);
    }
    @RequestMapping(value = "order/views/breakfastOrder")
    public ModelAndView getAllBreakfast(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Breakfast"))
                .filter(order -> order.getProduct().length()!=0).collect(Collectors.toList()));
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/breakfastOrder", "model", model);

    }
    @RequestMapping(value = "order/views/breakfastService")
    public ModelAndView getAllBreakfastService() {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Breakfast Service")).collect(Collectors.toList()));
        return new ModelAndView("order/views/breakfastService", "list", list);
    }
    @RequestMapping(value = "order/views/kt")
    public ModelAndView getAllKT() {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("K&T")).collect(Collectors.toList()));
        return new ModelAndView("order/views/kt", "list", list);
    }
    @RequestMapping(value = "order/views/dishwash")
    public ModelAndView getAllDishwash() {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Dishwash")).collect(Collectors.toList()));
        return new ModelAndView("order/views/dishwash", "list", list);
    }



    @RequestMapping(value = "/order/addOrder", method = RequestMethod.GET)
    public String newOrderRegistration(ModelMap model) {
        OrderDto orderDto = new OrderDto();
        model.addAttribute("order", mapper.mapToOrder(orderDto));
        return "order/addOrder";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrderRegistration(@Valid Order orderDto, BindingResult result, ModelMap model,
                                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "order/addOrder";
        }
        mapper.mapToOrderDto(service.saveOrder(orderDto));
        return redirect(orderDto);
    }

    @RequestMapping(value = "/order/editOrder/{id}")
    public String editOrder(@PathVariable Long id, ModelMap model) {
        mapper.mapToOrderDto(service.findOneorder(id));
        model.addAttribute("orderDto", mapper.mapToOrderDto(service.findOneorder(id)));

        return "order/editOrder";
    }

    @RequestMapping(value = "/editOrderSave", method = RequestMethod.POST)
    public ModelAndView editOrderSave(@ModelAttribute("orderDto") Order o) {
        Order order = service.findOneorder(o.getId());


        order.setDepartment(o.getDepartment());
        order.setProduct(o.getProduct());
        order.setSupplier(o.getSupplier());
        order.setStatus(o.getStatus());

        mapper.mapToOrderDto(service.saveOrder(order));
        return new ModelAndView(redirect(order));
    }

    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable Long id) {
        Order order = service.findOneorder(id);
        service.deleteOrder(order);
        return new ModelAndView(redirect(order));
    }
    @RequestMapping(value = "/delivery/addDelivery/{orderId}", method = RequestMethod.POST)
    public ModelAndView addDeliveryies(ModelMap model, @PathVariable Long orderId) {
        service.createDelivery(orderId);
        return new ModelAndView("redirect:/order/views/breakfastOrder");
    }

    @RequestMapping(value = "/saveDelivery", method = RequestMethod.POST)
    public ModelAndView addSaveDelivery(@Valid Delivery deliveryDto, BindingResult result, ModelMap model,
                                        RedirectAttributes redirectAttributes) {

        dmapper.mapToDeliveryDto(service.saveDelivery(deliveryDto));
        return new ModelAndView("redirect:/order/views/breakfastOrder");
    }


    @ModelAttribute("departments")
    public List<String> initializeDepartments() {
        List<String> departments = new ArrayList<>();
        departments.add("Breakfast");
        departments.add("Lunch&Banket");
        departments.add("K&T");
        departments.add("Breakfast Service");
        departments.add("Dishwash");
        return departments;
    }

    @ModelAttribute("suppliers")
    public List<String> initializeSupplier() {
        List<String> suppliers = new ArrayList<>();
        suppliers.add("Asko");
        suppliers.add("Bama");
        suppliers.add("Others");
        return suppliers;
    }

    public String redirect(Order order){
        String redirecting = "";
        switch (order.getDepartment()){
            case "Breakfast":
                redirecting = "redirect:/order/views/breakfastOrder";
                break;
            case "Lunch&Banket":
                redirecting = "redirect:/order/views/lunchbanket";
                break;
            case "K&T":
                redirecting = "redirect:/order/views/kt";
                break;
            case "Breakfast Service":
                redirecting = "redirect:/order/views/breakfastService";
                break;
            case "Dishwash":
                redirecting = "redirect:/order/views/dishwash";
                break;
        }
        return redirecting;
    }




}
