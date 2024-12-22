import 'package:flutter/material.dart';

class FoodDetailPage extends StatelessWidget {
  final Map<String, dynamic> food;

  FoodDetailPage({required this.food});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(food['name'])),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Image.asset(food['image'], height: 200, fit: BoxFit.cover),
            SizedBox(height: 16),
            Text(
              food['name'],
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 8),
            Text(
              food['description'],
              style: TextStyle(fontSize: 16, color: Colors.grey[700]),
            ),
            SizedBox(height: 16),
            Text(
              'Price: \$${food['price']}',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 8),
            Row(
              children: [
                Icon(Icons.star, color: Colors.yellow),
                SizedBox(width: 4),
                Text('${food['rating']}'),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
