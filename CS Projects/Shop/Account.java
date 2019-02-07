// Kelvin Kellner
// Online Shopping

import java.io.*;

public class Account
{
  String file = "storedata.txt"; // This variable is the name of the file which all our data will save/load to/from
  
  double balance; // The amount of money in the account
  String name; // The display name for this account (for welcome messages, etc.
  String username; // The username for loggin in
  String hashed; // The password for logging in, however already encrypted. The program does not store unencrypted passwords (nor should real websites or applications)
  
  boolean manager; // If this is a manager account, they will get special powers that customer accounts do not have
  
  Cart cart; // This will be used to load a shopping cart from the database
  
  Account[] accs; // This will be used in the access account to store the info about all other accounts
  
  double tax; // This number is a manager setting that will change the shop's tax rate
  
  Account() { this.cart = new Cart(); } // This is used to initialize the access account, where no user data is needed (since it's not an actual account), however the access acount's cart will contain the shop catalog
  
  Account(double balance, String name, String username, String pass, boolean manager) // This constructor is for initializing user data when an account is created
  {
    this.balance = balance;
    this.name = name;
    this.username = username;
    this.hashed = pass; // The password is encrypted before it is even saved (I use hash and encrypt interchangeably, but I'm actually not sure if they're the same thing, lmao
    this.manager = manager;
    this.cart = new Cart();
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void loadData() throws IOException // This constructor will attempt to search for a save file and load the info from it. If one can not be found, a blank one will be created for later.
  {
    this.accs = new Account[0]; // Create an empty array of accounts, which will be replaced if accounts are actually found and can be loaded
    
    try
    {
      FileReader file = new FileReader(this.file); // Setup a FileReader to look for the file name we set at the top of the class
      BufferedReader read = new BufferedReader(file); // Setup a BufferedReader for scanning the file
      
      int size = fileSize(read, "+");
      String next = "";
      
      // Load all the shop data
      
      next = read.readLine();
      this.taxRate(next);
      
      for(int i = 0; i <size;i++)
      {
        next = read.readLine();
        if(!next.equals("+")) // Stop character for shop catalog
        {
          String[] split = next.split("@");
          this.cart.addItem(split[0],split[1],split[2],split[3]);
        }
      }
      
      // Everything below this will load the information for each ndividual user, as well as their shopping carts
      
      int count = 0; // This will indicate the line number
      size = fileSize(read, "*"); // This will return the number of lines in the file until our stop character
      int num = countChar(read, "^", size); // This will count how many times our "new account" character is used
      
      this.accs = new Account[num]; // Create an array for the accounts
      
      for(int i = 0; i < size; i++) // Until the stop character
      {
        String line = read.readLine();
        if(line.equals("*")) // If we've reached the end...
          i=size; // Use a condition that will break the loop
        else if (line.equals("^")) // If a "new account" character has been reached...
        {
          // Grab all user data...
          boolean manager = Boolean.parseBoolean(read.readLine());
          double balance = Double.parseDouble(read.readLine());
          String name = read.readLine();
          String username = read.readLine();
          String hashed = read.readLine();
          i+=4; // (This keeps the loop from getting confused and looping extra times)
          
          this.accs[count] = new Account(balance, name, username, hashed, manager); // And conver that data into an account in the array
          count++;
        }
        else
        {
          String[] split = line.split("@"); // Split the data from the text file
          this.accs[count-1].cart.addItem(split[0],split[1],split[2],split[3]); // Create a new item in the cart using that data
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      // If no file is found, create an empty one that can be used later on, so at least it exists and won't throw an error
      FileWriter file = new FileWriter(this.file);
      PrintWriter print = new PrintWriter(file);
      print.println("13.0"); // Add the default tax rate
      print.println("Apples@Apple@2.50"); // Default Items
      print.println("Oranges@Orange@3.00"); // Default Items
      print.println("+"); // Add the stop characters
      print.println("*");
      print.close(); // Close to save
      this.tax = 13.0;
      this.cart.addItem("Apples", "Apple", "2.50", "0");
      this.cart.addItem("Oranges", "Orange", "3.00", "0");
      this.saveData(); // Now that a default file has been writen, load the data from it
    }
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void saveData() throws IOException
  {
    // Setup for printing to a file
    FileWriter file = new FileWriter(this.file);
    PrintWriter print = new PrintWriter(file);
    
    // Print store data
    print.println(this.tax); // The tax rate is at the top
    for(int i = 0; i < this.cart.items.length; i++)
    {
      print.println(this.cart.items[i].name + "@" + this.cart.items[i].nameSingular + "@" + this.cart.items[i].price + "@" + this.cart.items[i].quantity); // Then each item is printed
    }
    print.println("+");
    
    // Print individual user data
    for(int i = 0; i < this.accs.length; i++)
    {
      print.println("^"); // New account symbol for loading data
      // Print account data
      print.println(this.accs[i].manager);
      print.println(this.accs[i].balance);
      print.println(this.accs[i].name);
      print.println(this.accs[i].username);
      print.println(this.accs[i].hashed);
      // Print cart data
      for(int k = 0; k < this.accs[i].cart.items.length; k++)
      {
        print.println(this.accs[i].cart.items[k].name + "@" + this.accs[i].cart.items[k].nameSingular + "@" + this.accs[i].cart.items[k].price + "@" + this.accs[i].cart.items[k].quantity);
      }
    }
    print.println("*"); // Stop document symbol
    print.close(); // Close to save
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void addAccount(Account account) throws IOException
  {
    Account[] accs = new Account[this.accs.length + 1]; // Create a larger empty array with room for the new account
    
    for(int i=0;i<this.accs.length;i++) // Clone the old array
    {
      accs[i]=this.accs[i];
    }
    
    accs[this.accs.length] = account; // Add the new account to the array
    
    this.accs = accs; // Replace the old array with the new one
    saveData(); // Save to file
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void addAccount(double balance, String name, String username, String password, boolean manager) throws IOException
  {
    Account acc = new Account(balance, name, username, this.hash(password), manager);
    acc.newCart();
    addAccount(acc);
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void delAccount(int index)
  {
    for(int i = index; i < this.accs.length-1; i++) // For every account after the one we're deleting...
      this.accs[i] = this.accs[i+1]; // Push it forward a spot (overwriting the one we want to delete in the process)
    
    Account[] newAccs = new Account[this.accs.length-1]; // Create a new smaller array to replace the old one since arrays need a preset size
    
    for(int i = 0; i < newAccs.length; i++)
      newAccs[i] = this.accs[i]; // Clone the old array into the new one (the last element in the old array is a duplicate, so this loop will ignore that one)
    
    this.accs = newAccs; // Replace the old array with the new one
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  Account useAccount(String username)
  {
    for(int i = 0; i < this.accs.length; i++)
    {
      if(this.accs[i].username.equals(username))
        return this.accs[i];
    }
    return new Account();
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  boolean accountExists(String username, String password) // This will simply check if an account with the matching data exists
  {
    for(int i = 0; i < this.accs.length; i++) // Scan all accounts...
    {
      if(this.accs[i].username.equals(username) && this.accs[i].hashed.equals(hash(password))) // If the account is found...
        return true; // Say it was found, otherwise...
    }
    return false; // Say it wasn't...
    // This is kept after the for loop, because the "return" function will break the loop, so if it finds the account before this, it will always return true. It can't return both results.
  }
  
  boolean accountExists(String username) // This will simply check if an account with the matching data exists (yay method overloading!!!)
  {
    for(int i = 0; i < this.accs.length; i++) // Scan all accounts...
    {
      if(this.accs[i].username.equals(username)) // If the account is found...
        return true; // Say it was found, otherwise...
    }
    return false; // Say it wasn't...
    // This is kept after the for loop, because the "return" function will break the loop, so if it finds the account before this, it will always return true. It can't return both results.
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  int accountIndex(Account account) // This will return an account's index in the accs array
  {
    for(int i = 0; i < this.accs.length; i++) // Scan all accounts...
    {
      if(this.accs[i].username.equals(account.username) && this.accs[i].hashed.equals(account.hashed)) // If the account is found...
        return i; // Return it's index
    }
    return 0; // This should never return, suggesting accountExists is used correctly
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  boolean isManager(String username)
  {
    for(int i = 0; i < this.accs.length; i++) // Scan all accounts...
    {
      if(this.accs[i].username.equals(username) && this.accs[i].manager) // If the account is a manager...
        return true; // Say it is, otherwise...
    }
    return false; // Say it isn't...
  }
  
  // THIS IS AN ACCESS ACCOUNT CONSTRUCTOR
  void taxRate(String value) // Sets the shop's tax rate
  {
    this.tax = Double.parseDouble(value);
  }
  
  void newCart() // Create a new, empty cart and save it to the cart variable for future referencing
  {
    this.cart = new Cart();
  }
  
  String hash(String pass) // This constructor will use basic encryption for storing passwords. This is a simple measure to prevent passwords from being visible in the text file, which all reliable real websites/appications will do to ward off hackers
  {
    String[] acceptable = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "*", "^", "@", "+", "!", "?", "#", "$", "%", "&", "(", ")", "-", "_", "+", "=", "\\", "|", "`", "~", "[", "]", "{", "}", ";", ":", "'", "\"", ",", ".", "/", "<", ">"}; // This is a list of acceptable characters that can be used in the password with encryption, other numbers will not be encrypted
    int[] primes = prime(acceptable.length); // This will generate an array of prime numbers in accordance with each character, which our algorithm will use to generate the hash key
    
    String toHash = pass + username; // We add their username onto the end of the password and encrypt them together, so that even two accounts with the same password will have different hashes. I think the cool kids call this "salting" a hash ;)
    String[] split = toHash.split(""); // Split every digit of the password into an array, since each digit will be altered manually
    
    int total = 0; // The hashed key will be stored as an integer total, this will store this key
    String hashed = "";
    
    for(int i = 0; i < split.length; i++) // For every letter to be hashed...
    {
      boolean charExists = false; // This will keep track of whether or not the current character is one of the 'hashable' ones. If not, it can still be handled :)
      
      for(int k = 0; k < acceptable.length; k++)
      {
        if(split[i].equals(acceptable[k]))
        {
          charExists = true;
          total += Math.pow(primes[k],2);
          break;
        }
      }
      if(!charExists) // If it isn't in the list...
      {
        hashed+=total+split[i]; // Store our hash so for and add the special character onto the end as is
        total=0; // Reset for the rest of the hash so it can continue to be used from this point onward
      }
    }
    hashed+=total; // Export the total (this will sometimes add a 0 onto the end, which is fine if it does)
    
    // System.out.println(hashed); // If you want to see what the hash key looks like, uncomment this line! (it also prints to the save file each time)
    
    return hashed; // Return the hashed key
  }
  
  int[] prime(int loops) // This constructor will generate then return a list of any number of prime numbers
  {
    int[] primes = new int[loops]; // Create an empty array of the correct size to store the prime numbers
    
    int count = 0; // Create a counter to keep track of how many prime numbers we've found so far
    int num = 37; // Create another counter to keep track of which number we are currently checking to see if it is prime
    boolean prime = true; // This boolean will tell us whether or not a number is prime, and if it should be added to the list
    
    while(count < loops) // Loop until we have the correct amount of prime numbers
    {
      num+=3; // Check a new number (7 is only used rather than 1 to make the hash look more random than it really is, this has no significance)
      prime = true; // Reset boolean
      for(int i = 2; i < num; i++) // Check all previous numbers to see if they are factors
      {
        if(num%i==0) // If the number was previously thought to be prime
          prime = false; // Number is not prime :(
      }
      
      if(prime) // Self explanitory...
      {
        primes[count] = num; // Add it to the array
        count++; // Increase our prime number count by 1
      }
    }
    return primes; // Return the list
  }
  
  int fileSize(BufferedReader read, String stopper) throws IOException // Stopper is the key phrase/character that signifies the end of our scan
  {
    read.mark(100000); // This will set a marker in our current position in the file that we can easily reset to after reading the file (the number represents how many lines we can scan before the "mark" gives up on saving it's position and is no longer able to be used)
    
    int count = 0;
    boolean stop = false; // Will tell the program when to stop
    
    do
    {
      String line = read.readLine();
      if(line.equals(stopper))
        stop = true; // If we've reached the end, stop counting
      else
        count++; // A new line has been read, increase the count
    } while (!stop);
    
    read.reset(); // Reset the BufferedReader's position so the method that called this one will be in the same position in the file as before
    return count;
  }
  
  int countChar(BufferedReader read, String character, int size) throws IOException // Will count how many times a character will be used
  {
    read.mark(100000); // This will set a marker in our current position in the file that we can easily reset to after reading the file (the number represents how many lines we can scan before the "mark" gives up on saving it's position and is no longer able to be used)
    int count = 0; // The counter for how many times we've found the character
    
    for(int i = 0; i < size; i++) // This for loop just counts each time the character is found as its own line (which is all we'll need)
    {
      String line = read.readLine();
      if(line.equals(character))
        count++; //  Add 1
    }
    
    read.reset(); // Reset the BufferedReader's position so the method that called this one will be in the same position in the file as before
    return count;
  }
  
  String ifZero(double price) // What this constructor does, is if a number does not have 2 decimal places in the 0's it will add one. For example: $2.50 will normally be displayed as 2.5, but this adds the 0 on the end, making it 2.50
  {
    if ((price*10) % 1.0 == 0.0) // If it needs a 0...
      return "0"; // Add one...
    else // Otherwise...
      return ""; // Add nothing
  }
  
  String tabber(String item) // This constructor calculates how many tabs to add on carts and shop lists depending on how long the item's name is
  {
    if(item.length()<=10)
      return "\t\t\t";
    else if(item.length()<=20)
      return "\t\t";
    else
      return "\t";
  }
}