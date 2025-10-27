import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RewardsService } from '../../services/rewards.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class HomeComponent implements OnInit {
  customers: any[] = [];
  rewards: any[] = [];
  filteredRewards: any[] = [];

  selectedMonth: string = '';
  selectedYear: string = '';

  months = [
    { name: 'January', value: 1 },
    { name: 'February', value: 2 },
    { name: 'March', value: 3 },
    { name: 'April', value: 4 },
    { name: 'May', value: 5 },
    { name: 'June', value: 6 },
    { name: 'July', value: 7 },
    { name: 'August', value: 8 },
    { name: 'September', value: 9 },
    { name: 'October', value: 10 },
    { name: 'November', value: 11 },
    { name: 'December', value: 12 }
  ];

  years: number[] = [2023, 2024, 2025];

  constructor(private rewardsService: RewardsService) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.rewardsService.getCustomers().subscribe(data => {
      this.customers = data;
    });

    this.rewardsService.getRewards().subscribe(data => {
      this.rewards = data;
      this.filteredRewards = data;
      console.log('Rewards:', this.rewards);
    });
  }

  filterRewards() {
    this.filteredRewards = this.rewards.filter(r => {
      const matchMonth = this.selectedMonth ? r.month == this.selectedMonth : true;
      const matchYear = this.selectedYear ? r.year == this.selectedYear : true;
      return matchMonth && matchYear;
    });
  }
}
