package com.lakomydev.cleancode.service;

import com.lakomydev.cleancode.model.Customer;
import com.lakomydev.cleancode.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidClassException;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void handle(Customer customer) throws InvalidClassException {
        final Pattern pt = Pattern.compile("^(.+)@(\\S+)$");

        if(pt.matcher(customer.getEmail()).matches()){
            log.info("Address email is ok !");
            customerRepository.saveCustomer(customer);
        } else {
            log.warn("This email is invalid !");
            customer.setValid(false);
            throw new InvalidClassException("This customer is not valid!");
        }
    }
}
