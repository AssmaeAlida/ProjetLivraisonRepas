import { Component } from '@angular/core';
import { AuthService } from '../services/register/AuthSevice';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService, private router: Router) {}

 login(): void {
  console.log('Login attempt:', this.email, this.password); 

  this.authService.signIn(this.email, this.password).subscribe({
    next: (response) => {
      console.log('Login response:', response); 

      if (response.id) {  // Check if user ID is present
        console.log('Login successful! Redirecting to home page.');
        localStorage.setItem('isLoggedIn', 'true'); 
        localStorage.setItem('userId', response.id); // Store user ID in localStorage
        console.log('Retrieved User ID from localStorage:', localStorage.getItem('userId'));
        console.log('isLoggedIn:', this.authService.isLoggedIn());
        this.router.navigate(['/']);
        this.message = 'Login successful!';
      } else {
        console.log('Login failed: ', response.message || 'Invalid email or password.');
        this.message = response.message || 'Invalid email or password.';
      }
    },
    error: (err) => {
      console.error('Error during login:', err); // Log error in case of failure
      this.message = 'An error occurred. Please try again.';
    }
  });
}
  
}
