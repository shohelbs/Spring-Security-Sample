package com.springsecuiry.springsecuritysample.repository;

import com.springsecuiry.springsecuritysample.model.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findCustomerByUsername(String username);

}
