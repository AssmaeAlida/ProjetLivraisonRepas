import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/home_screen.dart';

class OrderCompletedScreenD extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
          padding: EdgeInsets.all(20),
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(16),
            boxShadow: [
              BoxShadow(
                color: Colors.grey.withOpacity(0.5),
                spreadRadius: 5,
                blurRadius: 7,
                offset: Offset(0, 3), // changes position of shadow
              ),
            ],
          ),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Container(
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: Colors.red,
                ),
                padding: EdgeInsets.all(16),
                child: Icon(
                  Icons.check,
                  color: Colors.white,
                  size: 40,
                ),
              ),
              SizedBox(height: 20),
              Text(
                "Thank You!\nOrder Completed",
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 20),
              Text(
                "Please rate your last Driver",
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: List.generate(5, (index) {
                  return Icon(
                    index < 4 ? Icons.star : Icons.star_border,
                    color: index < 4 ? Colors.amber : Colors.grey,
                  );
                }),
              ),
              SizedBox(height: 20),
              TextField(
                decoration: InputDecoration(
                  labelText: "Leave feedback",
                  border: OutlineInputBorder(),
                ),
              ),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  ElevatedButton(
                    onPressed: () {
                    Navigator.push(context,MaterialPageRoute(builder: (context) => HomeScreen())); // Retour à la page précédente

                      // Logic to submit feedback
                    },
                    child: Text("Submit"),
                  ),
                  TextButton(
                    onPressed: () {
                      // Logic to skip feedback
                      Navigator.push(context,MaterialPageRoute(builder: (context) => HomeScreen())); // Retour à la page précédente
                    },
                    child: Text("Skip"),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}