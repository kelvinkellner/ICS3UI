// Kelvin Kellner
// Online Shopping

public class Item
{
  // Store the item's data
  String name; 
  String nameSingular;
  double price;
  int quantity;
  
  Item(String name, String nameSingular, double price, int quantity)
  {
    this.name = name.trim().replaceAll("\\s+", " "); // Trim the extra spaces
    this.nameSingular = nameSingular.trim().replaceAll("\\s+", " "); // Trim the extra spaces
    this.price = (Math.round(price * 100.0))/100.0; // Round the price to the cent
    this.quantity = quantity;
  }
  
  Item(String name, String nameSingular, String price, String quantity) // This will allow you to create an item using all strings
  {
    this(name, nameSingular, Double.parseDouble(price), Integer.parseInt(quantity));
  }
  
  void newPrice(double price)
  {
    this.price = (Math.round(price * 100.0))/100.0; // Round to the nearest cent
  }
}