import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/CreateAccountScreen.dart';
import 'package:livraisonrepasflutter/screens/home_screen.dart';

class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login", style: TextStyle(color: Colors.white)),
        backgroundColor: Colors.red,
        elevation: 0,
      ),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "Connexion",
              style: TextStyle(fontSize: 32, fontWeight: FontWeight.bold, color: Colors.red),
            ),
            SizedBox(height: 20),
            _buildTextField(label: "Adresse e-mail", hint: "Entrez votre email"),
            SizedBox(height: 10),
            _buildTextField(label: "Mot de passe", hint: "Entrez votre mot de passe", obscureText: true),
            SizedBox(height: 20),
            _buildElevatedButton("Se connecter", () {
              // Implémentez la logique de connexion
            }),
            SizedBox(height: 20),
            _buildElevatedButton("Suivant", () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => HomeScreen()),
              );
            }),
            SizedBox(height: 20),
            _buildFooter(context),
          ],
        ),
      ),
    );
  }

  Widget _buildTextField({required String label, required String hint, bool obscureText = false}) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(label, style: TextStyle(fontSize: 16)),
        TextField(
          obscureText: obscureText,
          decoration: InputDecoration(
            hintText: hint,
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
            ),
            contentPadding: EdgeInsets.symmetric(vertical: 15, horizontal: 15),
            filled: true,
            fillColor: Colors.grey[200],
          ),
        ),
      ],
    );
  }

  Widget _buildElevatedButton(String text, VoidCallback onPressed) {
    return ElevatedButton(
      onPressed: onPressed,
      child: Text(text, style: TextStyle(fontSize: 18)),
      style: ElevatedButton.styleFrom(
        backgroundColor: Colors.red,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        padding: EdgeInsets.symmetric(vertical: 14),
      ),
    );
  }

  Widget _buildFooter(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text("Vous n'avez pas de compte? ", style: TextStyle(fontSize: 16)),
        TextButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => CreateAccountScreen()),
            );
          },
          child: Text("S'inscrire", style: TextStyle(fontSize: 16, color: Colors.red)),
        ),
      ],
    );
  }
}