package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.dto.RewardDTO;
import com.rewardsapp.rewards_backend.entity.Reward;
import com.rewardsapp.rewards_backend.entity.Transaction;
import com.rewardsapp.rewards_backend.repository.RewardRepository;
import com.rewardsapp.rewards_backend.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rewards")
@CrossOrigin("*")
public class RewardController {

    private final RewardRepository rewardRepository;
    private final TransactionRepository transactionRepository;

    public RewardController(RewardRepository rewardRepository, TransactionRepository transactionRepository) {
        this.rewardRepository = rewardRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public List<RewardDTO> getAllRewards() {
        return rewardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/customer/{customerId}")
    public List<RewardDTO> getRewardsByCustomer(@PathVariable Long customerId) {
        return rewardRepository.findAll().stream()
                .filter(r -> r.getCustomerId().equals(customerId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/filter")
    public RewardDTO getRewardByCustomerAndMonthYear(
            @RequestParam Long customerId,
            @RequestParam int month,
            @RequestParam int year) {

        Reward reward = rewardRepository.findByCustomerIdAndMonthAndYear(customerId, month, year)
                .orElseThrow(() -> new RuntimeException("No rewards found for given filters"));

        return convertToDTO(reward);
    }

    // ---------------------- Helper ----------------------
    private RewardDTO convertToDTO(Reward reward) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndMonthAndYear(
                reward.getCustomerId(),
                reward.getMonth(),
                reward.getYear()
        );

        List<RewardDTO.TransactionInfo> transactionInfos = transactions.stream()
                .map(t -> new RewardDTO.TransactionInfo(
                        t.getId(),
                        t.getPurchaseAmount(),
                        t.getPurchaseDate()
                ))
                .collect(Collectors.toList());

        return new RewardDTO(
                reward.getCustomerId(),
                reward.getMonth(),
                reward.getYear(),
                reward.getTotalPoints(),
                transactionInfos
        );
    }
}


