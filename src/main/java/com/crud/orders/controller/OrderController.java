package com.crud.orders.controller;


import com.crud.orders.mapper.DeliveryMapper;
import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.*;
import com.crud.orders.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new AppUser());
        return "login";
    }

    @RequestMapping(value = "order/views/allOrders")
    public ModelAndView getAllOrders(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders());
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/allOrders", "model", model);
    }

    @RequestMapping(value = "order/views/lunchbanket")
    public ModelAndView getAllBanket(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Lunch&Banket")).collect(Collectors.toList()));
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/lunchbanket", "model", model);
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
    public ModelAndView getAllBreakfastService(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Breakfast Service")).collect(Collectors.toList()));
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/breakfastService", "model", model);
    }
    @RequestMapping(value = "order/views/kt")
    public ModelAndView getAllKT(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("K&T")).collect(Collectors.toList()));
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/kt", "model", model);
    }
    @RequestMapping(value = "order/views/dishwash")
    public ModelAndView getAllDishwash(Model model) {
        List<OrderDto> list = mapper.mapToOrderDtoList(service.findAllOrders().stream()
                .filter(order -> order.getDepartment().matches("Dishwash")).collect(Collectors.toList()));
        List<DeliveryDto> dList = dmapper.mapToDeliveryDtoList(service.findAllDeliveryies());
        model.addAttribute("list", list);
        model.addAttribute("dList", dList);
        return new ModelAndView("order/views/dishwash", "model", model);
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
        return redirect(orderDto.getDepartment());
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
        return new ModelAndView(redirect(order.getDepartment()));
    }

    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable Long id) {
        Order order = service.findOneorder(id);
        service.deleteOrder(order);
        return new ModelAndView(redirect(order.getDepartment()));
    }


    @RequestMapping(value = "/delivery/addDelivery/{orderId}", method = RequestMethod.POST)
    public ModelAndView addDeliveryies(ModelMap model, @PathVariable Long orderId) {
        Order order = service.findOneorder(orderId);
        service.createDelivery(orderId);
        return new ModelAndView(redirect(order.getDepartment()));
    }

    @RequestMapping(value = "/saveDelivery", method = RequestMethod.POST)
    public ModelAndView addSaveDelivery(@Valid Delivery deliveryDto, BindingResult result, ModelMap model,
                                        RedirectAttributes redirectAttributes) {

        dmapper.mapToDeliveryDto(service.saveDelivery(deliveryDto));
        return new ModelAndView(redirect(deliveryDto.getDepartment()));
    }

    @RequestMapping(value = "/deleteDelivery/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDelivery(@PathVariable Long id){
        Delivery delivery = service.findOneDelivery(id);
        service.deleteDelivery(delivery);
        return new ModelAndView(redirect(delivery.getDepartment()));
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

    public String redirect(String string){
        String redirecting = "";
        switch (string){
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
