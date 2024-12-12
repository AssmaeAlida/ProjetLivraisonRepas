import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isDropdownVisible: boolean = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
 
  }
  
  toggleDropdown(): void {
    console.log('Le menu a été cliqué');
    this.isDropdownVisible = !this.isDropdownVisible;
    console.log('isDropdownVisible:', this.isDropdownVisible);
  }
  

  logout(): void {
    // Logique pour déconnecter l'utilisateur (exemple : suppression des tokens)
    localStorage.clear();
    this.router.navigate(['/admin/LoginAdmin']); // Redirige vers la page loginAdmin
  }
}
