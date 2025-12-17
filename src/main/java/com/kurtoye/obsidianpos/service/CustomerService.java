package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer) throws Exception;
    Customer getCustomer(Long customerId) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    List<Customer> searchCustomer(String keyword) throws Exception;
    void deleteCustomer(Long customerId) throws Exception;
}
