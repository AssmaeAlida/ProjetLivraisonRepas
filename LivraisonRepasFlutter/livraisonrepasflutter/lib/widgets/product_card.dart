import 'package:flutter/material.dart';

class ProductCard extends StatelessWidget {
  final String name;
  final String description;
  final double price;
  final double rating;
  final String imageUrl;
  final VoidCallback onAddToCart; // Callback to handle add to cart action

  ProductCard({
    required this.name,
    required this.description,
    required this.price,
    required this.rating,
    required this.imageUrl,
    required this.onAddToCart, // Ensure this is passed
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5.0,
      margin: EdgeInsets.symmetric(vertical: 10.0, horizontal: 15.0),
      child: ListTile(
        leading: Image.asset(imageUrl),
        title: Text(name),
        subtitle: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(description),
            Row(
              children: [
                Icon(Icons.star, color: Colors.yellow, size: 15),
                Text(rating.toString()),
              ],
            ),
            Text('\$${price.toString()}'),
          ],
        ),
        trailing: IconButton(
          icon: Icon(Icons.add_shopping_cart),
          onPressed: onAddToCart, // Action triggered on tap
        ),
      ),
    );
  }
}
