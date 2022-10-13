package com.example.FinalProject.service;

import com.example.FinalProject.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailService implements UserDetails {
    private User user;
    public UserDetailService(User user){
        this.user = user;
    }
    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<Role>roles = this.user.getRoles();
        List<GrantedAuthority>authorities = new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    @Override
    public String getPassword(){
        return this.user.getPassword();
    }
    @Override
    public String getUsername(){
        return this.user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }
}
