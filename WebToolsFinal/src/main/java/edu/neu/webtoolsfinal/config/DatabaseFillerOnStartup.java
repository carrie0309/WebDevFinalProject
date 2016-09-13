/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.config;

import edu.neu.webtoolsfinal.entity.Role;
import edu.neu.webtoolsfinal.entity.Task;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.repository.RoleRepository;
import edu.neu.webtoolsfinal.repository.TaskRepository;
import edu.neu.webtoolsfinal.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author jiayangshen
 */
@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.findAll(User.class).isEmpty()) {
            initDatabase();
        }
    }

    public void initDatabase() {
        // init roles
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        roleRepository.save(adminRole);

        Role customerRole = new Role();
        customerRole.setRoleName("Customer");
        roleRepository.save(customerRole);

        Role companyRole = new Role();
        companyRole.setRoleName("Company");
        roleRepository.save(companyRole);

        // init users
        User xiaoyang = new User();
        xiaoyang.setUsername("xiaoyang");
        xiaoyang.setName("Jiayang Shen");
        xiaoyang.setPassword("123");
        xiaoyang.setRole(adminRole);
        userRepository.save(xiaoyang);

        User customer1 = new User();
        customer1.setUsername("xiaoxing");
        customer1.setName("Jiaxing Shen");
        customer1.setPassword("123");
        customer1.setRole(customerRole);
        userRepository.save(customer1);

        User company1 = new User();
        company1.setUsername("c1");
        company1.setName("Hello Delivery");
        company1.setPassword("123");
        company1.setRole(companyRole);
        userRepository.save(company1);

    }
}
