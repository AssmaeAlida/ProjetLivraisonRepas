import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent {
  email: string = '';

  constructor() {}

  onSubmit() {
    if (this.email) {
      console.log("Password reset link sent to:", this.email);
      // Logique pour envoyer la demande de réinitialisation du mot de passe
      // Par exemple, appeler une API pour envoyer un lien de réinitialisation
    }
  }
}
