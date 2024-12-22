import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class CreateAccountScreen extends StatefulWidget {
  @override
  _CreateAccountScreenState createState() => _CreateAccountScreenState();
}

class _CreateAccountScreenState extends State<CreateAccountScreen> {
  bool isSignUp = true;
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _fullNameController = TextEditingController();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  // Handle Submit for Login or Signup
  void _handleSubmit() async {
    if (_formKey.currentState!.validate()) {
      final fullName = _fullNameController.text;
      final email = _emailController.text;
      final password = _passwordController.text;

      if (isSignUp) {
        // Sign Up Logic
        final url = Uri.parse(
            'http://localhost:8089/api/user/signUp/fullName/$fullName/email/$email/password/$password');

        try {
          final response = await http.post(url);
          if (response.statusCode == 200) {
            final result = jsonDecode(response.body);
            print("Inscription réussie : $result");
            Navigator.pushReplacementNamed(context, '/login');
          } else {
            print("Erreur lors de l'inscription : ${response.statusCode}");
            _showErrorDialog("Erreur lors de l'inscription");
          }
        } catch (e) {
          print("Exception lors de l'inscription : $e");
          _showErrorDialog("Une erreur s'est produite.");
        }
      } else {
        // Login Logic
        final url = Uri.parse('http://localhost:8089/api/user/signIn');
        final payload = jsonEncode({"email": email, "password": password});

        try {
          final response = await http.post(
            url,
            headers: {"Content-Type": "application/json"},
            body: payload,
          );

          print("Login Response: ${response.body}"); // Debugging line
         if (response.statusCode == 200) {
  final result = jsonDecode(response.body);
  // Accessing the 'id' instead of 'userId'
  final userId = result['id']; 

  // Store userId in SharedPreferences
  SharedPreferences prefs = await SharedPreferences.getInstance();
  await prefs.setString('userId', userId.toString());

  print("Connexion réussie : $result");
  Navigator.pushReplacementNamed(context, '/home');
} else if (response.statusCode == 401) {
  _showErrorDialog("Identifiants invalides.");
} else {
  print("Erreur lors de la connexion : ${response.statusCode}");
  _showErrorDialog("Erreur lors de la connexion.");
}

        } catch (e) {
          print("Exception lors de la connexion : $e");
          _showErrorDialog("Une erreur lors de la connexion s'est produite.");
        }
      }
    }
  }

  // Show error dialog
  void _showErrorDialog(String message) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Erreur'),
        content: Text(message),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: Text('OK'),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        title: Text(isSignUp ? "Create Account" : "Login"),
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Stack(
              children: [
                Container(
                  height: 200,
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: AssetImage("assets/top.jpg"),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                Positioned(
                  bottom: 0,
                  left: 0,
                  right: 0,
                  child: Container(
                    height: 30,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.vertical(top: Radius.circular(30)),
                    ),
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Form(
                key: _formKey,
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        TextButton(
                          onPressed: () => setState(() => isSignUp = true),
                          child: Text(
                            "Create Account",
                            style: TextStyle(
                              color: isSignUp ? Colors.red : Colors.grey,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        TextButton(
                          onPressed: () => setState(() => isSignUp = false),
                          child: Text(
                            "Login",
                            style: TextStyle(
                              color: !isSignUp ? Colors.red : Colors.grey,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                    isSignUp ? buildSignUpForm() : buildLoginForm(),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  // Build SignUp Form
  Widget buildSignUpForm() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        TextFormField(
          controller: _fullNameController,
          decoration: InputDecoration(labelText: 'Full Name'),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter your name';
            }
            return null;
          },
        ),
        SizedBox(height: 10),
        TextFormField(
          controller: _emailController,
          decoration: InputDecoration(labelText: 'Email address'),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter your email';
            } else if (!RegExp(r'^[^@]+@[^@]+\.[^@]+').hasMatch(value)) {
              return 'Enter a valid email address';
            }
            return null;
          },
        ),
        SizedBox(height: 10),
        TextFormField(
          controller: _passwordController,
          decoration: InputDecoration(labelText: 'Password'),
          obscureText: true,
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter your password';
            } else if (value.length < 6) {
              return 'Password must be at least 6 characters';
            }
            return null;
          },
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: _handleSubmit,
          child: Text('Sign Up'),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.red,
            padding: EdgeInsets.symmetric(vertical: 15),
            textStyle: TextStyle(fontSize: 18),
          ),
        ),
      ],
    );
  }

  // Build Login Form
  Widget buildLoginForm() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        TextFormField(
          controller: _emailController,
          decoration: InputDecoration(labelText: 'Email address'),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter your email';
            }
            return null;
          },
        ),
        SizedBox(height: 10),
        TextFormField(
          controller: _passwordController,
          decoration: InputDecoration(labelText: 'Password'),
          obscureText: true,
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter your password';
            }
            return null;
          },
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: _handleSubmit,
          child: Text('Login'),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.red,
            padding: EdgeInsets.symmetric(vertical: 15),
            textStyle: TextStyle(fontSize: 18),
          ),
        ),
      ],
    );
  }
}
