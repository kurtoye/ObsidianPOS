package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.models.Customer;
import com.kurtoye.obsidianpos.payload.response.ApiResponse;
import com.kurtoye.obsidianpos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok().body(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok().body(customerService.updateCustomer(id, customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(customerService.getCustomer(id));
    }


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam String keyword) throws Exception {
        return ResponseEntity.ok().body(customerService.searchCustomer(keyword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted successfully");
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(apiResponse);
    }

}
