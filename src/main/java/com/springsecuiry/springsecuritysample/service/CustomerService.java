package com.springsecuiry.springsecuritysample.service;

import com.springsecuiry.springsecuritysample.model.SignUpRequest;
import com.springsecuiry.springsecuritysample.model.entitiy.Customer;
import com.springsecuiry.springsecuritysample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByUserName(String userName){
        return customerRepository.findCustomerByUsername(userName);
    }

    public void saveCustomer(SignUpRequest request){
        customerRepository.save(getCustomerModel(request));
    }


    private Customer getCustomerModel(SignUpRequest request){
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setUsername(request.getUsername());
        customer.setPassword(request.getPassword());
//        customer.setEmail(request.getEmail());
        return customer;
    }
}
