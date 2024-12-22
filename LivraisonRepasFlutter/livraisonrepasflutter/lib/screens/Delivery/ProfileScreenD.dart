import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:io'; // Import pour utiliser File

class ProfileScreenD extends StatefulWidget {
  @override
  _ProfileScreenState createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreenD> {
  String? name;
  String? email;
  String? phone;
  String? address;
  XFile? _profileImage;
  final _picker = ImagePicker();
  TextEditingController phoneController = TextEditingController();
  TextEditingController addressController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _loadUserInfo();
  }

  Future<void> _loadUserInfo() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      name = prefs.getString('name') ?? '';
      email = prefs.getString('email') ?? '';
      phone = prefs.getString('phone') ?? '';
      address = prefs.getString('address') ?? '';
    });
  }

  Future<void> _updateProfile() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('phone', phoneController.text);
    await prefs.setString('address', addressController.text);
    setState(() {
      phone = phoneController.text;
      address = addressController.text;
    });
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Profile updated successfully!')));
  }

  Future<void> _pickImage() async {
    final pickedImage = await _picker.pickImage(source: ImageSource.gallery);
    setState(() {
      _profileImage = pickedImage;
    });
  }

  // Fonction qui retourne l'ImageProvider approprié
  ImageProvider<Object>? _getImageProvider() {
    if (_profileImage == null) {
      return const AssetImage('assets/default_profile.png');
    } else {
      return FileImage(File(_profileImage!.path));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Profile'),
        backgroundColor: Colors.red,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: [
              GestureDetector(
                onTap: _pickImage,
                child: CircleAvatar(
                  radius: 50,
                  backgroundImage: _getImageProvider(), // Utilisation de la fonction ici
                ),
              ),
              Text(name ?? '', style: const TextStyle(fontSize: 24)),
              Text(email ?? '', style: const TextStyle(color: Colors.grey)),
              TextField(
                controller: phoneController..text = phone ?? '',
                decoration: const InputDecoration(labelText: "Numéro de téléphone"),
              ),
              TextField(
                controller: addressController..text = address ?? '',
                decoration: const InputDecoration(labelText: "Adresse"),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _updateProfile,
                child: const Text("Sauvegarder les modifications"),
                style: ElevatedButton.styleFrom(backgroundColor: Colors.red),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
