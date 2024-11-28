import { Component, OnInit } from '@angular/core';
import { FoodService } from './../services/food/food.service'; // Assurez-vous que le service est importé
import { Food } from './../shared/models/Food';

@Component({
  selector: 'app-food-filter',
  templateUrl: './food-filter.component.html',
  styleUrls: ['./food-filter.component.css']
})
export class FoodFilterComponent implements OnInit {
  foods: Food[] = [];
  filteredFoods: Food[] = [];
  
  // Plage de prix initiale
  priceRange = { min: 0, max: 50 };

  constructor(private foodService: FoodService) {}

  ngOnInit(): void {
    this.foods = this.foodService.getAll();
    this.filteredFoods = [...this.foods];
  }

  // Fonction pour gérer le changement de plage de prix
  onPriceChange(): void {
    this.filteredFoods = this.foods.filter(food => 
      food.price >= this.priceRange.min && food.price <= this.priceRange.max
    );
  }
}
