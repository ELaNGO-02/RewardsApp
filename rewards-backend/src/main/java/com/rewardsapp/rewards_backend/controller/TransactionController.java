package com.rewardsapp.rewards_backend.controller;

import com.rewardsapp.rewards_backend.dto.DTOMapper;
import com.rewardsapp.rewards_backend.dto.TransactionDTO;
import com.rewardsapp.rewards_backend.entity.Customer;
import com.rewardsapp.rewards_backend.entity.Transaction;
import com.rewardsapp.rewards_backend.repository.CustomerRepository;
import com.rewardsapp.rewards_backend.repository.TransactionRepository;
import com.rewardsapp.rewards_backend.service.RewardService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
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
    public TransactionDTO addTransaction(@RequestBody Transaction transaction) {
        Customer customer = customerRepository.findById(transaction.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        transaction.setCustomer(customer);

        Transaction saved = transactionRepository.save(transaction);

        rewardService.calculateAndSaveReward(
                saved.getCustomer().getId(),
                saved.getPurchaseAmount(),
                saved.getPurchaseDate()
        );

        return DTOMapper.toTransactionDTO(saved);
    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(DTOMapper::toTransactionDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}




