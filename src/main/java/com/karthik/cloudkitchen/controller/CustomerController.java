package com.karthik.cloudkitchen.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.karthik.cloudkitchen.model.Customer;
import com.karthik.cloudkitchen.service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/api/customers/register")
    public ResponseEntity<?> registerCustomer(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("phoneNumber") String phoneNumber,
                                           @RequestParam("address") String address) {
    Customer existingCustomer = customerService.findByEmail(email);
    if (existingCustomer != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists. Please login.");
    } else {
        Customer newCustomer = new Customer(null, firstName, lastName, email, password, phoneNumber, address);
        customerService.registerCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful. Please check your email.");
    }
}

@PostMapping("/api/customers/login")
public void login(HttpServletResponse response,
                  @RequestParam String email, @RequestParam String password) throws IOException {
    if ("caterer@example.com".equals(email) && "password123".equals(password)) {
        response.sendRedirect("/caterer_welcome");
    } else {
        Customer customer = customerService.login(email, password);
        if (customer != null) {
            response.sendRedirect("/customer_welcome");
        } else {
            response.sendRedirect("/error");
        }
    }
}



    @GetMapping("/login")
    public String loginPage() {
        return "login"; // This will return login.html from templates directory
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // Return signup.html similarly
    }

    @GetMapping("/customer_welcome")
    public String welcomePage() {
        return "customer_welcome"; // Return welcome.html similarly
    }
    @GetMapping("/caterer_welcome")
    public String CaterewelcomePage() {
        return "caterer_welcome"; // Return welcome.html similarly
    }

    @GetMapping("/error")
    public String ErrorPage() {
        return "error"; // Return error.html similarly
    }

}

