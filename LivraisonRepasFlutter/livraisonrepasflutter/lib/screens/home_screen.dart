import 'package:flutter/material.dart';
import 'package:livraisonrepasflutter/widgets/food_detail_page.dart';
import 'package:livraisonrepasflutter/screens/CartScreen.dart';
import 'package:livraisonrepasflutter/screens/ProfileScreen.dart';
import 'package:livraisonrepasflutter/widgets/CustomAppBar.dart';
import 'package:livraisonrepasflutter/widgets/product_card.dart'; // Import ProductCard

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  List<Map<String, dynamic>> menuItems = [
    {
      "name": "Chicken Burger",
      "description": "100 gr chicken + tomato + cheese + lettuce",
      "price": 20.00,
      "rating": 3.8,
      "image": 'assets/chicken_burger.png',
      "category": "Burger",
      "ingredients": ["Chicken", "Tomato", "Cheese", "Lettuce"]
    },
    {
      "name": "Cheese Burger",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/cheese_burger.png',
      "category": "Burger",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Pizza Margherita",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/pizzamargherita.jpeg',
      "category": "Pizza",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Pizza Pepperoni",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/pizzapepperoni.jpg',
      "category": "Pizza",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Coca Cola",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/cocacola.jpg',
      "category": "Boisson",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Fanta",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/fanta.jpg',
      "category": "Boisson",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Poms",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/poms.jpg',
      "category": "Boisson",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
    {
      "name": "Chocolate Cake",
      "description": "100 gr meat + onion + tomato + lettuce cheese",
      "price": 15.00,
      "rating": 4.5,
      "image": 'assets/chocolatecake.png',
      "category": "Dessert",
      "ingredients": ["Beef", "Cheese", "Tomato", "Lettuce", "Onion"]
    },
  ];

  String selectedCategory = "Burger";
  List<Map<String, dynamic>> cartItems = [];
  String searchQuery = '';

  void addToCart(Map<String, dynamic> food) {
    setState(() {
      cartItems.add(food);
    });
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text("${food['name']} ajouté au panier !"),
        duration: Duration(seconds: 2),
      ),
    );
  }

  List<Map<String, dynamic>> getFilteredMenuItems() {
    if (searchQuery.isEmpty) {
      return menuItems
          .where((food) => food['category'] == selectedCategory)
          .toList();
    } else {
      return menuItems
          .where((food) =>
              food['category'] == selectedCategory &&
              food['name'].toLowerCase().contains(searchQuery.toLowerCase()))
          .toList();
    }
  }

  Widget buildSearchBar() {
    return TextField(
      onChanged: (query) {
        setState(() {
          searchQuery = query;
        });
      },
      decoration: InputDecoration(
        hintText: 'Search for a dish...',
        prefixIcon: Icon(Icons.search),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
      ),
    );
  }

  Widget buildCategoryButtons() {
    List<Map<String, String>> categories = [
      {"name": "Burger", "icon": "🍔"},
      {"name": "Pizza", "icon": "🍕"},
      {"name": "Boisson", "icon": "🥤"},
      {"name": "Dessert", "icon": "🍰"},
    ];

    return SingleChildScrollView(
      scrollDirection: Axis.horizontal, // Défilement horizontal
      child: Row(
        children: categories.map((category) {
          bool isSelected = selectedCategory == category["name"];
          return GestureDetector(
            onTap: () {
              setState(() {
                selectedCategory = category["name"]!;
              });
            },
            child: Container(
              margin: EdgeInsets.symmetric(horizontal: 8), // Espacement entre les boutons
              padding: EdgeInsets.symmetric(horizontal: 16, vertical: 8),
              decoration: BoxDecoration(
                color: isSelected ? Colors.red : Colors.grey[200],
                borderRadius: BorderRadius.circular(20),
              ),
              child: Row(
                children: [
                  Text(
                    category["icon"]!,
                    style: TextStyle(fontSize: 20),
                  ),
                  SizedBox(width: 8),
                  Text(
                    category["name"]!,
                    style: TextStyle(
                      color: isSelected ? Colors.white : Colors.black,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ],
              ),
            ),
          );
        }).toList(),
      ),
    );
  }

  Widget buildFoodList(List<Map<String, dynamic>> items) {
    return ListView.builder(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      itemCount: items.length,
      itemBuilder: (context, index) {
        final food = items[index];
        return GestureDetector(
          onTap: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => FoodDetailPage(food: food),
              ),
            );
          },
          child: ProductCard(
            name: food['name'],
            description: food['description'],
            price: food['price'],
            rating: food['rating'],
            imageUrl: food['image'],
            onAddToCart: () => addToCart(food),
          ),
        );
      },
    );
  }

  BottomNavigationBar buildBottomNavigationBar(BuildContext context) {
    return BottomNavigationBar(
      currentIndex: 0,
      onTap: (index) {
        if (index == 1) {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CartScreen(cartItems: cartItems),
            ),
          );
        } else if (index == 2) {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => ProfileScreen(),
            ),
          );
        }
      },
      items: [
        BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
        BottomNavigationBarItem(icon: Icon(Icons.shopping_cart), label: 'Cart'),
        BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Profile'),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Map<String, dynamic>> filteredMenuItems = getFilteredMenuItems();

    return Scaffold(
      appBar: CustomAppBar(),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              buildSearchBar(), // Ajout de la barre de recherche
              SizedBox(height: 16),
              buildCategoryButtons(),
              SizedBox(height: 16),
              Text(
                "Popular Meal Menu",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 8),
              buildFoodList(filteredMenuItems),
            ],
          ),
        ),
      ),
      bottomNavigationBar: buildBottomNavigationBar(context),
    );
  }
}
