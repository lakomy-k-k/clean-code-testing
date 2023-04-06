package com.lakomydev.cleancode.service;

import com.lakomydev.cleancode.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InvalidClassException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CustomerService {

    private static long value = 1;

    public static long getNext() {
        return value++;
    }


    public void handle(Customer customer) throws InvalidClassException {
        final Pattern pt = Pattern.compile("^(.+)@(\\S+)$");
        if(pt.matcher(customer.getEmail()).matches()){
            log.info("Address email is ok !");
            customer.setValid(true);
            customer.setId(getNext());
            applyVipPoints(customer);
            this.saveCustomer(customer);
        } else {
            log.warn("This email is invalid !");
            customer.setValid(false);
            throw new InvalidClassException("This customer is not valid!");
        }
    }

    private void applyVipPoints(Customer customer) {
        switch(customer.getType()) {
            case "NORMAL" ->  customer.setVipPoints(10);
            case "BUSINESS" ->  customer.setVipPoints(25);
        }
    }

    private static final Map<Long, Customer> CUSTOMER_DATABASE = new HashMap<>();

    public Customer saveCustomer(Customer customer) {
        return CUSTOMER_DATABASE.put(customer.getId(), customer);
    }

}
