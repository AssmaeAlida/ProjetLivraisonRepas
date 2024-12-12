import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router) {}

  // Vérifiez si l'utilisateur est sur une route admin
  isAdminRoute(): boolean {
    return this.router.url.startsWith('/admin');
  }
}