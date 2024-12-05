import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/register/AuthSevice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false; // Simule un utilisateur connecté
  profileImage = 'assets/images/foods/photoProfile.png';
  showDropdown = false;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();

  }

  toggleDropdown(): void {
    this.showDropdown = !this.showDropdown;
  }

  logout(): void {
    this.authService.signOut();
    console.log('Déconnexion réussie');
    this.isLoggedIn = false;
    this.showDropdown = false;
    console.log('isLoggedIn:', this.isLoggedIn);
    this.router.navigate(['/login']);


  }
}
