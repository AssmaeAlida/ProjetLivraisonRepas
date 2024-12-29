import 'package:flutter/material.dart';
import 'dart:async';
import 'package:livraisonrepasflutter/screens/LoginScreen.dart';
import 'package:livraisonrepasflutter/screens/home_screen.dart'; // Import du fichier HomeScreen.dart
import 'package:livraisonrepasflutter/screens/SelectScreen.dart';
import 'package:livraisonrepasflutter/screens/Delivery/HomeScreenD.dart';



void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: IntroScreen(),
      debugShowCheckedModeBanner: false,
      routes: {
        '/login': (context) => LoginScreen(),
        '/home': (context) => HomeScreen(), // Ajout de la route vers HomeScreen
        '/SelectScreen':(context) => SelectScreen(),
        '/homeD': (context) => HomeScreenD(),

      },
    );
  }
}

class IntroScreen extends StatefulWidget {
  @override
  _IntroScreenState createState() => _IntroScreenState();
}

class _IntroScreenState extends State<IntroScreen> {
  @override
  void initState() {
    super.initState();
    _navigateToSelectScreen();
  }

  _navigateToSelectScreen() async {
    await Future.delayed(Duration(seconds: 3));
    if (mounted) {
      Navigator.pushReplacementNamed(context, '/SelectScreen');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Stack(
        children: [
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Image.asset(
                  'assets/delivery.webp',
                  height: 200,
                ),
                SizedBox(height: 20),
                Text(
                  "FastDelivery",
                  style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.bold,
                    color: Colors.red,
                  ),
                ),
                SizedBox(height: 10),
                Text(
                  "Votre service de livraison rapide",
                  style: TextStyle(
                    fontSize: 18,
                    color: Colors.grey[600],
                  ),
                ),
              ],
            ),
          ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: TextButton(
                onPressed: () {
                  Navigator.pushReplacementNamed(context, '/SelectScreen');
                },
                child: Text(
                  "Passer",
                  style: TextStyle(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
