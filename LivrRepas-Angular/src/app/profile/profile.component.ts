import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userInfo = {
    name: 'Assmae',
    email: 'alidaassmae@gmail.com',
    phone: '0637451198',
    address: '123 rue Marrakech '
  };

  orderHistory = [
    { foodName: 'Pizza Margherita', date: '2024-11-10', total: 12.99 },
    { foodName: 'Burger Deluxe', date: '2024-11-11', total: 9.99 }
  ];

  saveUserInfo() {
    // Ajoutez ici la logique pour sauvegarder les données (ex. : via API)
    console.log('Données utilisateur sauvegardées :', this.userInfo);
    alert('Profil sauvegardé avec succès !');
  }

  deleteOrder(index: number) {
    if (confirm('Voulez-vous vraiment supprimer cette commande ?')) {
      this.orderHistory.splice(index, 1);
    }
  }
}
