import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RewardsService } from '../../services/rewards.service';

@Component({
  selector: 'app-manage-rewards',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './manage-rewards.component.html',
  styleUrls: ['./manage-rewards.component.scss']
})
export class ManageRewardsComponent implements OnInit {
  transactions: any[] = [];
  newTransaction = { customer: { id: null }, purchaseAmount: '', purchaseDate: '' };

  constructor(private rewardsService: RewardsService) {}

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions() {
    this.rewardsService.getTransactions().subscribe(data => {
      this.transactions = data;
    });
  }

  addTransaction() {
    // Convert purchaseAmount to number
    const transactionToSend = {
      customer: { id: Number(this.newTransaction.customer.id) },
      purchaseAmount: Number(this.newTransaction.purchaseAmount),
      purchaseDate: this.newTransaction.purchaseDate
    };

    this.rewardsService.addTransaction(transactionToSend).subscribe(() => {
      this.getTransactions(); // refresh list
      this.newTransaction = { customer: { id: null }, purchaseAmount: '', purchaseDate: '' };
    });
  }

  deleteTransaction(id: number) {
    this.rewardsService.deleteTransaction(id).subscribe(() => {
      this.getTransactions(); // refresh list
    });
  }
}
