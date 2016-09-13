/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.config;

import edu.neu.webtoolsfinal.entity.Role;
import edu.neu.webtoolsfinal.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author NicolasZHANG
 */
public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if (user != null) {
            this.setUserId(user.getUserId());
            this.setUsername(user.getUsername());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        Role userRole = this.getRole();
//        if (userRole != null) {
//
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRoleName());
//            authorities.add(authority);
//
//        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
