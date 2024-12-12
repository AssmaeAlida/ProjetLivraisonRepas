import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  // Importez Router

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private router: Router) { }  // Injectez Router

  ngOnInit(): void {
  }

  onSubmit() {
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
}
