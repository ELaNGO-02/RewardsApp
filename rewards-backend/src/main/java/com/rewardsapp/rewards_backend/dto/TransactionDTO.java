package com.rewardsapp.rewards_backend.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private Double purchaseAmount;
    private LocalDate purchaseDate;
    private Long customerId;
    private String customerName; // optional, for display

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPurchaseAmount() { return purchaseAmount; }
    public void setPurchaseAmount(Double purchaseAmount) { this.purchaseAmount = purchaseAmount; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
