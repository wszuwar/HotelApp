package com.crud.orders.controller;

import com.crud.orders.dao.OrderDao;
import com.crud.orders.model.Order;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String newRegistration(ModelMap model){
        Order order = new Order();

        model.addAttribute("order",order);
        return "add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRegistration(@Valid Order order, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "add";
        }
        orderDao.save(order);
        return view(order);
    }
    @RequestMapping(value = "/views/breakfastOrder")
    public ModelAndView getAll() {
        List<Order> list = orderDao.findAll().stream().filter(s->s.getDepartment().contains("Breakfast")).collect(Collectors.toList());
        return new ModelAndView("breakfastOrder", "list", list);
    }
    @RequestMapping(value = "/editOrder/{id}")
    public String edit(@PathVariable Long id , ModelMap model){
        Order order = orderDao.findOne(id);
        model.addAttribute("order", order);
        return "editOrder";
    }
    @RequestMapping (value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("order") Order o){
        Order order = orderDao.findOne(o.getId());

        order.setDepartment(o.getDepartment());
        order.setProduct(o.getProduct());
        order.setSupplier(o.getSupplier());
        order.setStatus(o.getStatus());

        orderDao.save(order);
        return new ModelAndView(view(order));
    }
    @RequestMapping(value = "/deleteAll")
    public ModelAndView deleteAll(){
        List<Order> list = orderDao.deleteAll();
        return new ModelAndView("redirect:/viewOrders", "list", list);
    }


    public String view(@ModelAttribute("order") Order o){
        String s = "";
        switch (o.getDepartment()){
            case "Breakfast":
                 s ="redirect:/views/breakfastOrder";
                break;
            case "Lunch&Banket" :
                s ="redirect:/views/lunch&banket";
                break;
            case "K&T" :
                 s ="redirect:/views/k&t";
                break;
            case "Dishwash":
                 s =  "redirect/views/dishwash";
                break;
            case "Breakfast Service":
                s = "redirect:/views/breakfastService";
        }
            return s;
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
