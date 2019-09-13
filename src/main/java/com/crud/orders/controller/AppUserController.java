package com.crud.orders.controller;

import com.crud.orders.model.AppUser;
import com.crud.orders.model.Role;
import com.crud.orders.service.impl.RoleService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppUserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
   private UserServiceImpl userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "user/views/allUsers")
    public ModelAndView getAllUsers(Model model) {
        List<AppUser> list = userService.findAllUsers();
        List<Role> rollList = roleService.findAllRoles();
        model.addAttribute("list",list);
        model.addAttribute("roleList",rollList);
        return new ModelAndView("user/views/allUsers", "model", model);
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
        List<Role> roleList = Arrays.asList(roleService.findOneRole(2L));
        appUser.setRoles(roleList);
        userService.saveUser(appUser);
        return "redirect:/user/views/allUsers";
    }

    @RequestMapping(value = "/user/editUser/{id}")
    public String editUser(@PathVariable Long id, ModelMap model) {
        userService.findOneUser(id);
        List<Role> rollList = roleService.findAllRoles();
        model.addAttribute("user", userService.findOneUser(id));
        model.addAttribute("rollList", rollList);
        return "user/editUser";
    }

    @RequestMapping(value = "/editUserSave", method = RequestMethod.POST)
    public ModelAndView editUserSave(@ModelAttribute("user") AppUser o) {
        AppUser appUser = userService.findOneUser(o.getId());


        appUser.setUsername(o.getUsername());
        appUser.setPassword(o.getPassword());
        appUser.setRoles(o.getRoles());
        appUser.setActive(o.isActive());
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        userService.saveUser(appUser);
        return new ModelAndView("redirect:/user/views/allUsers");

    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        AppUser appUser = userService.findOneUser(id);
        userService.deleteUser(appUser);
        return new ModelAndView("redirect:/user/views/allUsers");
    }




    @RequestMapping(value = "role/views/allRoles")
    public ModelAndView getAllRoles(Model model){
        List<Role> roleList = roleService.findAllRoles();
        return new ModelAndView("role/views/allRoles", "roleList",roleList);
    }

    @RequestMapping(value = "/role/addRole", method = RequestMethod.GET)
    public String newRoleRegistration(ModelMap model){
        Role userRole = new Role();
        model.addAttribute("userRole", userRole);

        return "role/addRole";
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRoleRegistration(@Valid Role role, BindingResult result, ModelMap model,
                                       RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            System.out.println("HAS ERRORS!");
            return "role/addRole";
        }

        roleService.saveRole(role);
        return "redirect:/role/views/allRoles";
    }
    @RequestMapping(value = "/role/editRole/{id}")
    public String editRole(@PathVariable Long id, ModelMap model){
        roleService.findOneRole(id);
        model.addAttribute("role", roleService.findOneRole(id));
        return "role/editRole";
    }

    @RequestMapping(value = "/editRoleSave", method = RequestMethod.POST)
    public ModelAndView editUserSave(@ModelAttribute("role") Role r){
        Role role = roleService.findOneRole(r.getId());

        role.setRole(r.getRole());

        roleService.saveRole(role);
        return new ModelAndView("redirect:/role/allRoles");
    }

    @RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRole(@PathVariable Long id){
        Role role = roleService.findOneRole(id);
        roleService.deleteRole(role);
        return new ModelAndView("redirect:/role/allRoles");
    }


}
