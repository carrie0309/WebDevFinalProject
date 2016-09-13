/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.service;

import edu.neu.webtoolsfinal.config.SecurityUser;
import edu.neu.webtoolsfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        edu.neu.webtoolsfinal.entity.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        return new SecurityUser(user);

    }

}
