import { Component } from '@angular/core';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
})
export class OrderListComponent {
  orders = [
    {
      id: 1,
      customerName: 'John Doe',
      meals: ['Pizza', 'Burger', 'Salad'],
      address: '123 Main Street, City',
      phoneNumber: '123-456-7890',
      date: new Date(),
      deliveryPerson: '',
      isAssigned: false, // Ajout de cette propriété

    },
    {
      id: 2,
      customerName: 'Jane Smith',
      meals: ['Pasta', 'Garlic Bread'],
      address: '456 Elm Street, Town',
      phoneNumber: '987-654-3210',
      date: new Date(),
      deliveryPerson: '',
      isAssigned: false, // Ajout de cette propriété

    },
  ];

  deliveryPersons = ['Alex', 'Maria', 'Daniel', 'Sophia'];
  
  assignDeliveryPerson(order: any) {
    if (order.deliveryPerson) {
      order.isAssigned = true; // Mark the order as assigned
      alert(`The delivery person ${order.deliveryPerson} has been assigned to order ID: ${order.id}`);
      // Optionally, call an API here to save the assignment in a database
    } else {
      alert('Please select a delivery person before proceeding.');
    }
  }
  
  
  
}
