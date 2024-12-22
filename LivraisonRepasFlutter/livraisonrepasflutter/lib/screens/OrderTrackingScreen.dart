import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/OrderCompletedScreen.dart';

class OrderTrackingScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Order Status")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "Invoice: 123456",
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 20),
            Expanded(
              child: ListView(
                children: [
                  _buildOrderStep("Order received", "09:10 AM, 9 May 2018", true),
                  _buildOrderStep("On the way", "09:15 AM, 9 May 2018", false, isTracking: true),
                  _buildOrderStep("Delivered", "Finish time in 3 min", false),
                ],
              ),
            ),
            ElevatedButton(
              onPressed: () {
                // Naviguer vers OrderCompletedScreen directement
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => OrderCompletedScreen()),
                );
              },
              child: Text("Confirm Delivery"),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildOrderStep(String title, String time, bool isActive, {bool isTracking = false}) {
    return Row(
      children: [
        Column(
          children: [
            CircleAvatar(
              radius: 10,
              backgroundColor: isActive ? Colors.blue : Colors.grey,
            ),
            if (!isActive) ...[
              Container(
                height: 40,
                width: 2,
                color: Colors.grey,
              ),
            ],
          ],
        ),
        SizedBox(width: 10),
        Expanded(
          child: Container(
            margin: EdgeInsets.only(left: 8.0),
            padding: EdgeInsets.all(10.0),
            decoration: BoxDecoration(
              color: isActive ? Colors.blue.withOpacity(0.1) : Colors.grey.withOpacity(0.1),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(title, style: TextStyle(fontWeight: FontWeight.bold, color: isActive ? Colors.blue : Colors.black)),
                SizedBox(height: 5),
                Text(time, style: TextStyle(color: Colors.black54)),
                if (isTracking) 
                  TextButton(
                    onPressed: () {
                      // Logique de suivi
                    },
                    child: Text("TRACKING", style: TextStyle(color: Colors.blue)),
                  ),
              ],
            ),
          ),
        ),
      ],
    );
  }
}