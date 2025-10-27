package com.rewardsapp.rewards_backend.service;

import com.rewardsapp.rewards_backend.entity.Reward;
import com.rewardsapp.rewards_backend.entity.Transaction;
import com.rewardsapp.rewards_backend.repository.RewardRepository;
import com.rewardsapp.rewards_backend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final TransactionRepository transactionRepository;

    public RewardService(RewardRepository rewardRepository, TransactionRepository transactionRepository) {
        this.rewardRepository = rewardRepository;
        this.transactionRepository = transactionRepository;
    }

    public void calculateAndSaveReward(Long customerId, double purchaseAmount, LocalDate purchaseDate) {
        int month = purchaseDate.getMonthValue();
        int year = purchaseDate.getYear();

        // Fetch all transactions for that month & year
        List<Transaction> monthlyTransactions =
                transactionRepository.findByCustomerIdAndMonthAndYear(customerId, month, year);

        int totalPoints = 0;
        LocalDate latestDate = purchaseDate;

        for (Transaction t : monthlyTransactions) {
            totalPoints += calculatePoints(t.getPurchaseAmount());
            if (t.getPurchaseDate().isAfter(latestDate)) {
                latestDate = t.getPurchaseDate();
            }
        }

        String formattedDate = latestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Reward reward = rewardRepository.findByCustomerIdAndMonthAndYear(customerId, month, year)
                .orElse(new Reward(customerId, month, year, 0, formattedDate));

        reward.setTotalPoints(totalPoints);
        reward.setLastUpdatedDate(formattedDate);

        rewardRepository.save(reward);
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2) + 50;
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
