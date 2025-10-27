import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ManageRewardsComponent } from './pages/manage-rewards/manage-rewards.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'manage', component: ManageRewardsComponent },
];

