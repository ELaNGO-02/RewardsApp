import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RewardsService {

  private baseUrl = 'http://localhost:8080/api'; 


  constructor(private http: HttpClient) {}


  getCustomers(): Observable<any> {
    return this.http.get(`${this.baseUrl}/customers`);
  }

  addCustomer(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/customers`, data);
  }

  deleteCustomer(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/customers/${id}`);
  }

  // --- Transactions ---
  getTransactions(): Observable<any> {
    return this.http.get(`${this.baseUrl}/transactions`);
  }

  addTransaction(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/transactions`, data, {
    headers: { 'Content-Type': 'application/json' }
  });
  }


  deleteTransaction(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/transactions/${id}`);
  }

  // --- Rewards ---
  getRewards(): Observable<any> {
    return this.http.get(`${this.baseUrl}/rewards`);
  }
}


