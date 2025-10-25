package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.entity.Customer;
import com.rewardsapp.rewards_backend.entity.Transaction;
import com.rewardsapp.rewards_backend.repository.CustomerRepository;
import com.rewardsapp.rewards_backend.repository.TransactionRepository;
import com.rewardsapp.rewards_backend.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final RewardService rewardService;

    public TransactionController(TransactionRepository transactionRepository,
                                 CustomerRepository customerRepository,
                                 RewardService rewardService) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.rewardService = rewardService;
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        // Load the customer from DB using the customerId
        Customer customer = customerRepository.findById(transaction.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        transaction.setCustomer(customer);

        Transaction saved = transactionRepository.save(transaction);

        // Calculate rewards
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

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }
}
