// Kelvin Kellner
// Unit 3 Test 2 - While Loops
// Option 1
// Mrs. Cooper
// 19 April 2018

import java.util.*; // Import the Java utility

public class Unit3Test2WhileLoops
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    
    int classes = 0; // The # of classes Mrs. Cooper teaches that semester
    boolean isFinalStudent = false;
    
    // These two variable will be cleared and reused to calculate the average for all the classes
    int total = 0; // The total collective mark of all students (for any given class)
    int students = 0; // The number of students (for any given class)
    int current = 0; // The current students mark (for any given student)
    
    System.out.println("\nWelcome, Mrs. Cooper!\nThis is your class average calculator.\nLet's begin :)\n"); // Welcome message
    
    System.out.println("How many classes do you teach this semester?");
    classes = scan.nextInt(); // Store that number into the variable
    scan.nextLine(); // Clear scanner
    
    int [] averages = new int[classes]; // Create an empty array to store all the class averages
    
    for (int i = 0; i < classes; i++)
    {
      isFinalStudent = false; // If the loop just restarted, that means this is a new class, so we haven't reached the final student (or any for that matter), so we must reset the boolean
      total = 0; // Reset the total class average, since this is a new class
      students = 0; // Reset the number of students in the class, since this is a new class
      
      System.out.println("\nFor class number " + (i + 1) + ", please input each student's mark one at a time as an integer, pressing enter after each one.\nType 0 and then press enter after the final student's mark has been submitted.");
      // i + 1 is used to display the class number in a "human-friendly" manner :)
      
      while (!isFinalStudent) // Repeat until there are no more students marks remaining for this class
      {
        current = scan.nextInt(); // Store the student's mark so we can add it to the average
        scan.nextLine(); // Clear scanner
        
        if (current != 0) // If 0 is entered, that means that there are no more students in this class
        {
          total += current; // Add the student's mark to the class total
          students ++; // Tell the computer that, yes, there is another student, add that to the # of students for the class
        }
        else
        {
          isFinalStudent = true; // Tell the program that there are no more students in this class, so it can stop trying to collect marks, and it can now calculate the average (after the loop)
        }
      }
      
      averages[i] = (total/students); // Store the average for this class into the array
      System.out.println("Class " + (i + 1) + " had an average of " + averages[i] + "%."); // Print out the class average to the user
    }
    
    System.out.println("\nGreat! Those were all the classes :)");
    
    int highest = 0; // This will store the array index # for the class with the highest average
    
    for (int i = 0; i < classes; i++) // This loop will filter through all the classes to find which has the highest average
    {
      if (averages[i] > averages[highest]) // If the average of the current class is higher than the highest so far...
      {
        highest = i; // Then set the new highest class average to the current class
      }
    }
    
    System.out.println("The class with the highest average was class " + (highest + 1) + ", with an average of " + averages[highest] + "%."); // Tell the user which class had the highest average
    
    System.out.println("\nThanks for calculating with me :)\nBye!\n");
    
    scan.close(); // Close scanner
  } // end main
} // end class