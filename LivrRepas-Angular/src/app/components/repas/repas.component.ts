import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-repas',
  templateUrl: './repas.component.html',
  styleUrls: ['./repas.component.css'],
})
export class RepasComponent  {
  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {}
  ngOnInit(): void {
    this.fetchMeals();
    console.log('Meals on init:', this.meals);  
  }
  
  meals: any[] = []; 

  isAddMealModalVisible = false;
  isViewMealModalVisible = false;
  isEditMealModalVisible = false;
  isDeleteMealModalVisible = false;

  newMeal: any = {};
  fileToUpload: File | null = null;  
    selectedMeal: any;

    

  showAddMealModal() {
    this.isAddMealModalVisible = true;
  }

  closeAddMealModal() {
    this.isAddMealModalVisible = false;
  }
  fetchMeals() {
    this.http.get('http://localhost:8089/api/admin/repases').subscribe(
      (response: any) => {
        console.log('Meals data fetched:', response);  // Log the response
        this.meals = response;
      },
      (error) => {
        console.error('Error fetching meals:', error);
      }
    );
  }
  

  addMeal(event: Event) {
    event.preventDefault(); // Prevent the default form submission behavior
  
    this.http.post('http://localhost:8089/api/admin/repas', this.newMeal).subscribe(
      (response: any) => {
        const mealId = response.id;
  
        // Step 2: If there is an image, upload it using the uploadImage endpoint
        if (this.fileToUpload) {
          const formData = new FormData();
          formData.append('file', this.fileToUpload);
          this.http
            .post(`http://localhost:8089/api/admin/repas/${mealId}/uploadImage`, formData)
            .subscribe(
              (imageResponse: any) => {
                console.log('Image uploaded successfully:', imageResponse);
                // Update the meal list with the uploaded image URL
                response.image = imageResponse.imageUrl; // Assuming image URL is returned as 'imageUrl'
                this.meals.push(response); 
                this.closeAddMealModal();
              },
              (error) => {
                console.error('Error uploading image:', error);
              }
            );
        } else {
          // If no image, just add the meal as is
          this.meals.push(response);
          this.closeAddMealModal();
        }
      },
      (error) => {
        console.error('Error adding meal:', error);
      }
    );
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
  handleFileInput(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.fileToUpload = file;
      this.newMeal.image = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(file)); // Sanitize the URL
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