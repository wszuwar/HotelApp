package com.crud.orders.controller;

import com.crud.orders.mapper.DeliveryMapper;
import com.crud.orders.mapper.OrderMapper;
import com.crud.orders.model.Delivery;
import com.crud.orders.model.DeliveryDto;
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

    @RequestMapping(value = "delivery/addDeliveriy", method = RequestMethod.GET)
    public String newDeliveryRegistration( ModelMap model){
        DeliveryDto deliveryDto = new DeliveryDto();

        model.addAttribute("delivery", dmapper.mapToDelivery(deliveryDto));
        return "delivery/addDelivery";
    }

    @RequestMapping(value = "/saveDelivery", method = RequestMethod.POST)
    public String saveDeliveryRegistration(@Valid Delivery delivery, BindingResult result, ModelMap model,
                                           RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "delivery/addDelivery";
        }
        dmapper.mapToDeliveryDto(service.saveDelivery(delivery));
        return "redirect:/order/views/breakfastOrder";
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
