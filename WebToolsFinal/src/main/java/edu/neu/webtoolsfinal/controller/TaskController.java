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
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/post-task", method = RequestMethod.GET)
    public String postTask() {
        return "post-task";
    }

    @RequestMapping(value = "/post-task", method = RequestMethod.POST)
    public String createTask(@AuthenticationPrincipal Principal principal, @RequestParam("category") String category,
            @RequestParam("weight") String weight, @RequestParam("fromStreet") String fromStreet, @RequestParam("fromCity") String fromCity,
            @RequestParam("fromState") String fromState, @RequestParam("fromCountry") String fromCountry, @RequestParam("fromZipcode") String fromZipcode, @RequestParam("toStreet") String toStreet,
            @RequestParam("toCity") String toCity,
            @RequestParam("toState") String toState, @RequestParam("toCountry") String toCountry, @RequestParam("toZipcode") String toZipcode,
            RedirectAttributes redirectAttributes, Model model) {

        final String username = principal.getName();
        User user = userRepository.findByUsername(username);

        Date date = new Date();
        Address toAddress = taskService.addAddress(toStreet, toCity, toState, toCountry, toZipcode);
        Address fromAddress = taskService.addAddress(fromStreet, fromCity, fromState, fromCountry, fromZipcode);
        taskService.addTask(category, weight, fromAddress, toAddress, user, date);
        model.addAttribute("msg", "Task Post successfully!");
        return "redirect:/view-mytasks";

    }

    @RequestMapping(value = "/viewAllTasks", method = RequestMethod.GET)
    public String viewAllTasks(@AuthenticationPrincipal Principal principal, Model model, RedirectAttributes redirectAttributes) {

        List<Task> list = taskService.viewTasks();
        model.addAttribute("allTaskList", list);
        return "viewAllTasks";
    }

    @RequestMapping(value = "/viewAllTasks", method = RequestMethod.POST)
    public String companyViewTasks(@AuthenticationPrincipal Principal principal,
            @RequestParam("task") int[] taskIds,
            RedirectAttributes redirectAttributes) {

        final String username = principal.getName();
        User user = userRepository.findByUsername(username);

        for (int taskId : taskIds) {
            Task task = taskService.get(taskId);
            task.setAcceptCompany(user);
            taskService.update(task);
        }
        return "redirect:/viewAllTasks";

    }

    @RequestMapping(value = "/view-mytasks")
    public String viewTasks(@AuthenticationPrincipal Principal principal, Model model, RedirectAttributes redirectAttributes) {
        final String username = principal.getName();
        User user = userRepository.findByUsername(username);
        List<Task> list = taskService.findTasksByUser(user);
        model.addAttribute("taskList", list);
        return "view-mytasks";
    }

    @RequestMapping(value = "/view-acceptedTasks")
    public String viewAcceptedTasks(@AuthenticationPrincipal Principal principal, Model model, RedirectAttributes redirectAttributes) {
        final String username = principal.getName();
        User user = userRepository.findByUsername(username);
        List<Task> list = taskService.viewAcceptedTasks(user);
        model.addAttribute("taskList", list);
        return "view-acceptedTasks";
    }

}
