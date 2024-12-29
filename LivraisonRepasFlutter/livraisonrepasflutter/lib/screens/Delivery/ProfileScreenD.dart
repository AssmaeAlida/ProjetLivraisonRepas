import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:io';
import 'package:shared_preferences/shared_preferences.dart';

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
  String? userId;

  @override
  void initState() {
    super.initState();
    _getUserIdFromSharedPreferences();
  }

  // Fetch the userId from SharedPreferences
  Future<void> _getUserIdFromSharedPreferences() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      userId = prefs.getString('userId');
    });
    if (userId != null) {
      _loadUserInfo();
    }
  }

  // Load user information from API
  Future<void> _loadUserInfo() async {
    if (userId == null) return;

    final url = Uri.parse('http://localhost:8089/api/user/$userId');
    try {
      final response = await http.get(url);
      if (response.statusCode == 200) {
        final userData = jsonDecode(response.body);
        setState(() {
          name = userData['fullName'];
          email = userData['email'];
          phone = userData['numTel'];
          address = userData['lieu'];
          phoneController.text = phone ?? '';
          addressController.text = address ?? '';
        });
      } else {
        print('Failed to load user info: ${response.statusCode}');
      }
    } catch (e) {
      print('Exception while fetching user info: $e');
    }
  }

  // Update user profile information
  Future<void> _updateProfile() async {
    if (userId == null) return;

    final url = Uri.parse('http://localhost:8089/api/user/updateUser');
    final payload = {
      "id": userId,
      "fullName": name,
      "email": email,
      "numTel": phoneController.text,
      "lieu": addressController.text,
    };

    try {
      final response = await http.post(
        url,
        headers: {"Content-Type": "application/json"},
        body: jsonEncode(payload),
      );

      if (response.statusCode == 200) {
        setState(() {
          phone = phoneController.text;
          address = addressController.text;
        });
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Profile updated successfully!')),
        );
      } else {
        print('Failed to update profile: ${response.statusCode}');
      }
    } catch (e) {
      print('Exception while updating profile: $e');
    }
  }

  // Pick a profile image from the gallery
  Future<void> _pickImage() async {
    final pickedImage = await _picker.pickImage(source: ImageSource.gallery);
    setState(() {
      _profileImage = pickedImage;
    });
  }

  // Get the image provider for the profile picture
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
      body: userId == null
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  children: [
                    GestureDetector(
                      onTap: _pickImage,
                      child: CircleAvatar(
                        radius: 50,
                        backgroundImage: _getImageProvider(),
                      ),
                    ),
                    Text(name ?? '', style: const TextStyle(fontSize: 24)),
                    Text(email ?? '', style: const TextStyle(color: Colors.grey)),
                    TextField(
                      controller: phoneController,
                      decoration: const InputDecoration(labelText: "Phone Number"),
                    ),
                    TextField(
                      controller: addressController,
                      decoration: const InputDecoration(labelText: "Address"),
                    ),
                    const SizedBox(height: 20),
                    ElevatedButton(
                      onPressed: _updateProfile,
                      child: const Text("Save Changes"),
                      style: ElevatedButton.styleFrom(backgroundColor: Colors.red),
                    ),
                  ],
                ),
              ),
            ),
    );
  }
}
