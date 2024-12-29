import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/CreateAccountScreen.dart';
import 'package:livraisonrepasflutter/screens/Delivery/CreateAccountScreen.dart';

class SelectScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                "Select Your Identity",
                style: TextStyle(
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                  color: Colors.redAccent,
                ),
              ),
              SizedBox(height: 30),
              GestureDetector(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => CreateAccountScreen()),
                  );
                },
                child: IdentityCard(
                  imagePath: 'assets/girl.png', // Chemin vers l'image "Client"
                  label: 'Client',
                ),
              ),
              SizedBox(height: 50),
              GestureDetector(
                onTap: () {
                  print('Delivery card clicked'); // Message de débogage
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => CreateAccountScreenD()), // Redirige vers HomeScreen
                  );
                },
                child: IdentityCard(
                  imagePath: 'assets/boy.webp', // Chemin vers l'image "Delivery"
                  label: 'Delivery',
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class IdentityCard extends StatelessWidget {
  final String imagePath;
  final String label;

  const IdentityCard({Key? key, required this.imagePath, required this.label})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        CircleAvatar(
          radius: 60,
          backgroundColor: Colors.grey[200],
          backgroundImage: AssetImage(imagePath),
        ),
        SizedBox(height: 15),
        Text(
          label,
          style: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
            color: Colors.redAccent,
          ),
        ),
      ],
    );
  }
}