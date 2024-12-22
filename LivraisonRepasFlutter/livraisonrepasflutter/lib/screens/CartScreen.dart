import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/PaymentScreen.dart'; 


class CartScreen extends StatefulWidget {
  final List<Map<String, dynamic>> cartItems;

  CartScreen({required this.cartItems});

  @override
  _CartScreenState createState() => _CartScreenState();
}

class _CartScreenState extends State<CartScreen> {
  Map<String, int> itemQuantities = {};

  @override
  void initState() {
    super.initState();
    // Initialiser les quantités pour chaque article
    for (var item in widget.cartItems) {
      itemQuantities[item['name']] = 1;
    }
  }

  double getTotal() {
    double total = 0.0;
    for (var item in widget.cartItems) {
      final quantity = itemQuantities[item['name']] ?? 1;
      total += item["price"] * quantity;
    }
    return total;
  }

  void increaseQuantity(String itemName) {
    setState(() {
      itemQuantities[itemName] = (itemQuantities[itemName] ?? 1) + 1;
    });
  }

  void decreaseQuantity(String itemName) {
    setState(() {
      if ((itemQuantities[itemName] ?? 1) > 1) {
        itemQuantities[itemName] = (itemQuantities[itemName]! - 1);
      }
    });
  }

  void removeItem(String itemName) {
    setState(() {
      widget.cartItems.removeWhere((item) => item['name'] == itemName);
      itemQuantities.remove(itemName);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Cart")),
      body: widget.cartItems.isEmpty
          ? Center(child: Text("Your cart is empty"))
          : ListView.builder(
              itemCount: widget.cartItems.length,
              itemBuilder: (context, index) {
                final item = widget.cartItems[index];
                final quantity = itemQuantities[item['name']] ?? 1;

                return Card(
                  margin: EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                  child: ListTile(
                    leading: Image.asset(
                      item['image'],
                      width: 50,
                      height: 50,
                      fit: BoxFit.cover,
                    ),
                    title: Text(item['name']),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("\$${item['price']}"),
                        Text("Quantity: $quantity"),
                      ],
                    ),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                          icon: Icon(Icons.remove_circle_outline),
                          onPressed: () {
                            decreaseQuantity(item['name']);
                          },
                        ),
                        IconButton(
                          icon: Icon(Icons.add_circle_outline),
                          onPressed: () {
                            increaseQuantity(item['name']);
                          },
                        ),
                        IconButton(
                          icon: Icon(Icons.delete_outline),
                          onPressed: () {
                            removeItem(item['name']);
                          },
                        ),
                      ],
                    ),
                  ),
                );
              },
            ),
      bottomNavigationBar: BottomAppBar(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "Total: \$${getTotal().toStringAsFixed(2)}",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              ElevatedButton(
                onPressed: () {
                  // Rediriger vers la page de localisation
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => PaymentScreen(
                        totalAmount: getTotal(),
                      ),
                    ),
                  );
                },
                child: Text("Place My Order"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
