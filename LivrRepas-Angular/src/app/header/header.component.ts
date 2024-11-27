import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false; // Simule un utilisateur connecté
  profileImage = 'assets/images/foods/photoProfile.png';
  showDropdown = false;

  constructor() {}

  ngOnInit(): void {}

  toggleDropdown(): void {
    this.showDropdown = !this.showDropdown;
  }

  logout(): void {
    // Logique de déconnexion
    console.log('Déconnexion réussie');
    this.isLoggedIn = false;
    this.showDropdown = false;
    console.log('isLoggedIn:', this.isLoggedIn);

  }
}
