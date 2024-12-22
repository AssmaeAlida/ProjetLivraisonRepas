import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/OrderTrackingScreen.dart';

class PaymentScreen extends StatelessWidget {
  final double totalAmount;

  PaymentScreen({required this.totalAmount});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Payment")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "Total amount: \$${totalAmount.toStringAsFixed(2)}",
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 40),
            Text(
              "Choose your payment method:",
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                // Afficher le pop-up pour le paiement par carte
                showDialog(
                  context: context,
                  builder: (context) => AlertDialog(
                    title: Text("Checkout Page", style: TextStyle(color: Colors.white)),
                    backgroundColor: Colors.redAccent,
                    content: Container(
                      width: double.maxFinite,
                      child: Column(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          TextField(
                            decoration: InputDecoration(
                              labelText: "Card Number",
                              labelStyle: TextStyle(color: Colors.white),
                              enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(color: Colors.white),
                              ),
                            ),
                          ),
                          TextField(
                            decoration: InputDecoration(
                              labelText: "Card Expiry (MM/YY)",
                              labelStyle: TextStyle(color: Colors.white),
                              enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(color: Colors.white),
                              ),
                            ),
                          ),
                          TextField(
                            decoration: InputDecoration(
                              labelText: "CVV",
                              labelStyle: TextStyle(color: Colors.white),
                              enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(color: Colors.white),
                              ),
                            ),
                            obscureText: true,
                          ),
                        ],
                      ),
                    ),
                    actions: [
                      TextButton(
                        child: Text("Pay Now", style: TextStyle(color: Colors.white)),
                        onPressed: () {
                          // Fermer le pop-up et afficher le message de succès
                          Navigator.pop(context); // Fermer le pop-up de paiement
                          showDialog(
                            context: context,
                            builder: (context) => AlertDialog(
                              title: Text("Payment Successful", style: TextStyle(color: Colors.white)),
                              backgroundColor: Colors.redAccent,
                              content: Text("Thank You.", style: TextStyle(color: Colors.white)),
                              actions: [
                                TextButton(
                                  child: Text("continue ->", style: TextStyle(color: Colors.white)),
                                  onPressed: () {
                                    Navigator.push(context, MaterialPageRoute(builder: (context) => OrderTrackingScreen())); // Fermer le pop-up de succès
                                  },
                                ),
                              ],
                            ),
                          );
                        },
                      ),
                      TextButton(
                        child: Text("Cancel", style: TextStyle(color: Colors.white)),
                        onPressed: () {
                          Navigator.pop(context);
                        },
                      ),
                    ],
                  ),
                );
              },
              child: Text("Pay by Card"),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                // Code to handle cash payment logic
                showDialog(
                  context: context,
                  builder: (context) => AlertDialog(
                    title: Text("Cash Payment", style: TextStyle(color: Colors.white)),
                    backgroundColor: Colors.redAccent,
                    content: Text("Cash payment processing...", style: TextStyle(color: Colors.white)),
                    actions: [
                      TextButton(
                        child: Text("OK", style: TextStyle(color: Colors.white)),
                        onPressed: () {
                          Navigator.pop(context);
                        },
                      ),
                    ],
                  ),
                );
              },
              child: Text("Pay by Cash"),
            ),
          ],
        ),
      ),
    );
  }
}