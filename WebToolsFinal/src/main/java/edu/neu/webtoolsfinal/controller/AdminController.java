/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.controller;

import edu.neu.webtoolsfinal.entity.Role;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.service.TaskService;
import edu.neu.webtoolsfinal.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jiayangshen
 */
@Controller
public class AdminController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin-dashboard", method = RequestMethod.GET)
    public String admin(RedirectAttributes redirectAttributes) {

        return "admin-dashboard";
    }

    @RequestMapping(value = "/manageUser", method = RequestMethod.GET)
    public String manageCustomer(@AuthenticationPrincipal Principal principal,
            Model model, RedirectAttributes redirectAttributes) {

        List<User> list = userService.findAll();
        model.addAttribute("allUsers", list);
        return "manageUser";
    }

    @RequestMapping(value = "/manageUser", method = RequestMethod.POST)
    public String customers(@AuthenticationPrincipal Principal principal,
            @RequestParam("deleteUser") int userId,
            RedirectAttributes redirectAttributes) {

        User user = userService.findByUserId(userId);
        userService.deleteUser(user);

        return "redirect:/manageUser";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public String addCompany(RedirectAttributes redirectAttributes) {

        return "addCompany";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public String addCompany(@RequestParam("userName") String userName, @RequestParam("realName") String realName,
            @RequestParam("email") String email,
            @RequestParam("password") String passowrd, Model model, RedirectAttributes redirectAttributes) {

        Role userRole = new Role();
        userRole.setRoleName("Company");
        userService.addUser(userName, realName, email, userRole, passowrd);
        model.addAttribute("success", "Company added successful");
        return "redirect:/addCompany";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAmin(@RequestParam("userName") String userName, @RequestParam("realName") String realName,
            @RequestParam("email") String email,
            @RequestParam("password") String passowrd, Model model, RedirectAttributes redirectAttributes) {

        Role userRole = new Role();
        userRole.setRoleName("Admin");
        userService.addUser(userName, realName, email, userRole, passowrd);
        return "redirect:/addAdmin";
    }

}
