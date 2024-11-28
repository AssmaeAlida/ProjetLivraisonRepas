import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart/cart.service';
import { Cart } from '../shared/models/Cart';
import { CartItem } from '../shared/models/CartItem';


@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit {
  cart!:Cart;
  isModalOpen: boolean = false;

 


 // Ouvrir le modal
 showModal() {
  this.isModalOpen = true;
}

// Fermer le modal
closeModal() {
  this.isModalOpen = false;
}

// Méthode pour gérer la soumission du formulaire
onSubmit(form: any) {
  if (form.valid) {
    console.log('Form Data:', form.value);

    // Ajoutez la logique pour passer au paiement
    alert('Proceeding to payment...');
    this.closeModal(); // Fermer le modal après soumission
  } else {
    alert('Please fill out all required fields.');
  }
}



  constructor(private cartService: CartService) { 
    this.setCart();

  }
  ngOnInit(): void {
  }

  removeFromCart(cartItem:CartItem){
    this.cartService.removeFromCart(cartItem.food.id);
    this.setCart();
  }

  changeQuantity(cartItem:CartItem, quantityInString:string){
    const quantity= parseInt(quantityInString);
    this.cartService.changeQuantity(cartItem.food.id, quantity);
    this.setCart();
  }

  setCart(){
    this.cart = this.cartService.getCart();
  }

}
