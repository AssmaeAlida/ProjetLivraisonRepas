import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/register/AuthSevice';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userInfo = {
    id: 0, // Will be dynamically populated
    fullName: '',
    email: '',
    lieu: '' ,
    numTel: ''

  };

  orderHistory = [
    { foodName: 'Pizza Margherita', date: '2024-11-10', total: 12.99 },
    { foodName: 'Burger Deluxe', date: '2024-11-11', total: 9.99 }
  ];

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserInfo();
  }

  loadUserInfo() {
    const userId = 1; // Replace with actual user ID from AuthService or local storage
    this.http.get(`http://localhost:8089/api/user/${userId}`)
      .subscribe({
        next: (data: any) => {
          // Populate the user info dynamically from backend response
          this.userInfo = {
            id: data.id,
            fullName: data.fullName,  // Ensure these match your backend field names
            email: data.email,
            lieu: data.lieu,
            numTel: data.numTel
          };
          console.log('User info loaded:', this.userInfo);
        },
        error: (error) => {
          console.error('Error fetching user info:', error);
          alert('Une erreur est survenue lors du chargement des informations utilisateur.');
        }
      });
  }

  saveUserInfo() {
    const updatedUser = {
      id: this.userInfo.id,
      fullName: this.userInfo.fullName,
      email: this.userInfo.email,
      lieu: this.userInfo.lieu,
      numTel: this.userInfo.numTel
    };

    this.http.post('http://localhost:8089/api/user/updateUser', updatedUser)
      .subscribe({
        next: (response) => {
          console.log('Profile updated successfully:', response);
          alert('Profil sauvegardé avec succès !');
        },
        error: (error) => {
          console.error('Error updating profile:', error);
          alert('Une erreur est survenue lors de la sauvegarde du profil.');
        }
      });
  }

  deleteOrder(index: number) {
    if (confirm('Voulez-vous vraiment supprimer cette commande ?')) {
      this.orderHistory.splice(index, 1);
    }
  }
}
