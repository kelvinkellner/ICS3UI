// Kelvin Kellner
// Online Shopping

public class Cart
{
  Item[] items; // The Cart class carries this item array, which will store all of the items in the cart
  // This could be done in the "Accounts" class, but consider the abundance of Constructor commands that will be used on the cart, I think it would be cleaner if it was a separate class
  
  Cart()
  {
    clearCart(); // When the cart is first created, I make an empty array since there are no items. If I don't do this I get null pointer items, since I'm trying to add items to an array that doesn't exist yet
  }
  
  // This method will allow you add a brand new item to your cart
  void addItem(String name, String nameSingular, double price, int quantity)
  {
    Item item = new Item(name, nameSingular, price, quantity);
    
    // Since arrays in Java must have preset sizes, I can't just add the item to the end of the array, I have to...
    Item[] newList = new Item[this.items.length + 1]; // Create a new array that has room for an extra item...
    for(int i = 0; i < this.items.length; i++)
    {
      newList[i] = this.items[i]; // Clone all of the old items into this array...
    }
    newList[this.items.length] = item; // Add the new item to this new, larger array...
    this.items = newList; // Then replace the old array with the new one
  }
  
  void addItem(String name, String nameSingular, String price, String quantity) // Yay for method overloading ;)
  {
    // This constructer allows me to directly use Scanner input to create items, since now I can just input Strings and it will create the Item regardless of input types. This saves me code in the Shop class!
    addItem(name, nameSingular, Double.parseDouble(price), Integer.parseInt(quantity));
  }
  
  void addItem(Item item) // Yay for method overloading ;)
  {
    // This constructer allows me to add an item to the cart straigth from the access account's cart
    addItem(item.name, item.nameSingular, item.price, item.quantity);
  }
  
  void delItem(int index)
  {
    for(int i = index; i < this.items.length-1; i++) // For every item after the one we're deleting...
      this.items[i] = this.items[i+1]; // Push it forward a spot (overwriting the one we want to delete in the process)
    
    Item[] newCart = new Item[this.items.length-1]; // Create a new smaller array to replace the old one since arrays need a preset size
    
    for(int i = 0; i < newCart.length; i++)
      newCart[i] = this.items[i]; // Clone the old array into the new one (the last element in the old array is a duplicate, so this loop will ignore that one)
    this.items = newCart; // Replace the old array with the new one
  }
  
  void delItem(String name) // This constructor will allow you to delete an item by it's name as a String (it will find it's index and then call the other method using that number)
  {
    for(int i = 0; i < this.items.length; i++) // For every item...
    {
      if(this.items[i].name.equalsIgnoreCase(name) || this.items[i].nameSingular.equalsIgnoreCase(name)) // Check if it's name is the same as the item we're trying to delete. If it is...
        delItem(i); // Use the other constructor to delete it using its index number
    }
  }
  
  void clearCart()
  {
    this.items = new Item[0]; // Replace the old array of items with an empty one
  }
  
  boolean itemExists(String name) // Checks if an item with that name is in the cart
  {
    for(int i = 0; i < this.items.length; i++)
    {
      if(this.items[i].name.equalsIgnoreCase(name) || this.items[i].nameSingular.equalsIgnoreCase(name))
        return true;
    }
    return false;
  }
  
  Item itemNamed(String name) // Returns the item with the given name
  {
    for(int i = 0; i < this.items.length; i++)
    {
      if(this.items[i].name.equalsIgnoreCase(name) || this.items[i].nameSingular.equalsIgnoreCase(name))
        return this.items[i];
    }
    return new Item("BLANK","BLANK",0.00,0); // The constructor MUST return a value, so I had to include this. By properly using itemExists first, this value should never need to be returned.
  }
  
  double subtotal() // Returns the total price of all cart items before tax
  {
    double subtotal = 0.0;
    for(int i = 0; i < this.items.length; i++)
      subtotal += this.items[i].quantity * this.items[i].price; // Adds up all the prices and quantities
    subtotal = Math.round(subtotal * 100.0) / 100.0;
    return subtotal;
  }
  
  double tax(Account access) // Returns the price of tax alone
  {
    double tax = this.subtotal()*(access.tax/100.0); // Tax = Tax Rate * Sub-Total
    tax = Math.round(tax * 100.0) / 100.0;
    return tax;
  }
  
  double total(Account access) // Returns the total price of all items in the cart including tax
  {
    double total = this.subtotal() + this.tax(access); // Total = Sub-Total + (Tax Rate * Total)
    total = Math.round(total * 100.0) / 100.0;
    return total;
  }
}