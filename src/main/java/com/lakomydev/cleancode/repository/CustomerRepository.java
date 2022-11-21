package com.lakomydev.cleancode.repository;

import com.lakomydev.cleancode.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {

    private static final Map<Long, Customer> CUSTOMER_DATABASE = new HashMap<>();

    public Customer saveCustomer(Customer customer) {
        long id = SequenceGenerator.getNext();
        customer.setId(id);
        return CUSTOMER_DATABASE.put(id, customer);
    }

    class SequenceGenerator {
        private static long value = 1;

        public static long getNext() {
            return value++;
        }
    }
}
