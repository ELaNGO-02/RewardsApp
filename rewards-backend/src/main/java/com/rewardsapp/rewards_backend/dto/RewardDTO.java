package com.rewardsapp.rewards_backend.dto;

import java.time.LocalDate;
import java.util.List;

public class RewardDTO {
    private Long customerId;
    private Integer month;
    private Integer year;
    private Integer totalPoints;
    private List<TransactionInfo> transactions;

    // Inner class for transaction details
    public static class TransactionInfo {
        private Long id;
        private Double purchaseAmount;
        private LocalDate purchaseDate;

        public TransactionInfo() {}
        public TransactionInfo(Long id, Double purchaseAmount, LocalDate purchaseDate) {
            this.id = id;
            this.purchaseAmount = purchaseAmount;
            this.purchaseDate = purchaseDate;
        }

        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Double getPurchaseAmount() { return purchaseAmount; }
        public void setPurchaseAmount(Double purchaseAmount) { this.purchaseAmount = purchaseAmount; }
        public LocalDate getPurchaseDate() { return purchaseDate; }
        public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    }

    public RewardDTO() {}
    public RewardDTO(Long customerId, Integer month, Integer year, Integer totalPoints, List<TransactionInfo> transactions) {
        this.customerId = customerId;
        this.month = month;
        this.year = year;
        this.totalPoints = totalPoints;
        this.transactions = transactions;
    }

    // Getters and setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getTotalPoints() { return totalPoints; }
    public void setTotalPoints(Integer totalPoints) { this.totalPoints = totalPoints; }
    public List<TransactionInfo> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionInfo> transactions) { this.transactions = transactions; }
}

