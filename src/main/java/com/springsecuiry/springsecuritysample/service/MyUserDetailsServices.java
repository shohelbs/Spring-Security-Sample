package com.springsecuiry.springsecuritysample.service;

import com.springsecuiry.springsecuritysample.model.entitiy.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsServices implements UserDetailsService {

    @Autowired
    CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByUserName(userName);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new User(customer.getUsername(),customer.getPassword(),new ArrayList<>());
    }
}
