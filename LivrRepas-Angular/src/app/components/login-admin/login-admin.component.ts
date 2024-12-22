import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  // Importez Router
import { AuthService } from '../../services/register/AuthSevice';  // Importez AuthService
@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})

export class LoginAdminComponent  {
  email: string = '';
  password: string = '';
  message: string = '';


  constructor(private authService: AuthService, private router: Router) {}

  

  onSubmitt() {
    console.log('Login attempt with', this.email, this.password);
    
    // Logic de vérification de l'authentification (vous pouvez y ajouter un appel d'API pour valider les informations)
    if (this.email === 'admin@example.com' && this.password === 'password123') {  // Exemple de validation simple
      // Si la connexion est réussie, redirigez l'utilisateur vers le dashboard
      this.router.navigate(['/admin/dashboard']);
    } else {
      // Si la connexion échoue, vous pouvez afficher un message d'erreur ou faire autre chose
      console.log('Invalid credentials');
    }
  }

  onSubmit(): void {
    console.log('Login attempt:', this.email, this.password); 
  
    this.authService.signInAdmin(this.email, this.password).subscribe({
      next: (response) => {
        console.log('Login response:', response); 
  
             if (response.id) {  // Check if user ID is present
              console.log('Login successful! Redirecting to home page.');
              localStorage.setItem('isLoggedIn', 'true'); 
              localStorage.setItem('userId', response.id); // Store user ID in localStorage
              console.log('Retrieved User ID from localStorage:', localStorage.getItem('userId'));
              console.log('isLoggedIn:', this.authService.isLoggedIn());
              this.router.navigate(['/admin/dashboard']);
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
      