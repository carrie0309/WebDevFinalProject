/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.controller;

import edu.neu.webtoolsfinal.entity.Address;
import edu.neu.webtoolsfinal.entity.Task;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.repository.UserRepository;
import edu.neu.webtoolsfinal.service.TaskService;
import java.security.Principal;
import java.util.Date;
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
public class UserController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/customer-dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "customer-dashboard";
    }

    @RequestMapping(value = "/company-dashboard", method = RequestMethod.GET)
    public String companyDashboard() {
        return "company-dashboard";
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String companyIndex(RedirectAttributes redirectAttributes) {

        return "redirect:/company-dashboard";
    }

   

}
