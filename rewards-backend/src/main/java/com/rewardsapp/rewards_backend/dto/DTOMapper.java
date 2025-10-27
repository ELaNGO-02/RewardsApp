package com.rewardsapp.rewards_backend.dto;

import com.rewardsapp.rewards_backend.entity.Customer;
import com.rewardsapp.rewards_backend.entity.Transaction;

public class DTOMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setTransactions(
                customer.getTransactions().stream()
                        .map(DTOMapper::toTransactionDTO)
                        .toList()
        );
        return dto;
    }

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setPurchaseAmount(transaction.getPurchaseAmount());
        dto.setPurchaseDate(transaction.getPurchaseDate());
        dto.setCustomerId(transaction.getCustomer().getId());
        dto.setCustomerName(transaction.getCustomer().getName());
        return dto;
    }
}

