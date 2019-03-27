package com.crud.orders.controller;


import com.crud.orders.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private DbService service;





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
