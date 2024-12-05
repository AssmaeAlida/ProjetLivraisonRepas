import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart/cart.service';
import { CommandeService } from '../services/cart/CommandeService';
import { AuthService } from '../services/register/AuthSevice';
import { Cart } from '../shared/models/Cart';
import { CartItem } from '../shared/models/CartItem';

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css'],
})
export class CartPageComponent implements OnInit {
  cart!: Cart;
  isModalOpen: boolean = false;

  constructor(
    private cartService: CartService,
    private commandeService: CommandeService,
    private authService: AuthService // Inject AuthService
  ) {
    this.setCart();
  }

  ngOnInit(): void {}

  removeFromCart(cartItem: CartItem) {
    this.cartService.removeFromCart(cartItem.food.id);
    this.setCart();
  }

  changeQuantity(cartItem: CartItem, quantityInString: string) {
    const quantity = parseInt(quantityInString);
    this.cartService.changeQuantity(cartItem.food.id, quantity);
    this.setCart();
  }

  setCart() {
    this.cart = this.cartService.getCart();
  }

  showModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  onSubmit(form: any) {
    if (form.valid) {
      const utilisateurId = this.authService.getUserId(); // Retrieve user ID dynamically
      if (utilisateurId) {
        this.commandeService.creerCommande(utilisateurId).subscribe({
          next: (response) => {
            console.log('Commande créée avec succès :', response);
            alert('Commande créée avec succès !');
            this.closeModal();
          },
          error: (err) => {
            console.error('Erreur lors de la création de la commande :', err);
            alert('Une erreur est survenue lors de la création de la commande.');
          },
        });
      } else {
        alert('Utilisateur non connecté.');
      }
    } else {
      alert('Veuillez remplir tous les champs obligatoires.');
    }
  }
}
