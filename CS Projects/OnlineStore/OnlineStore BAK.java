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
    Scanner scan = new Scanner(System.in);
    System.out.println("\nWelcome to Kelvin's online store!\n");
    
    Account[] accs = loadAccs();
    
    boolean valid = false;
    
    do {
      System.out.println("\nDo you already have a store account?");
      String in = scan.nextLine();
      if(in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("y"))
      {
        Account user = login(accs);
        
        valid = true;
      }
      else if (in.equalsIgnoreCase("no") || in.equalsIgnoreCase("no"))
      {
        Account user = signup();
        //accs = addAccount(accs, user);
        //Item[] cart = loadCart(user);
        valid = true;
      }
      else
        System.out.println("Sorry, that wasn't a valid response.\nPlease try again!");
    } while (!valid);
    
    Item[] cart = loadCart();
    
    do {
      System.out.println("Are you a customer, or an employee? (c/e)\n");
      String in = scan.nextLine();
      if(in.equalsIgnoreCase("employee") || in.equalsIgnoreCase("e"))
      {
        employee(cart, scan);
        valid = true;
      }
      else if (in.equalsIgnoreCase("customer") || in.equalsIgnoreCase("c"))
      {
        System.out.println("\nWelcome customer!");
        customer(cart, scan);
        valid = true;
      }
      else
        System.out.println("\nSorry, I didn't get that.");
    } while (!valid);
  } // end menu
  
  public static Account[] loadAccs() throws IOException
  {
    try
    {
      FileReader file = new FileReader("StoreAccounts.txt");
      BufferedReader read = new BufferedReader(file);
      
      int size = fileSize(read);
      
      Account[] accs = new Account[size];
      
      for(int i = 0; i < size; i++)
      {
        String line = read.readLine();
        String[] split = line.split("@");
        
        accs[i] = new Account(Double.parseDouble(split[0]), split[1], split[2], split[3]);
      }
      
      read.close();
      file.close();
    }
    catch (Exception e)
    {
      FileWriter file = new FileWriter("StoreAccounts.txt");
      PrintWriter write = new PrintWriter(file);
      
      write.println("*");
      
      Account[] accs = new Account[0];
      return accs;
    }
  } // end load accounts
  
  public static Account signup() throws IOException
  {
    System.out.println("\n- User Sign Up -");
    Account user = new Account();
    
    System.out.println("\nPlease choose a username.");
    user.username = scan.NextLine();
    
    System.out.println("\nPlease choose a password.");
    user.hashed = hash(scan.NextLine());
    
    System.out.println("\nWhat is your name?");
    user.name = scan.NextLine();
    
    System.out.println("\nHow much money would you like to deposit to start?");
    user.balance = scan.NextLine();
    
    FileReader fileIn = new FileReader("StoreAccounts.txt");
    BufferedReader read = new BufferedReader(file);
    
    boolean stop = false;
    String out = "";
    do{
      String line = read.readLine();
      if (line!="*")
        out+="\n" + line;
      else
        stop = true;
    } while (!stop);
    
    read.close();
    fileIn.close();
    
    FileWriter file = new FileWriter("StoreAccounts.txt");
    PrintWriter write = new PrintWriter(file);
    
    out += "\n" + user.balance + "@" + user.name + "@" + user.username + "@" + user.hashed;
    
    write.println(out);
  }
  
  public static Account login(Account[] accs)
  {
    Account user = new Account();
    return user;
  }
  
  public static void customer(Item[] cart, Scanner scan) throws IOException
  {
    boolean valid = false;
    do
    {
      System.out.println("\nWhat would you like to do?\n1 - Add an item your cart\n2 - Remove an item from your cart\n3 - View your cart\n4 - Checkout\n5 - See our catalog of items\n6 - Save cart and exit\n");
      
      String ans = scan.nextLine();
      if (ans.equals("1"))
        addMenu(cart, scan);
      else if (ans.equals("2"))
        removeMenu(cart, scan);
      else if (ans.equals("3"))
        viewCart(cart, scan);
      else if (ans.equals("4"))
        checkout(cart, scan);
      else if (ans.equals("5"))
        catalog(cart, scan);
      else if (ans.equals("6"))
      {
        saveAndExit(cart);
        valid = true;
      }
    } while (!valid);
  }
  
  public static void addMenu(Item[] cart, Scanner scan) throws IOException
  {
    boolean valid = false;
    do
    {
      System.out.println("\nWhat item would you like to add to your cart? e.g. 2 apples (type \"menu\" to go back, or \"catalog\" to see the list of available items)");
      String answer = scan.nextLine();
      if (answer.equalsIgnoreCase("menu"))
      {
        valid = true;
        //customer(cart, scan);
      }
      else if (answer.equalsIgnoreCase("catalog"))
      {
        valid = true;
        catalog(cart, scan);
      }
      else
      {
        if (tryAddToCart(cart, answer))
        {
          valid = true;
          System.out.println("\nAwesome! We added that to your cart :)\nWe're going to take you back to the menu now...");
        }
        else
          System.out.println("Sorry, something went wrong.\nPlease try again!\n");
      }
    } while (!valid); 
  } // end add menu
  
  public static void removeMenu(Item[] cart, Scanner scan) throws IOException
  {
    boolean valid = false;
    do
    {
      System.out.println("\nWhat item would you like to remove from your cart? e.g. 2 apples (type \"menu\" to go back, or \"cart\" to see what is currently in your cart)");
      String answer = scan.nextLine();
      if (answer.equalsIgnoreCase("menu"))
      {
        valid = true;
        //customer(cart, scan);
      }
      else if (answer.equalsIgnoreCase("cart"))
      {
        valid = true;
        viewCart(cart, scan);
      }
      else
      {
        if (tryRemoveFromCart(cart, answer))
        {
          valid = true;
          System.out.println("\nAwesome! We removed that from your cart :)\nWe're going to take you back to the menu now...");
        }
        else
          System.out.println("Sorry, something went wrong.\nPlease try again!\n");
      }
    } while (!valid); 
  } // end remove menu
  
  public static void viewCart(Item[] cart, Scanner scan)
  {
    System.out.println("\n\t\t   - Your cart -\n");
    
    System.out.println("Item name\t\t\tPrice $\tQuantity\tTotal $");
    
    double total = 0.00;
    
    for(int i = 0; i<cart.length;i++)
    {
      if(cart[i].quantity != 0)
        System.out.println(cart[i].name + "\t\t\t" + cart[i].price + "\t" + cart[i].quantity + "\t" + (cart[i].price * cart[i].quantity));
      total += cart[i].price * cart[i].quantity;
    }
    
    if(total == 0)
      System.out.println("NO ITEMS ADDED");
    
    double tax = 1.13;
    double taxDisplay = Math.round((tax - 1.0) * 1000.0)/10.0;
    double taxed = Math.round(tax * total * 100.0)/100.0;
    
    System.out.println("\n\t\t\tSUB-TOTAL\t\t" + total + "\n\t\t\tTAX\t\t" + taxDisplay + "%\n\t\t\tTOTAL\t\t" + taxed);
  }
  
  public static void checkout(Item[] cart, Scanner scan)
  {
    System.out.println("Checking out...\n");
    
    viewCart(cart, scan);
    
    Account[] accs = loadAccounts();
    System.out.println("Which account would you like to use?");
    
    for(int i = 0; i < accs.length; i++)
      System.out.println(i + "\t" + accs[i].name);
  }
  
  public static void employee(Item[] cart, Scanner scan)
  {
    
  }
  
  public static void catalog(Item[] cart, Scanner scan)
  {
    System.out.println("\n\t   - Catalog -\n");
    
    System.out.println("Item name\t\t\tPrice");
    
    for(int i = 0; i<cart.length;i++)
    {
      System.out.println(cart[i].name + "\t\t\t$ " + cart[i].price + " each");
    }
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
    
    int size = fileSize(read);
    
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
  
  public static int fileSize(BufferedReader read) throws IOException
  {
    boolean stop = false;
    
    int size = 0;
    
    read.mark(10000);
    
    do
    {
      String line = read.readLine();
      
      if (!line.equals("*"))
        size++;
      else
        stop = true;
    } while (!stop);
    
    read.reset(0);
    
    return size;
  } // end cart size
  
  public static boolean checkAnswer(String one, String two)
  {
    if (one.equalsIgnoreCase(two))
      return true;
    else
      return false;
  } // end check answer
} // end class