package com.rewardsapp.rewards_backend.entity;

import jakarta.persistence.*;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private int month;
    private int year;
    private int totalPoints;

    // Optional tracking field
    private String lastUpdatedDate;

    // Constructors
    public Reward() {}

    public Reward(Long customerId, int month, int year, int totalPoints, String lastUpdatedDate) {
        this.customerId = customerId;
        this.month = month;
        this.year = year;
        this.totalPoints = totalPoints;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getTotalPoints() { return totalPoints; }
    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }

    public String getLastUpdatedDate() { return lastUpdatedDate; }
    public void setLastUpdatedDate(String lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }
}
