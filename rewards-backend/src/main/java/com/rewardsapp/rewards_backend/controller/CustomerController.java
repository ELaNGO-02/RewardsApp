package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.dto.CustomerDTO;
import com.rewardsapp.rewards_backend.dto.DTOMapper;
import com.rewardsapp.rewards_backend.entity.Customer;
import com.rewardsapp.rewards_backend.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer CRUD APIs")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Operation(summary = "Create a new customer")
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody Customer customer) {
        Customer saved = customerRepository.save(customer);
        return DTOMapper.toCustomerDTO(saved);
    }

    @Operation(summary = "Get all customers")
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(DTOMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    // READ single customer by ID
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return DTOMapper.toCustomerDTO(customer);
    }

    // UPDATE customer
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        Customer saved = customerRepository.save(existing);
        return DTOMapper.toCustomerDTO(saved);
    }

    // DELETE customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
