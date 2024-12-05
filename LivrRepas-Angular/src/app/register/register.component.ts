import { Component } from '@angular/core';
import { AuthService } from '../services/register/AuthSevice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  fullName = '';
  email = '';
  password = '';
  confirmPassword = '';
  message = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.message = 'Passwords do not match!';
      return;
    }

    this.authService.signUp(this.email, this.password).subscribe({
      next: (response) => {
        if (response === 1) {
          this.message = 'Registration successful! You can now log in.';
          this.router.navigate(['/login']); 
          this.resetForm();
        } else if (response === -1) {
          this.message = 'User with this email already exists.';
        } else {
          this.message = 'Unexpected response from the server.';
        }
      },
      error: (err) => {
        this.message = 'An error occurred. Please try again.';
        console.error('Error during registration:', err);
      }
    });
  }
  private resetForm(): void {
    this.fullName = '';
    this.email = '';
    this.password = '';
    this.confirmPassword = '';
  }
}
