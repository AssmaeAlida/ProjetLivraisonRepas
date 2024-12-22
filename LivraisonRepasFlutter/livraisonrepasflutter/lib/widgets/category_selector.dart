import 'package:flutter/material.dart';

class CategorySelector extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0),
      child: Row(
        children: [
          _buildCategoryButton('Burger', true),
          SizedBox(width: 16),
          _buildCategoryButton('Pizza', false),
        ],
      ),
    );
  }

  Widget _buildCategoryButton(String label, bool isSelected) {
    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        backgroundColor: isSelected ? Colors.red : Colors.grey[300], // Remplace `primary` par `backgroundColor`
        foregroundColor: isSelected ? Colors.white : Colors.black,  // Remplace `onPrimary` par `foregroundColor`
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 12), // Optionnel : ajuster les marges
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(12), // Bordures arrondies
        ),
      ),
      onPressed: () {
        // Ajouter ici une logique pour changer la catégorie si nécessaire
      },
      child: Text(label, style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
    );
  }
}
