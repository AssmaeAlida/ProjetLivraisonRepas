import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-repas',
  templateUrl: './repas.component.html',
  styleUrls: ['./repas.component.css'],
})
export class RepasComponent implements OnInit {
  meals = [
    { id: 1, name: 'Pizza Margherita', price: 12.5, description: 'Classic Italian pizza with tomato and mozzarella.', image: 'assets/images/foods/food-1.jpg' },
    { id: 2, name: 'Spaghetti Bolognese', price: 10.0, description: 'Traditional pasta with meat sauce.', image: 'assets/images/foods/food-8.jpg' },
    { id: 3, name: 'Caesar Salad', price: 8.5, description: 'Fresh romaine lettuce with Caesar dressing.', image: 'assets/images/foods/food-5.jpg' },
  ];

  isAddMealModalVisible = false;
  isViewMealModalVisible = false;
  isEditMealModalVisible = false;
  isDeleteMealModalVisible = false;

  newMeal: any = {};
  selectedMeal: any;

  constructor() {}

  ngOnInit(): void {}

  showAddMealModal() {
    this.isAddMealModalVisible = true;
  }

  closeAddMealModal() {
    this.isAddMealModalVisible = false;
  }

  addMeal() {
    const newId = this.meals.length + 1;
    this.meals.push({ ...this.newMeal, id: newId, image: 'assets/images/foods/default.jpg' });
    this.closeAddMealModal();
  }

  showViewMealModal(meal: any) {
    this.selectedMeal = meal;
    this.isViewMealModalVisible = true;
  }

  closeViewMealModal() {
    this.isViewMealModalVisible = false;
  }

  showEditMealModal(meal: any) {
    this.selectedMeal = { ...meal };
    this.isEditMealModalVisible = true;
  }

  closeEditMealModal() {
    this.isEditMealModalVisible = false;
  }

  updateMeal() {
    const index = this.meals.findIndex((m) => m.id === this.selectedMeal.id);
    if (index !== -1) {
      this.meals[index] = { ...this.selectedMeal };
    }
    this.closeEditMealModal();
  }

  showDeleteMealModal(meal: any) {
    this.selectedMeal = meal;
    this.isDeleteMealModalVisible = true;
  }

  closeDeleteMealModal() {
    this.isDeleteMealModalVisible = false;
  }

  deleteMeal(id: number) {
    this.meals = this.meals.filter((meal) => meal.id !== id);
    this.closeDeleteMealModal();
  }

  handleFileInput(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.newMeal.image = URL.createObjectURL(file);
    }
  }
  onImageChange(event: Event): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files[0]) {
      const file = fileInput.files[0];
      const reader = new FileReader();
  
      reader.onload = (e: ProgressEvent<FileReader>) => {
        // Mettez à jour l'image dans l'objet selectedMeal
        this.selectedMeal.image = e.target?.result as string;
      };
  
      reader.readAsDataURL(file); // Convertir l'image en Base64 pour l'aperçu
    }
  }
 
  
  
} 