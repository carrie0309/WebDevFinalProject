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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author NicolasZHANG
 */
@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Model model) {

        if (error != null) {
            model.addAttribute("msg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";

    }

    @RequestMapping(value = "/account-set", method = RequestMethod.GET)
    public String changePassword() {
        return "account-set";
    }

    @RequestMapping(value = "/account-set", method = RequestMethod.POST)
    public String changePassword(@AuthenticationPrincipal Principal principal, @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassowrd, RedirectAttributes redirectAttributes) {
        final String username = principal.getName();
        User user = userService.findByUsername(username);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassowrd);
            userService.update(user);
            redirectAttributes.addFlashAttribute("msg", "Your password has been updated! Please login with the new credentials.");
            return "redirect:/logout";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Your old password is not valid!");
            return "redirect:/account-set";
        }
    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

    

}
