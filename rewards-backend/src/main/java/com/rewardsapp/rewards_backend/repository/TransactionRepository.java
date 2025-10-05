package com.rewardsapp.rewards_backend.repository;

import com.rewardsapp.rewards_backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> { }

