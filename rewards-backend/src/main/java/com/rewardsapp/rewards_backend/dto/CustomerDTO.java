package com.rewardsapp.rewards_backend.dto;

import java.util.List;

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private List<TransactionDTO> transactions; // nested DTO

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<TransactionDTO> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionDTO> transactions) { this.transactions = transactions; }
}
