package com.rewardsapp.rewards_backend.repository;

import com.rewardsapp.rewards_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
