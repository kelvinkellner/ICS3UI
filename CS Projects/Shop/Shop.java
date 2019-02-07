// Kelvin Kellner
// Online Shopping
// Mrs. Cooper
// 16 May 2018

// Welcome to my Online Store!
// This is a program that uses 4 classes to allow users to sign up for accounts and purchase items "online"
//
// Features include:
// - account signup (using username + password)
// - simple password encryption
// - saving/loading data to a file stored offline
// - multiple items with different prices and names
// - individual bank balances and shopping carts for each account
// - manager accounts which can manage the shop items, as well as the other accounts in the system
//
// This program including new challenges for me because:
// - in addition to arrays, I also used classes, objects, and constructors for each cart, item, and account
// - I used the java io class to write to a file offline, which I have never done in this complexity before
// - I had to learn to encrypt passwords, lol
// - method overloading ;)
//
// CLASSES
// Shop:
// The framework of the program. This class is responsible for drawing the user interface, collecting user reponses
// using scanners, and calling objects and their constructors whenever needed.
//
// Account:
// This class is used for everything involving the various user accounts, managing their carts, storing their account
// info, like their balance, username, display name, etc.
// There are two types of accounts: customer accounts and manager accounts
// Customer accounts can purchase items and manage their own account
// Manager accounts can edit the shop catalog and manage the settings for all other accounts, but cannot purchase items
// There is also one special account object that is used consistently throughout the program, called "access".
// This account performs all generic account actions, or any actions that affect more than just one account.
// Access also contains the array of all the accounts, and can be used to add/remove accounts, save/load data, etc.
//
// Cart:
// A cart object is stored in every account. The cart stores an array called "items" that stores all the user's items.
// It can be used to add/remove items from a cart, calculate totals for all the items in a cart, etc.
// The cart contained in the "access" object is used as the shop catalog, and adding items there will allow other users
// to purchase them in the store.
//
// Item:
// This class stores the information for each individual item in the cart or in the store.
// It stores the name, price, and quantity of each item.
//
// Cool stuff you gotta see:
// - the "saveData" and "loadData" account constructors took me ages. please look at those, and the "storedata.txt"
//   file that is created after running the program. it's all pretty cool
// - peep the "hash" constructor in the "account" class. it's the one that encrypts the passwords, it's pretty cool
// - look at the way I get around predetermind array sizes in the "addItem" and "deleteItem" constructors in "Cart"
// - idk, the rest of it is all pretty cool too tbh, there's tons of repition tho
// - the ifZero method for the account class decides whether or not to add an extra "0" onto the end of prices
//
// Cool, cool, I hope you like it :))

import java.util.*;
import java.io.*;

public class Shop
{
  public static void main(String[] args) throws IOException, InterruptedException
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("\nWelcome to Kelvin's digital store!");
    
    Account access = new Account(); // Create an access account for initialization, this is not a real account, it just runs generic constructors, like saving all acount data, etc. It will also contain the array of all actual accounts
    access.loadData(); // Load all user info that was previously saved
    
    if(access.accs.length==0) // If there are no accounts in the array
    {
      System.out.println("\nNo previously saved accounts were found :(\nPlease sign up!");
      signup(scan, access);
    }
    else
    {
      System.out.println("\n" + access.accs.length + " accounts have been found on this system.\nHowever, if you would like to sign up for a new account, you can create one for free.");
      shouldLogin(scan, access); // This method asks them whether they want to sign up or log in
    }
  } // Close Main
  
  public static void customer(Scanner scan, Account access, Account user) throws IOException, InterruptedException // This will be the menu of options for our customer
  {
    // Display the options
    System.out.println("\nWhat would you like to do?");
    System.out.println("1 - View the shop");
    System.out.println("2 - Add an item to your cart");
    System.out.println("3 - Remove an item from your cart");
    System.out.println("4 - View your cart");
    System.out.println("5 - Manage your account");
    System.out.println("6 - Checkout");
    System.out.println("");
    
    String answer = scan.nextLine(); // Collect their response
    
    // Call the proper method according to their response
    if(answer.equals("1"))
    {
      shop(access, user); // Print the shop catalog...
      customer(scan, access, user); // Then return to the list of controls
    }
    else if(answer.equals("2"))
      addToCart(scan, access, user);
    else if(answer.equals("3"))
      subFromCart(scan, access, user);
    else if(answer.equals("4"))
    {
      viewCart(access, user); // Print the shopping cart...
      customer(scan, access, user); // Then return the list of controls
    }
    else if(answer.equals("5"))
      myAccount(scan, access, user);
    else if(answer.equals("6"))
      checkout(scan, access, user);
    else
    {
      System.out.println("\nSorry!\nI don't think that was a valid answer :(\nPlease enter the number that matches your option.");
      customer(scan, access, user); // Run this method again (which removes the need for placing everything in a massive while loop
    }
  } // Close Customer
  
  public static void manager(Scanner scan, Account access, Account user) throws IOException, InterruptedException // This will be the menu of options for our managers
  {
    System.out.println("\nWhat would you like to do?");
    System.out.println("1 - View the shop");
    System.out.println("2 - Add an item to the shop catalog");
    System.out.println("3 - Remove an item from the shop catalog");
    System.out.println("4 - Change an item's price");
    System.out.println("5 - Manage all accounts");
    System.out.println("6 - Sign Out");
    System.out.println("");
    
    String answer = scan.nextLine();
    
    // Call the proper method according to their response
    if(answer.equals("1"))
    {
      shop(access, user); // Print the shop catalog...
      manager(scan, access, user); // Then return to the list of controls
    }
    else if(answer.equals("2"))
      addToShop(scan, access, user);
    else if(answer.equals("3"))
      subFromShop(scan, access, user);
    else if(answer.equals("4"))
      changePrice(scan, access, user);
    else if(answer.equals("5"))
      manageAccounts(scan, access, user);
    else if(answer.equals("6"))
      logout(scan, access, user);
    else
    {
      System.out.println("\nSorry!\nI don't think that was a valid answer :(\nPlease enter the number that matches your option.");
      customer(scan, access, user); // Run this method again (which removes the need for placing everything in a massive while loop
    }
  } // Close Manager
  
  public static void shop(Account access, Account user)
  {
    System.out.println("\n\t   - Catalog -\n");
    // Pretty formatting :))
    System.out.println("Item name\t\t\tPrice");
    
    for(int i = 0; i<access.cart.items.length;i++) // For each item in the shop...
      System.out.println(access.cart.items[i].name + access.tabber(access.cart.items[i].name) + "$ " + access.cart.items[i].price + access.ifZero(access.cart.items[i].price) + " each"); // Print it's data
    
    if (access.cart.items.length == 0) // If there are no items...
      System.out.println("NO ITEMS FOR SALE"); // Tell them
  } // Close Shop
  
  public static void addToCart(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nWhat would you like to add to you cart? e.g. 2 apples (type \"menu\" to return to your list of options)");
    
    String answer = scan.nextLine(); // Scan their response
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      customer(scan, access, user); // Let them go back if they want to
    else
    {
      // Now, we're going to parse there answer until we find the section that represents a number, if we can find one
      String[] split = answer.split(" ");
      String text = ""; // This will store the item name after it has been seperated from the quantity
      int amount = 0; // The amount of items they wish to purchase, which we will parse
      int overload = 0; // The number of different amounts they input (if the put more that one quantity for the same item, we will not continue)
      
      for(int i = 0; i<split.length; i++) // This method will separate the number from the rest of the text)
      {
        try
        {
          amount = Integer.parseInt(split[i]); // First it will try to parse the segment as an integer to see if it is our quantity...
          overload++; // If they enter more than one number for there item, we will throw an error. Without this, our program would just take whichever number came last, which still wouldn't be bad
        }
        catch (Exception e) // But if that doesn't work, and this part is just text...
        {
          // Then it will combine the segment with the other elements to form our item name (e.g. 5 chocolate milks would be broken into 5, chocolate, milks. This method will combine the chocolate and the milk back together so we can search for "chocolate milk")
            text+=split[i]+" "; // Re-add the spaces between each word
        }
      }
      text = text.trim().replaceAll("\\s+", " "); // Found this handy little method online. It replaces all sections with more than one space, with a single space. This way if they add multiple spaces between words, or add an extra space on the end, our program will fix it for them. Also, our program adds a space on the end e.g. "chocolate milk ", this gets rid of that extra space so it can be searched for
      if (overload == 0) // If they failed to enter a number...
      {
        System.out.println("\nSorry!\nI don't think you included a quantity with your item.\nPlease try again..."); //  Tell them...
        addToCart(scan, access, user); // Then allow them to try again
      }
      else if (overload>1) // If they enter more than one different quantity
      {
        System.out.println("\nHmmm.\nI think you entered too many different numbers.\nPlease try again..."); //  Tell them...
        addToCart(scan, access, user); // Then allow them to try again
      }
      else if (amount <= 0) // If they enter too small of a number...
      {
        System.out.println("\nUmmmm?\nEither that number was too small, or there wasn't a number at all.\nPlease try again..."); //  Tell them...
        addToCart(scan, access, user); // Then allow them to try again
      }
      if(access.cart.itemExists(text) && !user.cart.itemExists(text)) // If the item is not yet in their cart, but it is in the shop...
      {
        System.out.println("\nGreat!\nWe added " + amount + " " + text + " to your cart."); // Tell them...
        user.cart.addItem(access.cart.itemNamed(text)); // Add it to their cart...
        user.cart.itemNamed(text).quantity = amount; // And set the quantity
        access.saveData(); // Don't forget to save!
        customer(scan, access, user); // And bring them back to the menu when they're done
      }
      else if(access.cart.itemExists(text) && user.cart.itemExists(text))// If it is in both places...
      {
        System.out.println("\nGreat!\nWe added " + amount + " " + text + " to your cart."); // Tell them...
        user.cart.itemNamed(text).price = access.cart.itemNamed(text).price; // Make sure the price is updated...
        user.cart.itemNamed(text).quantity += amount; // Then add to the quantity...
        access.saveData(); // Don't forget to save!
        customer(scan, access, user); // And bring them back to the menu when they're done
      }
      else if (!access.cart.itemExists(text) && user.cart.itemExists(text))// If it's not an item available in the shop, but it is in their cart...
      {
        System.out.println("\nSorry!\nThat item is no longer for sale.\nPlease try something else..."); //  Tell them...
        addToCart(scan, access, user); // Then allow them to try again
      }
      else // If the item could not be found...
      {
        System.out.println("\nSorry!\nThat item could not be found.\nPlease try something else..."); //  Tell them...
        addToCart(scan, access, user); // Then allow them to try again
      }
    }
  } // Close Add To Cart
  
  public static void subFromCart(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    // THE FIRST HALF OF THIS METHOD IS ESSENTIALLY THE SAME AS ADD TO CART
    System.out.println("\nWhat would you like to remove from your cart? e.g. 3 grapes (type \"menu\" to return to your list of options)");
    
    String answer = scan.nextLine(); // Get their answer
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      customer(scan, access, user); // Let them go back if they want to
    else
    {
      // Now, we're going to parse there answer until we find the section that represents a number, if we can find one
      String[] split = answer.split(" ");
      String text = ""; // This will store the item name after it has been seperated from the quantity
      int amount = 0;
      
      for(int i = 0; i<split.length; i++)
      {
        try
        {
          amount = Integer.parseInt(split[i]); // Try to parse as quantity, otherwise...
        }
        catch (Exception e)
        {
          text+=split[i]+" "; // Add it to the item name
        }
      }
      text = text.trim().replaceAll("\\s+", " "); // Remove all extra spaces from the item name
      if (answer == text) // If they failed to enter a number...
      {
        System.out.println("\nSorry!\nI don't think you included a quantity with your item.\nPlease try again..."); //  Tell them...
        subFromCart(scan, access, user); // Then allow them to try again
      }
      else if (amount <= 0) // If they enter too small of a number...
      {
        System.out.println("\nUmmmm?\nEither that number was too small, or there wasn't a number at all.\nPlease try again..."); //  Tell them...
        subFromCart(scan, access, user); // Then allow them to try again
      }
      if(user.cart.itemExists(text)) // If the item is found in their cart
      {
        if(user.cart.itemNamed(text).quantity - amount >= 0) // If they are not taking away more of the item than they own
        {
          System.out.println("\nGreat!\nWe removed " + amount + " " + text + " from your cart.\nThere are now " + (user.cart.itemNamed(text).quantity - amount) + " " + text + " remaining."); // Tell them...
          user.cart.itemNamed(text).quantity -= amount; // Remove that many of the item
          if(user.cart.itemNamed(text).quantity == 0) // If their are no longer any of this item in their cart...
            user.cart.delItem(text); // Remove the item from their cart altogether
          access.saveData(); // Don't forget to save!
          customer(scan, access, user); // And bring them back to the menu when they're done
        }
        else // If they are taking away too many
        {
          System.out.println("\nUhhh,\nWe can't exactly take away that many.\nPlease try again with a smaller number..."); //  Tell them...
          subFromCart(scan, access, user); // Then allow them to try again
        }
      }
      else
      {
        System.out.println("\nSorry!\nThat item could not be found in your cart.\nPlease try something else..."); //  Tell them...
        subFromCart(scan, access, user); // Then allow them to try again
      }
    }
  } // Close Sub From Cart
  
  public static void viewCart(Account access, Account user)
  {
    System.out.println("\n\t\t   - Your cart -\n");
    
    System.out.println("Item name\t\t\tPrice $\tQuantity\tTotal $");
    
    for(int i = 0; i<user.cart.items.length;i++) // Print each item's information
    {
      System.out.println(user.cart.items[i].name + access.tabber(user.cart.items[i].name) + user.cart.items[i].price + access.ifZero(user.cart.items[i].price) + "\t" + user.cart.items[i].quantity + "\t" + (Math.round(100.0 * user.cart.items[i].price * user.cart.items[i].quantity) / 100.0) + access.ifZero(Math.round(100.0 * user.cart.items[i].price * user.cart.items[i].quantity) / 100.0)); // Print out 1 item's data to the cart...
    }
    
    if(user.cart.items.length==0) // If their are no items in the cart
      System.out.println("NO ITEMS ADDED");
    
    System.out.println("\n\t\t\tSUB-TOTAL $\t" + user.cart.subtotal() + access.ifZero(user.cart.subtotal()) + "\n\t\t\tTAX $\t\t" + user.cart.tax(access) + "\n\t\t\tTOTAL $\t\t" + user.cart.total(access) + access.ifZero(user.cart.total(access))); // Print out totals and tax values
  } // Close View Cart
  
  public static void myAccount(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nWhat would you like to do with your account? (type \"menu\" to go back to the main menu)");
    System.out.println("1 - Change my display name");
    System.out.println("2 - Change my password");
    System.out.println("3 - Deposit money");
    System.out.println("4 - Change account type");
    System.out.println("5 - Delete my account");
    System.out.println("6 - Sign out");
    System.out.println("");
    
    String answer = scan.nextLine();
    
    // Call the proper method according to their response
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      customer(scan, access, user);
    else if(answer.equals("1"))
      changeName(scan, access, user);
    else if(answer.equals("2"))
      changePassword(scan, access, user);
    else if(answer.equals("3"))
      deposit(scan, access, user);
    else if(answer.equals("4"))
      changeType(scan, access, user);
    else if(answer.equals("5"))
      delete(scan, access, user);
    else if(answer.equals("6"))
      logout(scan, access, user);
    else
    {
      System.out.println("\nSorry, that wasn't valid response.\nPlease try again...");
      myAccount(scan, access, user); // Give them a chance to try again
    }
  } // Close My Account
  
  public static void checkout(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    viewCart(access, user); // First display their cart...
    System.out.println("\nYour account balance is currently: $" + user.balance + user.ifZero(user.balance)); // Grab their balance
    System.out.println("\nWould you like to checkout for $" + user.cart.total(access) + user.ifZero(user.cart.total(access)) + "?"); // Display the total price
    
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
    {
      if(user.cart.items.length == 0) // If their cart is empty
      {
        System.out.println("\nYour cart is empty right now.\nTry adding items to your cart first...");
        customer(scan, access, user); // Bring them back
      }
      else if(user.balance >= user.cart.total(access)) // If they have enough money
      {
        user.balance = Math.round(100.0 * (user.balance - user.cart.total(access))) / 100.0; // Remove the money from their account
        user.cart.clearCart(); // Empty the cart
        access.saveData(); // Save
        System.out.println("\nWonderful!\nYour items have been purchased :)");
        customer(scan, access, user); // Bring them back to the main menu
      }
      else // If they don't have enough money
      {
        System.out.println("\nSorry, you don't have enough money to checkout right now :(\nTry depositing more money, or removing some items from your cart.");
        customer(scan, access, user);
      }
    }
    else if(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) // If they don't want to checkout yet
    {
      System.out.println("\nOkay!");
      customer(scan, access, user);
    }
    else // If they type an invalid answer
    {
      System.out.println("\nSorry, I didn't get that answer.\nPlease try again...");
      checkout(scan, access, user); // Let them try again
    }
  } // Close Checkout
  
  public static void addToShop(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nWhat's the PLURAL name of the item you would like to add to the shop? (e.g. \"Apples\")");
    String plural = scan.nextLine().trim().replaceAll("\\s+", " "); // Save their answer without extra spaces
    System.out.println("\nWhat's the SINGULAR name of the item you would like to add to the shop? (e.g. \"Apple\")");
    String singular = scan.nextLine().trim().replaceAll("\\s+", " "); // Save their answer without extra spaces
    System.out.println("\nHow much does each " + singular + " cost in dollars?");
    String amount = scan.nextLine();
    if(plural.equalsIgnoreCase("menu") || singular.equalsIgnoreCase("menu") || amount.equalsIgnoreCase("menu") || plural.equalsIgnoreCase("back") || singular.equalsIgnoreCase("back") || amount.equalsIgnoreCase("back"))
      manager(scan, access, user);
    try // Try to add the item
    {
      double price = Math.round(100.0 * Double.parseDouble(amount)) / 100.0; // Tries to parse their answer as a double, then round it
      access.cart.addItem(plural, singular, price, 0);
      access.saveData();
      System.out.println("\nAwesome!\nWe've added " + plural + " to the catalog.");
      manager(scan, access, user);
    }
    catch (Exception e) // If their amount could not be parsed as a double
    {
      System.out.println("\nSorry, I don't think that was a valid amount.\nPlease try again, or type \"menu\" into any of the boxes to return to your list of options...");
      addToShop(scan, access, user);
    }
  } // Close Add To Shop
  
  public static void subFromShop(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nWhat's the name of the item you would like to delete from the shop? (type \"menu\" to go back to the list of options)");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manager(scan, access, user);
    else
    {
      if(access.cart.itemExists(answer)) // If it exists...
      {
        access.cart.delItem(answer); // Delete it from the shop...
        for(int i = 0; i < access.accs.length; i++) // And for each user...
        {
          if(access.accs[i].cart.itemExists(answer)) // If the item's in their cart...
            access.accs[i].cart.delItem(answer); // Delete it from there too
        }
        access.saveData(); // Save again ;)
        System.out.println("\nExcellent!\nWe've removed that item from the shop.");
        manager(scan, access, user);
      }
      else // If the item doesn't exist...
      {
        System.out.println("\nSorry,\nThat item couldn't be found in the shop :(\nPlease try again...");
        subFromShop(scan, access, user); // Let them try again
      }
    }
  } // Close Sub From Shop
  
  public static void changePrice(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nWhat's the name of the item you would like to change? (or type \"menu\" to go back)");
    String item = scan.nextLine();
    if(item.equalsIgnoreCase("back") || item.equalsIgnoreCase("menu"))
      manager(scan, access, user);
    else
    {
      if(access.cart.itemExists(item))
      {
        System.out.println("\nThe price of " + access.cart.itemNamed(item).name + " is currently $" + access.cart.itemNamed(item).price + access.ifZero(access.cart.itemNamed(item).price) + "/" + access.cart.itemNamed(item).nameSingular + ".\nWhat would you like the new price to be in dollars? (e.g. 5.50)");
        try
        {
          double price = scan.nextDouble(); // Try to parse the price
          scan.nextLine();
          price = Math.round(100.0 * price) / 100.0; // Round the price
          access.cart.itemNamed(item).newPrice(price); // Update the item's price in the shop
          
          // We will also update the price of the item for each user who has the item in their cart
          for(int i = 0; i < access.accs.length; i++) // For each user...
          {
            if(access.accs[i].cart.itemExists(item)) // Check if the item is in their cart. If it is...
              access.accs[i].cart.itemNamed(item).newPrice(price); // Update the price
          }
          
          access.saveData(); // Don't forget to save ;)
          System.out.println("\nPerfect!\nThe price of " + access.cart.itemNamed(item).name + " has been changed to $" + price + access.ifZero(price) + ".");
          manager(scan, access, user);
        }
        catch (Exception e)
        {
          System.out.println("\nSorry!\nThat wasn't a valid amount.\nPlease try again...");
          changePrice(scan, access, user); // Have them try again
        }
      }
      else
      {
        System.out.println("\nSorry, that item couldn't be found.\nPlease try again.");
        changePrice(scan, access, user); // Have them try again
      }
    }
  } // Close Change Item Price
  
  public static void manageAccounts(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.print("\nPlease enter the number beside the account you would like to manage. (or type \"menu\" to go back to the menu)\n");
    for(int i = 0; i < access.accs.length; i++)
      System.out.println((i+1) + " - " + access.accs[i].username); // Print out a list of all accounts
    System.out.println("");
    
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manager(scan, access, user);
    else
    {
      try
      {
        int num = Integer.parseInt(answer) - 1; // Remove 1, since the counter starts at 1, but the array starts at 0
        manageAccount(scan, access, user, access.accs[num]); // Start managing that account
      }
      catch (Exception e)
      {
        System.out.println("\nSorry, that wasn't a valid number.\nPlease try again...");
        manageAccounts(scan, access, user);
      }
    }
  } // Close Manage Accounts
  
  public static void manageAccount(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    System.out.println("\n" + account.username + "\nName: " + account.name + "\nBalance: $" + account.balance + "\nManager Account: " + account.manager + "\nItems in Cart: " + account.cart.items.length); // User info
    System.out.println("\nWhat would you like to do to this account? (or type \"back\" to choose a different account)");
    System.out.println("1 - Change username");
    System.out.println("2 - Change display name");
    System.out.println("3 - Change password");
    System.out.println("4 - Update balance");
    System.out.println("5 - Change account type");
    System.out.println("6 - Clear cart");
    System.out.println("7 - Delete account");
    System.out.println("");
    
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manageAccounts(scan, access, user);
    else if(answer.equals("1"))
      changeUsername(scan, access, user, account);
    else if(answer.equals("2"))
      changeName(scan, access, user, account);
    else if(answer.equals("3"))
      changePassword(scan, access, user, account);
    else if(answer.equals("4"))
      updateBalance(scan, access, user, account);
    else if(answer.equals("5"))
      changeType(scan, access, user, account);
    else if(answer.equals("6")) // This one was really short and is only really used here, so I just coded it in the same method :)
    {
      account.cart.clearCart(); // Clear their account
      access.saveData();
      System.out.println("Great!\n" + account.name + "'s cart has been cleared.");
      manageAccount(scan, access, user, account);
    }
    else if(answer.equals("7"))
    {
      if(user.username == account.username)
      {
        System.out.println("\nYour account has been deleted successfully.");
        access.delAccount(access.accountIndex(account)); // Delete the account
        access.saveData(); // Save
        Thread.sleep(500);
        logout(scan, access, user); // If they delete their own account we'll want to log them out, unless we want a million null pointer errors, yikes!
      }
      else
      {
        System.out.println("\nThe account has been deleted successfully.");
        access.delAccount(access.accountIndex(account)); // Delete the account
        access.saveData(); // Save
        manageAccounts(scan, access, user); // This time we have to bring them to the menu with all the accounts, since this account doesn't exist anymore
      }
    }
    else
    {
      System.out.println("\nSorry, that wasn't a valid answer.\nPlease try again...");
      manageAccount(scan, access, user, account);
    }
  } // Close Manage Account
  
  public static void changeName(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nYour name is currently " + user.name + ".\nWhat would you like your new name to be? (or type \"back\" to cancel)");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      myAccount(scan, access, user);
    else
    {
      user.name = answer;
      access.saveData();
      System.out.println("\nWe will call you " + user.name + " from now on :)");
      myAccount(scan, access, user);
    }
  } // Close Change Name
  
  public static void changePassword(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nPlease enter your new password:");
    String password1 = scan.nextLine();
    user.hashed = user.hash(password1);
    access.saveData();
    System.out.println("\nYour password has been updated successfully.");
    myAccount(scan, access, user);
  } // Close Change Password
  
  public static void deposit(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nThere is currently $" + user.balance + user.ifZero(user.balance) + " in your account.\nHow much would you like to deposit (or type \"back\" to go back)?");
    try
    {
      String answer = scan.nextLine();
      if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
        myAccount(scan, access, user);
      else
      {
        double amount = Double.parseDouble(answer);
        if(amount>=0)
        {
          user.balance = Math.round(100.0 * (user.balance + amount)) / 100.0; // Add to their balance after rounding to the cent
          access.saveData();
          System.out.println("\nAwesome!\nYour new balance is $" + user.balance + user.ifZero(user.balance));
          myAccount(scan, access, user);
        }
        else
        {
          System.out.println("\nThat number is too small :(Please try again.");
          deposit(scan, access, user);
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("\nPlease enter a valid amount.");
      deposit(scan, access, user);
    }
  } // Close Deposit
  
  public static void changeType(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    if(user.manager)
      System.out.println("\nWould you like to change your account to a regular customer account?");
    else
      System.out.println("\nWould you like to upgrade your account to a manager account?");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
    {
      if(user.manager)
      {
        user.manager = false; // Toggle status
        access.saveData(); // Save
        System.out.println("\nAwesome!\nYou now have a customer account.");
        customer(scan, access, user); // Go back
      }
      else
      {
        user.manager = true; // Toggle status
        access.saveData(); // Save
        System.out.println("\nAwesome!\nYou now have a manager account.");
        manager(scan, access, user); // Go back
      }
    }
    else if(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
    {
      System.out.println("\nOkay!\nYour account type has not been changed.");
      myAccount(scan, access, user);
    }
    else
    {
      System.out.println("\nSorry, I didn't get your response.\nPlease type \"yes\" or \"no\".");
      changeType(scan, access, user);
    }
  } // Close Change Account Type
  
  public static void delete(Scanner scan, Account access, Account user) throws IOException, InterruptedException // This is for deleting THEIR OWN account
  {
    System.out.println("\nAre you sure you want to delete your account?");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
    {
      System.out.println("\nYour account has been deleted successfully.");
      access.delAccount(access.accountIndex(user)); // Delete the account
      access.saveData(); // Save
      Thread.sleep(500); // Wait 
      logout(scan, access, user); // If they delete their own account we'll want to log them out, unless we want a million null pointer errors, yikes!
    }
    else if(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
      myAccount(scan, access, user);
    else
    {
      System.out.println("\nI didn't get that answer.\nPlease type either \"yes\" or \"no\"...");
      delete(scan, access, user);
    }
  } // Close Delete Account
  
  public static void changeUsername(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    System.out.println("\nWhat should their new username be? (type \"back\" to go back to your options)");
    String name = scan.nextLine();
    if (name.equalsIgnoreCase("back") || name.equalsIgnoreCase("menu"))
      manageAccount(scan, access, user, account);
    else
    {
      if(!access.accountExists(name))
      {
        account.username = name; // Change username
        access.saveData(); // Save
        System.out.println(account.name + "'s new username is now " + name + ".");
        manageAccount(scan, access, user, account);
      }
      else
      {
        System.out.println("\nSorry!\nThat username is already taken :(\nTry something else...");
        manageAccount(scan, access, user, account);
      }
    }
  } // Close Change Username (Manager)
  
  public static void changeName(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    System.out.println("\nWhat would you like " + account.name + "'s new display name to be? (or type \"back\" to go back to the list of options)");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manageAccount(scan, access, user, account);
    else
    {
      account.name = answer; // Change name
      access.saveData(); // Save
      System.out.println("\nGreat!\nWe'll call " + account.username + " \"" + account.name + "\" from now on :)");
      manageAccount(scan, access, user, account);
    }
  } // Close Change Name (Manager)
  
  public static void changePassword(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    System.out.println("\nWhat should " + account.name + "'s new password be? (or type \"back\" to go back to the list of options)");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manageAccount(scan, access, user, account);
    else
    {
      account.hashed = account.hash(answer); // Change password to encrypted new password
      access.saveData(); // Save
      System.out.println("\nGreat!\n" + user.name + "'s password has been updated :)");
      manageAccount(scan, access, user, account);
    }
  } // Close Change Password (Manager)
  
  public static void updateBalance(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    System.out.println("\n" + account.name + " currently has $" + account.balance + access.ifZero(account.balance) + " in their account\nWhat would you like to set the new amount to? (or type \"back\" to go back to your options)");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("back") || answer.equalsIgnoreCase("menu"))
      manageAccount(scan, access, user, account);
    else
    {
      try
      {
        double amount = Double.parseDouble(answer);
        if(amount >= 0)
        {
          account.balance = Math.round(100.0 * amount) / 100.0; // Change their balance after rounding to the cent
          access.saveData();
          System.out.println("\n" + account.name + "'s balance has been updated.");
          manageAccount(scan, access, user, account);
        }
        else
        {
          System.out.println("\nThat amount is too small :(");
          updateBalance(scan, access, user, account);
        }
      }
      catch (Exception e)
      {
        System.out.println("\nThat wasn't a valid amount.\nPlease try again...");
        updateBalance(scan, access, user, account);
      }
    }
  } // End Update Balance (Manager)
  
  public static void changeType(Scanner scan, Account access, Account user, Account account) throws IOException, InterruptedException
  {
    if(account.manager)
      System.out.println("\nWould you like " + account.name + "'s to be changed to a regular customer account?");
    else
      System.out.println("\nWould you like " + account.name + "'s to be upgraded to a manager account?");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
    {
      if(account.manager) // If they already are a manager
      {
        account.manager = false; // Toggle status
        access.saveData();
        System.out.println("\n" + account.name + " is no longer a manager.");
        if(account.username == user.username) // If they changed their own account type...
        {
          System.out.println("\nYou changed your own account type.\nPlease sign in again.");
          logout(scan, access, user);
        }
        else
          manageAccount(scan, access, user, account);
      }
      else // If they are a customer
      {
        account.manager = true; // Toggle status
        access.saveData();
        System.out.println("\n" + account.name + " is now a manager.");
        manageAccount(scan, access, user, account);
      }
    }
    else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
    {
      System.out.println("\nOkay!");
      manageAccount(scan, access, user, account);
    }
    else
    {
      System.out.println("\nPlease answer \"yes\" or \"no\".");
      changeType(scan, access, user, account);
    }
  } // Close Change Type (Manager)
  
  public static void logout(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    access.saveData(); // Save first because we will need to load data again, so if I missed any spots to save earlier, this will catch it
    System.out.println("\nYou are being logged out...\n");
    Thread.sleep(2500); // Lmao, artifical timer to make it look like it's doing stuff in the background. It just feels more natural then the screen suddenly changing.
    System.out.println("\n\n\n\n\n\n\n\n\n\n"); // Tons of space, just to separate the old account actions with the new one in the interactions panel
    scan.close(); // We can finally close that Scanner, cuz now we'll use a new one :)
    main(null); // We will just load main again to log out, since the main method is where we login in the first place, and we will have to create the access and user accounts again by loading the file, so it will clear them both automatically
  } // Close Logout
  
  public static void signup(Scanner scan, Account access) throws IOException, InterruptedException
  {
    // Request and store all user data
    System.out.println("\nSelect a username:");
    String username = scan.nextLine();
    
    if(access.accountExists(username)) // If the username is taken
    {
      System.out.println("\nAn account with that username already exists.\nPlease try again!");
      signup(scan, access);
    }
    else if(username.trim().replaceAll("\\s+", "") != username) // If they added a space in their username somewhere
    {
      System.out.println("\nYou cannot use spaces in your username.\nPlease try again!");
      signup(scan, access);
    }
    else
    {
      System.out.println("\nSelect a password:");
      String password = scan.nextLine();
      
      System.out.println("\nWhat would you like us to call you? (select a display name)");
      String name = scan.nextLine();
      
      double balance = 0.0;
      boolean valid = false;
      do // We only want to use doubles here, so we're gonna loop until we get a double
      {
        try
        {
          System.out.println("\nHow much money would you like to deposit to start off with? (in dollars, e.g. 19.99)");
          balance = Math.round(100.0 * scan.nextDouble()) / 100.0;
          if(balance>=0)
          {
            access.saveData();
            scan.nextLine();
            valid = true; // Great, it worked so break the loop
          }
          else
            System.out.println("\nYou can not deposit a negative amount of money.");
        }
        catch (Exception e) // Not a double...
        {
          System.out.println("\nSorry!\nThat wasn't a valid amount.\nPlease try again...");
        }
      } while(!valid);
      
      // This will tell us whether or not the account is a manager account, or a customer account. Managers will be able update the stock and account info, while customers can't.
      boolean manager = false;
      do // We will loop until we get a yes or no answer
      {
        valid = true; // Reset boolean
        System.out.println("\nIs this a manager account?");
        String answer = scan.nextLine();
        if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
          manager = true;
        else if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
          manager = false;
        else
        {
          System.out.println("\nSorry, I didn't quite get response.\nPlease type either \"yes\" or \"no\"...");
          valid = false;
        }
      } while(!valid);
      
      if(username.equals("*") || username.equals("^") || username.equals("+") || name.equals("*") || name.equals("^") || name.equals("+")) // Prevent them from using our special characters as their names, which would break our saving and loading
      {
        System.out.println("\nPlease do not use \"*\", \"^\", or \"+\" as your name or username.");
        signup(scan, access);
      }
      else if(username.length() > 32 || username.length() > 32) // If there username or password is too long
      {
        System.out.println("\nEither your username or your password was too long.\nKeep them at 32 characters or less.\nPlease...");
        signup(scan, access);
      }
      else if(username.length() > 128) // If there username or password is too long
      {
        System.out.println("\nOh come on.\nPlease pick a display name that's 128 characters or less...");
        signup(scan, access);
      }
      else
      {
        access.addAccount(balance, name, username, password, manager); // Add the account to the database
        Account user = access.useAccount(username); // Login to the new account
        sign(scan, access, user); // Load the appropriate list of controls
      }
    }
  } // Close Sign Up
  
  public static void login(Scanner scan, Account access) throws IOException, InterruptedException
  {
    // Keep looping until either correct data is entered, or a new account is created
    boolean valid = true;
    do
    {
      valid = true; // Reset boolean
      // Collect data
      System.out.println("\nUsername:");
      String username = scan.nextLine();
      System.out.println("\nPassword:");
      String password = scan.nextLine();
      
      if(access.accountExists(username, password)) // Check if account with that data exists
      {
        Account user = access.useAccount(username); // Login to the account by setting the account in use to the appropriate account
        sign(scan, access, user); // Sign in to the appropriate menu
      }
      else
      {
        System.out.println("\nThe username and password combination you entered was not found.");
        shouldLogin(scan, access);
      }
    } while (!valid);
  } // Close Login
  
  public static void shouldLogin(Scanner scan, Account access) throws IOException, InterruptedException // Asks them if they want to sign into an existing account or create a new one
  {
    boolean valid = true;
    do // Will loop until a valid answer is submitted
    {
      valid = true; // Reset the loop boolean
      
      System.out.println("\nWould you like to create a new account?");
      String answer = scan.nextLine();
      
      if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
        signup(scan, access); // signup method
      else if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
        login(scan, access); // login method
      else
      {
        System.out.println("\nSorry, I didn't quite get response.\nPlease type either \"yes\" or \"no\"...");
        valid = false;
      }
    } while (!valid);
  } // Close Should Login
  
  public static void sign(Scanner scan, Account access, Account user) throws IOException, InterruptedException
  {
    System.out.println("\nYou are now signed in :)\nWelcome " + user.name + "!");
    
    // Load the proper menu depending on the account type
    if(user.manager)
      manager(scan, access, user);
    else
      customer(scan, access, user);
  } // Close Sign
} // Close Class