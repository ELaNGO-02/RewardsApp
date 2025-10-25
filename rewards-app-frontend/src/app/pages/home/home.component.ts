import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RewardsService } from '../../services/rewards.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,          // <-- make it standalone
  imports: [CommonModule]    // <-- now this works
})
export class HomeComponent implements OnInit {
  customers: any[] = [];
  rewards: any[] = [];

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
    });
  }
}


