import java.util.*;
import java.io.*;

public class OnlineStore
{
  public static void main(String[] args) throws IOException
  {
    menu();
  } // end main
  
  public static void menu() throws IOException
  {
    Item[] cart = loadCart();
    
    Scanner scan = new Scanner(System.in);
    System.out.println("\nWelcome to Kelvin's online store!\n");
    
    boolean validAnswer = false;
    
    do {
      System.out.println("Are you a customer, or an employee? (c/e)\n");
      String in = scan.nextLine();
      if(in.equalsIgnoreCase("employee") || in.equalsIgnoreCase("e"))
      {
        employee(cart, scan);
        validAnswer = true;
      }
      else if (in.equalsIgnoreCase("customer") || in.equalsIgnoreCase("c"))
      {
        System.out.println("\nWelcome customer!");
        customer(cart, scan);
        validAnswer = true;
      }
      else
        System.out.println("\nSorry, I didn't get that.");
    } while (!validAnswer);
  } // end menu
  
  public static void customer(Item[] cart, Scanner scan) throws IOException
  {
    boolean validAnswer = false;
    do
    {
      System.out.println("\nWhat would you like to do?\n1 - Add an item your cart\n2 - Remove an item from your cart\n3 - View your cart\n4 - See our catalog of items\n5 - Save cart and exit\n6 - Checkout\n");
      
      String ans = scan.nextLine();
      if (ans.equals("1"))
        addMenu(cart, scan);
      else if (ans.equals("2"))
        removeMenu(cart, scan);
      else if (ans.equals("3"))
        viewCart(cart, scan);
      else if (ans.equals("4"))
        catalog(cart, scan);
      else if (ans.equals("5"))
      {
        saveAndExit(cart);
        validAnswer = true;
      }
      else if (ans.equals("6"))
        checkout(cart, scan);
      
    } while (!validAnswer);
  }
  
  public static void addMenu(Item[] cart, Scanner scan) throws IOException
  {
    boolean validAnswer = false;
    do
    {
      System.out.println("\nWhat item would you like to add to your cart? e.g. 2 apples (type \"menu\" to go back, or \"catalog\" to see the list of available items)");
      String answer = scan.nextLine();
      if (answer.equalsIgnoreCase("menu"))
      {
        validAnswer = true;
        //customer(cart, scan);
      }
      else if (answer.equalsIgnoreCase("catalog"))
      {
        validAnswer = true;
        catalog(cart, scan);
      }
      else
      {
        if (tryAddToCart(cart, answer))
        {
          validAnswer = true;
          System.out.println("\nAwesome! We added that to your cart :)\nWe're going to take you back to the menu now...");
        }
        else
          System.out.println("Sorry, something went wrong.\nPlease try again!\n");
      }
    } while (!validAnswer); 
  } // end add menu
  
  public static void removeMenu(Item[] cart, Scanner scan) throws IOException
  {
    boolean validAnswer = false;
    do
    {
      System.out.println("\nWhat item would you like to remove from your cart? e.g. 2 apples (type \"CLEAR\" to empty your cart, \"menu\" to go back, or \"cart\" to see what is currently in your cart)");
      String answer = scan.nextLine();
      if (answer.equalsIgnoreCase("menu"))
      {
        validAnswer = true;
        //customer(cart, scan);
      }
      else if (answer.equalsIgnoreCase("cart"))
      {
        validAnswer = true;
        viewCart(cart, scan);
      }
      else if (answer.equalsIgnoreCase("clear"))
      {
        validAnswer = true;
        cart = clearCart(cart);
      }
      else
      {
        if (tryRemoveFromCart(cart, answer))
        {
          validAnswer = true;
          System.out.println("\nAwesome! We removed that from your cart :)\nWe're going to take you back to the menu now...");
        }
        else
          System.out.println("\nSomething went wrong.\nMake sure you are not trying to remove more items than the amount that is in your cart.\nCheck that you've included a quantity (e.g. 2 apples),\nAnd have spelled the item's name correctly.\nThank you for your patience!");
      }
    } while (!validAnswer); 
  } // end remove menu
  
  public static void viewCart(Item[] cart, Scanner scan)
  {
    System.out.println("\n\t\t   - Your cart -\n");
    
    System.out.println("Item name\t\t\tPrice $\tQuantity\tTotal $");
    
    double total = 0.00;
    
    for(int i = 0; i<cart.length;i++)
    {
      if(cart[i].quantity != 0)
        System.out.println(cart[i].name + "\t\t\t" + cart[i].price + ifZero(cart[i].price) + "\t" + cart[i].quantity + "\t" + (cart[i].price * cart[i].quantity) + ifZero(cart[i].price * cart[i].quantity));
      total += cart[i].price * cart[i].quantity;
    }
    
    if(total == 0)
      System.out.println("NO ITEMS ADDED");
    
    double tax = 1.13;
    double taxDisplay = Math.round((tax - 1.0) * 1000.0)/10.0;
    double taxed = Math.round(tax * total * 100.0)/100.0;
    
    System.out.println("\n\t\t\tSUB-TOTAL\t\t" + total + ifZero(total) + "\n\t\t\tTAX\t\t" + taxDisplay + "%\n\t\t\tTOTAL\t\t" + taxed + ifZero(taxed));
  }
  
  public static void checkout(Item[] cart, Scanner scan)
  {
    System.out.println("Checking out...\n");
    
    viewCart(cart, scan); 
  }
  
  public static void employee(Item[] cart, Scanner scan) throws IOException
  {
    boolean validAnswer = false;
    do
    {
      System.out.println("\nWhat would you like to do?\n1 - Add an item to the catalog\n2 - Remove an item from the catalog\n3 - Update an item's price\n4 - View the catalog\n5 - Save catalog and exit\n");
      
      String ans = scan.nextLine();
      if (ans.equals("1"))
        cart = addCatalog(cart, scan);
      else if (ans.equals("2"))
        cart = removeCatalog(cart, scan);
      else if (ans.equals("3"))
        cart = priceChange(cart, scan);
      else if (ans.equals("4"))
        catalog(cart, scan);
      else if (ans.equals("5"))
      {
        saveAndExit(cart);
        validAnswer = true;
      }
    } while (!validAnswer);
  }
  
  public static Item[] addCatalog(Item[] cart, Scanner scan)
  {
    System.out.println("\nWhat item would you like to add to the catalog?");
    String name = scan.nextLine();
    
    System.out.println("\nWhat is this item's price in dollars?");
    double price = scan.nextDouble();
    scan.nextLine();
    
    Item[] newCart = new Item[cart.length + 1];
    
    for(int i = 0; i < cart.length; i++)
      newCart[i] = cart[i];
    
    newCart[cart.length] = new Item(name, price, 0);
    
    System.out.println("\nAwesome!\nWe added " + name + " to the catalog.");
    
    return newCart;
  }
  
  public static Item[] removeCatalog(Item[] cart, Scanner scan)
  {
    boolean valid = false;
    Item[] newCart = new Item[cart.length - 1];
    
    do
    {
      System.out.println("\nWhat item would you like to remove from the catalog? (type \"menu\" to go back)");
      String name = scan.nextLine();
      
      if (!name.equalsIgnoreCase("menu"))
      {
        for(int i = 0; i < cart.length; i++)
        {
          if (cart[i].name.equalsIgnoreCase(name))
          {
            for(int z = 0; z < i; z++)
              newCart[z] = cart[z];
            for(int z = i; z < cart.length-1; z++)
              newCart[z] = cart[z+1];
            valid = true;
          }
        }
      }
      else
      {
        valid = true;
        return cart;
      }
      
      if (!valid)
        System.out.println("Sorry, that item couldn't be found.\nPlease try again!");
    } while (!valid);
    
    System.out.println("\nAwesome!\nWe removed that item from our catalog.");
    
    return newCart;
  }
  
  public static Item[] priceChange(Item[] cart, Scanner scan)
  {
    boolean valid = false;
    do
    {
      System.out.println("\nWhat is the name of the item which you would like to change the price for? (type \"menu\" to go back)");
      String name = scan.nextLine();
      
      if (!name.equalsIgnoreCase("menu"))
      {
        for(int i = 0; i < cart.length; i++)
        {
          if (cart[i].name.equalsIgnoreCase(name))
          {
            boolean isDouble = false;
            do {
              System.out.println("\nWhat should be the new price per item for " + cart[i].name + "?");
              try
              {
                cart[i].price = scan.nextDouble();
                scan.nextLine();
                System.out.println("\nGreat!\nThe price for " + cart[i].name + " has been changed to $" + cart[i].price + ifZero(cart[i].price) + ".");
                isDouble = true;
                valid = true;
                return cart;
              }
              catch (Exception e)
              {
                System.out.println("\nSorry, I don't think that was a valid price.\nPlease try again!");
              }
            } while (!isDouble);
          } // end if
        } // end for
      } // end bigger if
      else
      {
        valid = true;
        return cart;
      }
      
      if (!valid)
        System.out.println("Sorry, that item couldn't be found.\nPlease try again!");
    } while (!valid);
    
    System.out.println("\nAwesome!\nWe removed that item from our catalog.");
    
    return cart;
  }
    
  public static void catalog(Item[] cart, Scanner scan)
  {
    System.out.println("\n\t   - Catalog -\n");
    
    System.out.println("Item name\t\t\tPrice");
    
    for(int i = 0; i<cart.length;i++)
      System.out.println(cart[i].name + "\t\t\t$ " + cart[i].price + ifZero(cart[i].price) + " each");
    
    if (cart.length == 0)
      System.out.println("NO ITEMS FOR SALE");
  }
  
  public static boolean tryAddToCart(Item[] cart, String toAdd)
  {
    String[] split = toAdd.split(" ");
    
    try
    {
      int amount = Integer.parseInt(split[0]);
      String item = "";
      
      for (int i = 1; i < split.length - 1; i++)
        item += split[i] + " ";
      item += split[split.length - 1];
      
      for (int i = 0; i < cart.length; i++)
      {
        if (cart[i].name.equalsIgnoreCase(item))
        {
          cart[i].quantity+=amount;
          return true;
        }
      }
    }
    catch (Exception e)
    {
      return false;
    }
    return false;
  }
  
  public static boolean tryRemoveFromCart(Item[] cart, String toRemove)
  {
    String[] split = toRemove.split(" ");
    
    try
    {
      int amount = Integer.parseInt(split[0]);
      String item = "";
      
      for (int i = 1; i < split.length - 1; i++)
        item += split[i] + " ";
      item += split[split.length - 1];
      
      for (int i = 0; i < cart.length; i++)
      {
        if (cart[i].name.equalsIgnoreCase(item))
        {
          if (cart[i].quantity >= amount)
          {
            cart[i].quantity-=amount;
            return true;
          }
        }
      }
    }
    catch (Exception e)
    {
      return false;
    }
    return false;
  }
  
  public static Item[] clearCart(Item[] cart)
  {
    for (int i = 0; i < cart.length; i++)
      cart[i].quantity = 0;
    return cart;
  }
  
  public static void saveAndExit(Item[] cart) throws IOException
  {
    FileWriter file = new FileWriter("ShoppingCart.txt");
    saveCart(file, cart);
    System.out.println("\nThanks for shopping with us!\nSee you later :)\n");
  }
  
  public static Item[] loadCart() throws IOException
  {
    try
    {
      FileReader file = new FileReader("ShoppingCart.txt");
      Item[] cart = readCart(file);
      file.close();
      return cart;
    }
    catch (IOException e)
    {
      FileWriter file = new FileWriter("ShoppingCart.txt");
      Item[] cart = defaultCart(file);
      file.close();
      return cart;
    }
  } // end load cart
  
  public static Item[] readCart(FileReader file) throws IOException
  {
    BufferedReader read = new BufferedReader(file);
    read.mark(10000);
    
    int size = cartSize(read);
    read.reset();
    Item[] cart = new Item[size];
    
    for (int i = 0; i < size; i++)
    {
      String line = read.readLine();
      String split[] = line.split("@");
      
      cart[i] = new Item(split[0], Double.parseDouble(split[1]), Integer.parseInt(split[2]));
    }
    
    read.close();
    return cart;
  } // end read cart
  
  public static void saveCart(FileWriter file, Item[] cart) throws IOException
  {
    PrintWriter write = new PrintWriter(file);
    
    for(int i = 0; i < cart.length; i++)
    {
      String line = cart[i].name + "@" + cart[i].price + "@" + cart[i].quantity;
      write.println(line);
    }
    write.println("*");
    write.close();
  }
  
  public static Item[] defaultCart(FileWriter file) throws IOException
  {
    String defaults[] = {"Apples", "1.00", "Oranges", "2.50", "Grapes", "3.00"};
    
    Item[] cart = new Item[defaults.length/2];
    
    for(int i = 0; i < defaults.length; i+=2)
      cart[(i/2)] = new Item(defaults[i], Double.parseDouble(defaults[i+1]), 0);
    
    //writeCart(file, cart);
    return cart;
  }
  
  public static int cartSize(BufferedReader read) throws IOException
  {
    boolean stop = false;
    
    int size = 0;
    
    do
    {
      String line = read.readLine();
      
      if (!line.equals("*"))
        size++;
      else
        stop = true;
    } while (!stop);
    
    return size;
  } // end cart size
  
  public static String ifZero(double price)
  {
    if ((price*10) % 1.0 == 0.0)
      return "0";
    else
      return "";
  } // end if zero
} // end class