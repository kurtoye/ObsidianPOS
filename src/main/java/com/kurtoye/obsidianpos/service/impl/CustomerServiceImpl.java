package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.models.Customer;
import com.kurtoye.obsidianpos.repository.CustomerRepository;
import com.kurtoye.obsidianpos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) throws Exception {
        Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow(() -> new Exception("Customer not found"));

        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setEmail(customer.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long customerId) throws Exception {
        return customerRepository.findById(customerId).orElseThrow(() -> new Exception("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomer(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public void deleteCustomer(Long customerId) throws Exception {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new Exception("Customer not found"));
        customerRepository.delete(customer);
    }
}
