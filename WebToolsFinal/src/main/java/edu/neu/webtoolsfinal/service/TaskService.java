/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.service;

import edu.neu.webtoolsfinal.entity.Address;
import edu.neu.webtoolsfinal.entity.Task;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.repository.AddressRepository;
import edu.neu.webtoolsfinal.repository.TaskRepository;
import edu.neu.webtoolsfinal.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jiayangshen
 */
@Service("taskService")
public class TaskService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    AddressRepository addressRepository;

    public Address addAddress(String street, String city, String state, String country, String zipcode) {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setCountry(country);
        address.setZipcode(zipcode);
        address.setState(state);
        addressRepository.save(address);
        return address;
    }

    public void addTask(String category, String weight, Address fromAddress, Address toAddress, User user, Date date) {

        Task task = new Task();
        task.setCategory(category);
        task.setTaskDate(date);
        task.setWeight(weight);
        task.setFromAddress(fromAddress);
        task.setToAddress(toAddress);
        task.setFromUser(user);
        taskRepository.save(task);
    }

    public List<Task> findTasksByUser(User user) {
        Criteria criteria = taskRepository.getSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("fromUser", user));

        criteria.addOrder(Order.desc("taskDate"));
        final List list = criteria.list();
        return list;
    }

    public List<Task> viewTasks() {
        Criteria criteria = taskRepository.getSession().createCriteria(Task.class);
        criteria.addOrder(Order.desc("taskDate"));
        final List list = criteria.list();
        return list;
    }

    public Task get(int id) {
        return taskRepository.get(Task.class, id);
    }

    public List<Task> viewAcceptedTasks(User user) {
        Criteria criteria = taskRepository.getSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("acceptCompany", user));
        criteria.addOrder(Order.desc("taskDate"));
        final List list = criteria.list();
        return list;
    }

    public void update(Task task) {
        taskRepository.save(task);

    }

}
