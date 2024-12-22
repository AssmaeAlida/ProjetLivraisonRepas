import 'package:flutter/material.dart';
import '../models/menu_item.dart';

class MenuProvider with ChangeNotifier {
  List<MenuItem> _menuItems = [];
  
  List<MenuItem> get menuItems => _menuItems;

  void setMenuItems(List<MenuItem> items) {
    _menuItems = items;
    notifyListeners();
  }
}
