package com.rewardsapp.rewards_backend.repository;

import com.rewardsapp.rewards_backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.customer.id = :customerId AND MONTH(t.purchaseDate) = :month AND YEAR(t.purchaseDate) = :year")
    List<Transaction> findByCustomerIdAndMonthAndYear(@Param("customerId") Long customerId,
                                                      @Param("month") int month,
                                                      @Param("year") int year);
}


