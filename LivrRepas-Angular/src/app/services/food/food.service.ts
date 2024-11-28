import { Injectable } from '@angular/core';
import { Food } from '../../shared/models/Food';
import { Tag } from '../../shared/models/Tag';
@Injectable({
  providedIn: 'root',
})
export class FoodService {
  constructor() { }

  getFoodById(id: number): Food{
    return this.getAll().find(food => food.id == id)!;
  }
  
  getAllFoodsBySearchTerm(searchTerm:string) :Food[]{
    return  this.getAll().filter(food =>
      food.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }
  getAllTags(): Tag[] {
    return [
      { name: 'All', count: 14, imageUrl: '/assets/images/tags/all.png' },
      { name: 'FastFood', count: 4, imageUrl: '/assets/images/tags/fastfood.png' },
      { name: 'Pizza', count: 2, imageUrl: '/assets/images/tags/pizza.png' },
      { name: 'Lunch', count: 3, imageUrl: '/assets/images/tags/lunch.png' },
      { name: 'SlowFood', count: 2, imageUrl: '/assets/images/tags/slowfood.png' },
      { name: 'Hamburger', count: 1, imageUrl: '/assets/images/tags/hamburger.png' },
      { name: 'Fry', count: 1, imageUrl: '/assets/images/tags/fry.png' },
      { name: 'Soup', count: 1, imageUrl: '/assets/images/tags/soup.png' },
    ];
  }

  getAllFoodsByTag(tag: string): Food[] {
    return tag == "All" ?
      this.getAll() :
      this.getAll().filter(food => food.tags?.includes(tag));
  }

  getAll(): Food[] {
    return [
      {
        id: 1,
        name: 'Pizza Pepperoni',
        cookTime: '10-20',
        price: 10,
        favorite: false,
        origins: ['italy'],
        stars: 4.5,
        imageUrl: '/assets/images/foods/food-1.jpg',
        tags: ['FastFood', 'Pizza', 'Lunch'],
        description: 'Pepperoni',
      },
      {
        id: 2,
        name: 'Meatball',
        price: 20,
        cookTime: '20-30',
        favorite: true,
        origins: ['persia', 'middle east', 'china'],
        stars: 4.7,
        imageUrl: '/assets/images/foods/food-2.jpg',
        tags: ['SlowFood', 'Lunch'],
        description: 'Delicious seasoned meatballs, perfect as a side dish, in sandwiches, or served with sauces and pasta.',

      },
      {
        id: 3,
        name: 'Hamburger',
        price: 5,
        cookTime: '10-15',
        favorite: false,
        origins: ['germany', 'us'],
        stars: 3.5,
        imageUrl: '/assets/images/foods/food-3.jpg',
        tags: ['FastFood', 'Hamburger'],
        description: 'Pepperoni',

      },
      {
        id: 4,
        name: 'Fried Potatoes',
        price: 2,
        cookTime: '15-20',
        favorite: true,
        origins: ['belgium', 'france'],
        stars: 3.3,
        imageUrl: '/assets/images/foods/food-4.jpg',
        tags: ['FastFood', 'Fry'],
        description: 'Pepperoni',

      },
      {
        id: 5,
        name: 'Chicken Soup',
        price: 11,
        cookTime: '40-50',
        favorite: false,
        origins: ['india', 'asia'],
        stars: 3.0,
        imageUrl: '/assets/images/foods/food-5.jpg',
        tags: ['SlowFood', 'Soup'],
        description: 'Pepperoni',

      },
      {
        id: 6,
        name: 'Vegetables Pizza',
        price: 9,
        cookTime: '40-50',
        favorite: false,
        origins: ['italy'],
        stars: 4.0,
        imageUrl: '/assets/images/foods/food-6.jpg',
        tags: ['FastFood', 'Pizza', 'Lunch'],
        description: 'Pepperoni',

      },
      {
        id: 7,
        name: 'Sushi',
        price: 9,
        cookTime: '10-20',
        favorite: true,
        origins: ['asia'],
        stars: 4.0,
        imageUrl: '/assets/images/foods/food-7.jpg',
        tags: ['FastFood', 'Pizza', 'Lunch'],
        description: 'Pepperoni',

      },
      {
        id: 8,
        name: 'Pasta',
        price: 9,
        cookTime: '20-30',
        favorite: false,
        origins: ['italy'],
        stars: 4.0,
        imageUrl: '/assets/images/foods/food-8.jpg',
        tags: ['FastFood', 'Pizza', 'Lunch'],
        description: 'Pepperoni',

      },
    ];
  }
}
