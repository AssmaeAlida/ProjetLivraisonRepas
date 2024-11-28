import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginData = {
    email: '',
    password: '',
  };

  constructor(private router: Router) {}

  onSubmit() {
    const { email, password } = this.loginData;
    // Logique simple de vérification
    if (email === 'test@example.com' && password === 'password') {
      alert('Login successful!');
      // Redirection après connexion réussie
      this.router.navigate(['/home']);
    } else {
      alert('Invalid email or password!');
    }
  }
}
