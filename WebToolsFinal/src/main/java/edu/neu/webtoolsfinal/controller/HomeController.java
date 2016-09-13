/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.controller;

import edu.neu.webtoolsfinal.entity.Role;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
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
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String turnToIndex() {
        return "index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String roleDashboard(@AuthenticationPrincipal Principal principal, HttpSession session, RedirectAttributes redirectAttributes) {

        final String username = principal.getName();
        User user = userService.findByUsername(username);

        session.setAttribute("username", username);
        redirectAttributes.addFlashAttribute("user", username);
        if (user.getRole().getRoleName().equals("Customer")) {
            return "redirect:/customer-dashboard";
        } else if (user.getRole().getRoleName().equals("Company")) {
            return "redirect:/company-dashboard";

        } else if (user.getRole().getRoleName().equals("Admin")) {
            return "redirect:/admin-dashboard";
        }
        return "login";

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("userName") String userName, @RequestParam("realName") String realName,
            @RequestParam("email") String email,
            @RequestParam("password") String passowrd, Model model, RedirectAttributes redirectAttributes) {

        Role userRole = new Role();
        userRole.setRoleName("Customer");
        userService.addUser(userName, realName, email, userRole, passowrd);
        redirectAttributes.addFlashAttribute("message", "Registrated successfully, Please log in");
        return "redirect:/login";
    }
}
