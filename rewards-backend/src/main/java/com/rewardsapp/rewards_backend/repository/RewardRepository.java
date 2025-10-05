package com.rewardsapp.rewards_backend.repository;

import com.rewardsapp.rewards_backend.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    Optional<Reward> findByCustomerIdAndMonthAndYear(Long customerId, int month, int year);
}