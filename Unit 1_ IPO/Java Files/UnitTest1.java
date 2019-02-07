// Unit Test 1
// Kelvin Kellner
// This program will take the user's name, and count up from a number using a given interval
// Mrs. Cooper
// 22 February 2018

import java.util.*; // Import Scanner resource

public class UnitTest1
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in); // Create a new scanner named "scan" that will receive user input from System.in
    
    // Welcome greeting
    System.out.println("Welcome! Please type your name then press enter :)"); // Welcome the user and ask for their name
    String name = scan.nextLine(); // Create a box for the user to type into, and store their entry to a variable called "name"
    System.out.println("Nice to meet you, " + name + "!\n"); // Greet the user again with their name
    
    // Getting user info for later math operations
    System.out.println("Okay, this program is going to count up using intervals. Please enter a number to start counting up from (integers only)."); // Ask the user for a number
    int startNum = scan.nextInt(); // Store the starting number that the user gives us under a variable called "startNum"
    System.out.println("Nice! Now enter a number you would like to count up from, and we'll give you the next five numbers in the series (integers only)."); // Ask for an interval number
    int countNum = scan.nextInt(); // Store the number our program will count up by
    
    // Output the series to the user
    System.out.println("Awesome!\n\nNow watch our program do its magic...\nDone!\n"); // A silly little transition to show the user
    System.out.println("Starting at " + startNum + " and counting up by " + countNum + ", the next five numbers in series will be..."); // Introduce the results our program will display
    // Print the next five numbers - this could be done effectively with a method or a for loop, but shhhh, I don't know that yet ;)...
    System.out.println(startNum + countNum);
    System.out.println(startNum + (countNum * 2));
    System.out.println(startNum + (countNum * 3));
    System.out.println(startNum + (countNum * 4));
    System.out.println(startNum + (countNum * 5));
    
    // Say goodbye to the user, and thank them for using our program
    System.out.println("\nAyy, thanks for coming along " + name + "! See you next time ;)");
    
    scan.close(); // Always close resources if you can :)
  } // end main
} // end class