package com.rewardsapp.rewards_backend.service;

import com.rewardsapp.rewards_backend.entity.Reward;
import com.rewardsapp.rewards_backend.repository.RewardRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public void calculateAndSaveReward(Long customerId, double purchaseAmount, LocalDate purchaseDate) {
        int month = purchaseDate.getMonthValue();
        int year = purchaseDate.getYear();

        // Reward rule:
        // 1 point for every dollar over $50
        // 2 points for every dollar over $100
        int points = 0;
        if (purchaseAmount > 100) {
            points += (int)((purchaseAmount - 100) * 2) + 50;
        } else if (purchaseAmount > 50) {
            points += (int)(purchaseAmount - 50);
        }

        // Find existing reward for that customer and month
        Optional<Reward> existingReward =
                rewardRepository.findByCustomerIdAndMonthAndYear(customerId, month, year);

        Reward reward;
        if (existingReward.isPresent()) {
            reward = existingReward.get();
            reward.setTotalPoints(reward.getTotalPoints() + points);
        } else {
            reward = new Reward();
            reward.setCustomerId(customerId);
            reward.setMonth(month);
            reward.setYear(year);
            reward.setTotalPoints(points);
        }

        rewardRepository.save(reward);
    }
}
