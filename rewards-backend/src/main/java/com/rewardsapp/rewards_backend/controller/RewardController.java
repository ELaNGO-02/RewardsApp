package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.entity.Reward;
import com.rewardsapp.rewards_backend.repository.RewardRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
@CrossOrigin("*")
public class RewardController {

    private final RewardRepository rewardRepository;

    public RewardController(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    // GET all rewards
    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    // GET rewards by customer
    @GetMapping("/customer/{customerId}")
    public List<Reward> getRewardsByCustomer(@PathVariable Long customerId) {
        return rewardRepository.findAll()
                .stream()
                .filter(r -> r.getCustomerId().equals(customerId))
                .toList();
    }

    // GET rewards by customer + month + year
    @GetMapping("/filter")
    public Reward getRewardByCustomerAndMonthYear(
            @RequestParam Long customerId,
            @RequestParam int month,
            @RequestParam int year) {
        return rewardRepository.findByCustomerIdAndMonthAndYear(customerId, month, year)
                .orElseThrow(() -> new RuntimeException("No rewards found for given filters"));
    }
}
