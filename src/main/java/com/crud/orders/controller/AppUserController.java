package com.crud.orders.controller;

import com.crud.orders.model.AppUser;
import com.crud.orders.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class AppUserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
   private UserServiceImpl userService;

    @RequestMapping(value = "user/views/allUsers")
    public ModelAndView getAllUsers(Model model) {
        List<AppUser> list = userService.findAllUsers();
        return new ModelAndView("user/views/allUsers", "list", list);
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.GET)
    public String newUserRegistration(ModelMap model) {
        AppUser appUser = new AppUser();
        model.addAttribute("user", appUser );
        return "user/addUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveOrderRegistration(@Valid AppUser appUser, BindingResult result, ModelMap model,
                                        RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "user/addUser";
        }
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        userService.saveUser(appUser);
        return "redirect:/user/views/allUsers";
    }

    @RequestMapping(value = "/user/editUser/{id}")
    public String editUser(@PathVariable Long id, ModelMap model) {
        userService.findOneUser(id);
        model.addAttribute("user", userService.findOneUser(id));
        return "user/editUser";
    }

    @RequestMapping(value = "/editUserSave", method = RequestMethod.POST)
    public ModelAndView editUserSave(@ModelAttribute("user") AppUser o) {
        AppUser appUser = userService.findOneUser(o.getId());


        appUser.setUsername(o.getUsername());
        appUser.setPassword(o.getPassword());
        appUser.setRoles(o.getRoles());
        appUser.setActive(o.isActive());

        userService.saveUser(appUser);
        return new ModelAndView("redirect:/user/views/allUsers");

    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        AppUser appUser = userService.findOneUser(id);
        userService.deleteUser(appUser);
        return new ModelAndView("redirect:/user/views/allUsers");
    }

    @ModelAttribute("addUserRole")
    public List<String> initializeRoles(){
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        roles.add("MANAGER");
        return roles;
    }



}
