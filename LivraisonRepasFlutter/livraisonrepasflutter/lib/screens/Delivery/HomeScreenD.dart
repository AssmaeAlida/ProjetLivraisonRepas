import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/screens/Delivery/OrderCompletedScreenD.dart';
import 'package:livraisonrepasflutter/screens/Delivery/ProfileScreenD.dart';

class HomeScreenD extends StatefulWidget {
  @override
  _HomeScreenDState createState() => _HomeScreenDState();
}

class _HomeScreenDState extends State<HomeScreenD> {
  List<Map<String, dynamic>> reservations = [
    {"name": "John Doe", "date": "2024-01-02", "time": "14:00", "status": "Pending"},
    {"name": "Jane Smith", "date": "2024-01-05", "time": "10:00", "status": "Accepted"},
    {"name": "Robert Johnson", "date": "2024-01-08", "time": "18:00", "status": "Canceled"},
    {"name": "Emily Davis", "date": "2024-01-10", "time": "12:30", "status": "Pending"},
    {"name": "Michael Brown", "date": "2024-01-12", "time": "16:00", "status": "Accepted"},
    {"name": "Sarah Wilson", "date": "2024-01-15", "time": "09:00", "status": "Canceled"},
  ];

  int _selectedIndex = 0;

  @override
  void initState() {
    super.initState();
    sortReservations();
  }

  void sortReservations() {
    reservations.sort((a, b) => a['date'].compareTo(b['date']));
  }

  void switchStatus(int index) {
    setState(() {
      String currentStatus = reservations[index]['status'];
      if (currentStatus == 'Pending') {
        reservations[index]['status'] = 'Accepted';
      } else if (currentStatus == 'Accepted') {
        reservations[index]['status'] = 'Canceled';
      } else if (currentStatus == 'Canceled') {
        reservations[index]['status'] = 'Pending';
      }
    });
  }

  void deleteReservation(int index) {
    setState(() {
      reservations.removeAt(index);
    });
  }

  Widget buildReservationCard(Map<String, dynamic> reservation, int index) {
    Color statusColor;
    switch (reservation['status']) {
      case 'Pending':
        statusColor = Colors.orange;
        break;
      case 'Accepted':
        statusColor = Colors.green;
        break;
      case 'Canceled':
        statusColor = Colors.red;
        break;
      default:
        statusColor = Colors.grey;
    }

    return Card(
      elevation: 3,
      margin: EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      child: ListTile(
        title: Text(reservation['name']),
        subtitle: Text("${reservation['date']} at ${reservation['time']}"),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: statusColor,
              ),
              onPressed: () => switchStatus(index),
              child: Text(
                reservation['status'],
                style: TextStyle(color: Colors.white),
              ),
            ),
            SizedBox(width: 8),
            IconButton(
              icon: Icon(Icons.close, color: Colors.red),
              onPressed: () => deleteReservation(index),
            ),
          ],
        ),
      ),
    );
  }

  void onBottomNavTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });

    if (index == 1) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => OrderCompletedScreenD()),
      );
    } else if (index == 2) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => ProfileScreenD()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Réservations"),
        centerTitle: true,
      ),
      body: ListView.builder(
        itemCount: reservations.length,
        itemBuilder: (context, index) {
          return buildReservationCard(reservations[index], index);
        },
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _selectedIndex,
        onTap: onBottomNavTapped,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
          BottomNavigationBarItem(icon: Icon(Icons.location_on), label: "Location"),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Profile'),
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: HomeScreenD(),
  ));
}
