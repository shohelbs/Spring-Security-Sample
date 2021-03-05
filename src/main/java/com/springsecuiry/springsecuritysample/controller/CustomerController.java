package com.springsecuiry.springsecuritysample.controller;

import com.springsecuiry.springsecuritysample.model.AuthenticationRequest;
import com.springsecuiry.springsecuritysample.model.AuthenticationResponse;
import com.springsecuiry.springsecuritysample.model.SignUpRequest;
import com.springsecuiry.springsecuritysample.model.entitiy.Customer;
import com.springsecuiry.springsecuritysample.service.CustomerService;
import com.springsecuiry.springsecuritysample.service.MyUserDetailsServices;
import com.springsecuiry.springsecuritysample.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsServices userDetailsServices;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello Message";
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) throws Exception{
        String message;
        Customer customer = customerService.getCustomerByUserName(request.getUsername());
        if (customer ==null){
            customerService.saveCustomer(request);
            message = "Customer Added Successful";
        }else {
            message = "User already exist";
        }

        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user name or pass",e);
        }

        UserDetails userDetails = userDetailsServices.loadUserByUsername(request.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
