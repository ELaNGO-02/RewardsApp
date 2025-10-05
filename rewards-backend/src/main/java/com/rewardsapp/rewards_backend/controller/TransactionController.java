package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.entity.Transaction;
import com.rewardsapp.rewards_backend.repository.TransactionRepository;
import com.rewardsapp.rewards_backend.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final RewardService rewardService;

    public TransactionController(TransactionRepository transactionRepository, RewardService rewardService) {
        this.transactionRepository = transactionRepository;
        this.rewardService = rewardService;
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionRepository.save(transaction);
        rewardService.calculateAndSaveReward(
                saved.getCustomer().getId(),
                saved.getPurchaseAmount(),
                saved.getPurchaseDate()
        );
        return saved;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
