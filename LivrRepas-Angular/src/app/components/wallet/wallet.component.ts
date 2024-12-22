import { Component } from '@angular/core';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css'],
})
export class WalletComponent {
  payments = [
    {
      customerName: 'John Doe',
      email: 'john.doe@example.com',
      amount: 99.0,
      status: 'Active',
      date: '03/07/2024 01:40:54',
    },
    {
      customerName: 'Jane Smith',
      email: 'jane.smith@example.com',
      amount: 50.0,
      status: 'Canceled',
      date: '01/07/2024 14:25:00',
    },
    {
      customerName: 'Mark Johnson',
      email: 'mark.johnson@example.com',
      amount: 120.0,
      status: 'Active',
      date: '02/07/2024 10:15:20',
    },
  ];

  getTotalAmount(status: string): number {
    return this.payments
      .filter((payment) => payment.status === status)
      .reduce((total, payment) => total + payment.amount, 0);
  }
}
