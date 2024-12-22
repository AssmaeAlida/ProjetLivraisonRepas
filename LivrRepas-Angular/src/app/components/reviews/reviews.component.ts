import { Component } from '@angular/core';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css'],
})
export class ReviewsComponent {
  reviews = [
    {
      customerName: 'John Doe',
      orderId: 101,
      meals: ['Pizza', 'Burger', 'Salad'],
      comment: 'The food was amazing, and the delivery was super fast!',
    },
    {
      customerName: 'Jane Smith',
      orderId: 102,
      meals: ['Pasta', 'Garlic Bread'],
      comment: 'Great quality and taste. Will order again!',
    },
    {
      customerName: 'Mark Johnson',
      orderId: 103,
      meals: ['Sushi', 'Miso Soup'],
      comment: 'Fresh and delicious. Highly recommend!',
    },
  ];

  deleteReview(index: number): void {
    const confirmed = confirm('Are you sure you want to delete this review?');
    if (confirmed) {
      this.reviews.splice(index, 1); // Remove the review from the array
    }
  }
}
