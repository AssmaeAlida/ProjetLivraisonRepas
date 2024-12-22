import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';

class LocationScreen extends StatefulWidget {
  final double totalAmount;

  LocationScreen({required this.totalAmount});

  @override
  _LocationScreenState createState() => _LocationScreenState();
}

class _LocationScreenState extends State<LocationScreen> {
  final _locationController = TextEditingController();
  GoogleMapController? mapController;
  LatLng? _selectedLocation;

  @override
  void initState() {
    super.initState();
  }

  // Méthode pour obtenir la position actuelle de l'utilisateur
  Future<void> _getCurrentLocation() async {
    Position position = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high);
    setState(() {
      _selectedLocation = LatLng(position.latitude, position.longitude);
      if (mapController != null) {
        mapController!.animateCamera(CameraUpdate.newLatLng(_selectedLocation!));
      }
    });
  }

  // Méthode pour soumettre la localisation entrée par l'utilisateur
  void _submitLocation() {
    final location = _locationController.text.trim();
    if (location.isEmpty) {
      // Afficher une erreur si l'adresse est vide
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text("Error"),
          content: Text("Please enter your location."),
          actions: [
            TextButton(
              child: Text("OK"),
              onPressed: () {
                Navigator.pop(context);
              },
            ),
          ],
        ),
      );
    } else {
      // Affiche la localisation sur la carte (fonction de géocodage à ajouter)
      // Exemple d'appel de fonction de géocodage ici
      // Vous devrez utiliser un service de géocodage pour convertir une adresse en coordonnées
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Enter Your Location")),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: TextField(
              controller: _locationController,
              decoration: InputDecoration(
                labelText: "Enter your address",
                border: OutlineInputBorder(),
              ),
            ),
          ),
          ElevatedButton(
            onPressed: _submitLocation,
            child: Text("Submit Location"),
          ),
          ElevatedButton(
            onPressed: _getCurrentLocation,
            child: Text("Get Current Location"),
          ),
          Expanded(
            child: GoogleMap(
              initialCameraPosition: CameraPosition(
                target: LatLng(37.7749, -122.4194), // Par défaut San Francisco
                zoom: 14,
              ),
              onMapCreated: (GoogleMapController controller) {
                mapController = controller;
              },
              markers: _selectedLocation == null
                  ? {}
                  : {
                      Marker(
                        markerId: MarkerId("selected-location"),
                        position: _selectedLocation!,
                      ),
                    },
              onTap: (LatLng location) {
                setState(() {
                  _selectedLocation = location;
                });
              },
            ),
          ),
        ],
      ),
    );
  }
}
