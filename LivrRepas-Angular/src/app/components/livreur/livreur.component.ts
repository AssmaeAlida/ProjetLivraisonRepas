import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-livreur',
  templateUrl: './livreur.component.html',
  styleUrls: ['./livreur.component.css']
})
export class LivreurComponent implements OnInit {
  livreurs = [
    { id: 1, fullName: 'John Doe', email: 'john.doe@example.com', phone: '1234567890', ordersCompleted: 20, image: 'assets/images/customer1.png' },
    { id: 2, fullName: 'Jane Smith', email: 'jane.smith@example.com', phone: '0987654321', ordersCompleted: 15, image: 'assets/images/customer3.png' },
  ];

  isAddLivreurModalVisible = false;
  isViewLivreurModalVisible = false;
  isEditLivreurModalVisible = false;
  isDeleteLivreurModalVisible = false;

  newLivreur: any = {};
  selectedLivreur: any;

  constructor() {}

  ngOnInit(): void {}

  showAddLivreurModal() {
    this.isAddLivreurModalVisible = true;
  }

  closeAddLivreurModal() {
    this.isAddLivreurModalVisible = false;
  }

  addLivreur() {
    const newId = this.livreurs.length + 1;
    this.livreurs.push({ ...this.newLivreur, id: newId, image: 'assets/images/profiles/default.jpg' });
    this.closeAddLivreurModal();
  }

  showViewLivreurModal(livreur: any) {
    this.selectedLivreur = livreur;
    this.isViewLivreurModalVisible = true;
  }

  closeViewLivreurModal() {
    this.isViewLivreurModalVisible = false;
  }

  showEditLivreurModal(livreur: any) {
    this.selectedLivreur = { ...livreur };
    this.isEditLivreurModalVisible = true;
  }

  closeEditLivreurModal() {
    this.isEditLivreurModalVisible = false;
  }

  updateLivreur() {
    const index = this.livreurs.findIndex((l) => l.id === this.selectedLivreur.id);
    if (index !== -1) {
      this.livreurs[index] = { ...this.selectedLivreur };
    }
    this.closeEditLivreurModal();
  }

  showDeleteLivreurModal(livreur: any) {
    this.selectedLivreur = livreur;
    this.isDeleteLivreurModalVisible = true;
  }

  closeDeleteLivreurModal() {
    this.isDeleteLivreurModalVisible = false;
  }

  deleteLivreur(id: number) {
    this.livreurs = this.livreurs.filter((livreur) => livreur.id !== id);
    this.closeDeleteLivreurModal();
  }

  handleFileInput(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.newLivreur.image = URL.createObjectURL(file);
    }
  }
}
