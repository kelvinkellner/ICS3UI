import java.util.*;

public class Unit2AssignmentAden
{
  public static void main (String [] args)
  {
    Scanner startInput = new Scanner (System.in);
    Scanner orderInput = new Scanner (System.in);
    Scanner sticksInput = new Scanner (System.in);
    
    boolean isAnInteger = false; // I added this boolean. Remember, a boolean can only have two values: true or false. We will use this to keep track of, and check whether or not the player successfully enters an integer. The default value is false, meaning they did not enter an integer, which we will change to true, if they actually do enter an integer.
    
    int choice1 = 0; // Declare the variable first, because otherwise it will create a new variable everytime the loop below I added repeats, which would cause errors. You will need to move all your choices here I think.
    
    System.out.println ("We are about to play Nim\n");
    System.out.println ("Instructions:\n- There are 13 sticks in total\n- You can either pick up 1, 2, or 3 sticks on your turn\n- Once your turn is completed I will also have the chance to pick up 1, 2, or 3 sticks\n- The person who picks up the last stick loses\n\nGood luck!");
    System.out.println ("\nWould you like to begin the game (y/n)");
    
    String start = startInput.nextLine();
    int sticks = 13;
    
    if(start.equalsIgnoreCase ("yes") || start.equalsIgnoreCase ("y"))
    { 
      
      System.out.println ("Would you like to go First or Second?"); 
      
      String order = orderInput.nextLine();
      
      while (!order.equalsIgnoreCase("first") && !order.equalsIgnoreCase("second"))
      {
        System.out.println ("Sorry, that is an invalid statement. Please try again.");
        order = orderInput.nextLine();
      }
       
       if(order.equalsIgnoreCase ("first"))
       {
         System.out.println ("Please make your first choice, choose the number of sticks you would like to take:");
         
         
         // THIS ENTIRE SECTION BETWEEN THESE TWO HEADERS WILL NEED TO BE COPIED FOR EACH "choice" SECTION FOR THE PLAYER, SORRYYYY, THIS IS WHY LOOPS ARE ALWAYS HANDY :(( //
         
         
         
         // Okayyyy, so this is what I changed. Do you know how "do" loops work? They're similar to "while" loops, except they always run the FIRST time, and they only check if the value is true at the end to see if they should run again and again.
         do
         {
           // How try and catch works. The program will try to run what is in the "try" section, but if it runs into an error at any point, it will immediately move to the "catch" section and do what's there. If there is no error, then the catch section isn't run. So...
           
           try
           {
             choice1 = orderInput.nextInt(); // It will try to store the value the player enters. If it's not an integer, it will instantly break the code and move to the catch section...
             orderInput.nextLine(); // Remember, when collecting Int's and Double's, always throw a ".nextLine()" after the last lone ".nextInt()"
             isAnInteger = true; // If it is an integer, DO NOT repeat the loop and ask for another answer (this will only happen if the answer is an int, and the program doesn’t move to the catch)
           }
           catch (Exception e) // So this will run if the user DOES NOT enter an integer. Note: Idk what the "(Exception e)" means, it's just another one of those things you have to memorize. It's always beside the "catch" method, otherwise you can't compile.
           {
             orderInput.nextLine(); // Even if the player enters something other than an int, you still need to clear the Scanner, because the code will break before reaching this line in the "try" section
             isAnInteger = false; // If it is not an integer, PLEASE repeat the loop and ask for another answer
           }
           
           // Okayyy, I also moved this section into this do loop, because it requires less code, because otherwise you would need TWO try and catch sections. Now the loop will repeat if the answer is not an integer OR it is greater than 3 OR it is less than 1
           if (!isAnInteger)
           {
             System.out.println ("Sorry, that is not an integer. Please enter a whole number from 1-3."); // "!isAnInteger" just means "isAnInteger == false", but with less code.
           }
           else if(choice1 > 3 || choice1 < 1)
           { 
             System.out.println ("Sorry, that is an invalid number. Please try again."); //  You do not need to ask for a new Int in here, because it will loop back to the try and catch section at the top when it is done
           }
         } while (!isAnInteger || choice1 > 3 || choice1 < 1); // This will repeat if their answer is too big, too small, or not an int.
           
           
           
         // THIS ENTIRE SECTION BETWEEN THESE TWO HEADERS WILL NEED TO BE COPIED FOR EACH "choice" SECTION FOR THE PLAYER, SORRYYYY, THIS IS WHY LOOPS ARE ALWAYS HANDY :(( //
          
         int comchoice1 = 0;  
         
         if (choice1 == 1)
         {
           comchoice1 = 3;
           System.out.println ("You have chosen to take 1 sticks, there are now " + (sticks - choice1) + " sticks left");
           System.out.println ("I have chosen to take 3 sticks, now there are " + (sticks - choice1 - comchoice1) + " sticks left\n");
         }  
         else if (choice1 == 2)
         { 
           comchoice1 = 2;
           System.out.println ("You have chosen to take 2 sticks, there are now " + (sticks - choice1) + " sticks left");
           System.out.println ("I have chosen to take 2 sticks, now there are " + (sticks - choice1 - comchoice1) + " sticks left\n");
         }  
         else if (choice1 == 3)
         {  
           comchoice1 = 1;
           System.out.println ("You have chosen to take 3 sticks, there are now " + (sticks - choice1) + " sticks left");
           System.out.println ("I have chosen to take 1 sticks, now there are " + (sticks - choice1 - comchoice1) + " sticks left\n");
         } 
                         
         System.out.println ("It's your turn again, please choose the number of sticks you would like to take:");
         int choice2 = sticksInput.nextInt();
         int comchoice2 = 0;
                    
         while (choice2 > 3 || choice2 < 1)
         {
           System.out.println ("Sorry, that is an invalid number. Please try again.");
           choice2 = sticksInput.nextInt();      
         }
         
         if (choice2 == 1)
         {
           comchoice2 = 3;
           System.out.println ("You have chosen to take 1 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2) + " sticks left");
           System.out.println ("I have chosen to take 3 sticks, now there are " + (sticks - choice1 - comchoice1 - choice2 - comchoice2) + " sticks left\n");
         } 
         else if (choice2 == 2)
         {  
           comchoice2 = 2;
           System.out.println ("You have chosen to take 2 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2) + " sticks left");
           System.out.println ("I have chosen to take 2 sticks, now there are " + (sticks - choice1 - comchoice1 - choice2 - comchoice2) + " sticks left\n");
         }  
         else if (choice2 == 3)
         {  
           comchoice2 = 1;
           System.out.println ("You have chosen to take 3 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2) + " sticks left");
           System.out.println ("I have chosen to take 1 sticks, now there are " + (sticks - choice1 - comchoice1 - choice2 - comchoice2) + " sticks left\n");
         } 
        
         System.out.println ("It's your turn again, please choose the number of sticks you would like to take:");
         int choice3 = sticksInput.nextInt();
         int comchoice3 = 0;
         
         while (choice3 > 3 || choice3 < 1)
         {
           System.out.println ("Sorry, that is an invalid number. Please try again.");
           choice3 = sticksInput.nextInt();       
         }
         
         if (choice3 == 1)
         {
           comchoice3 = 3;
           System.out.println ("You have chosen to take 1 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3) + " sticks left");
           System.out.println ("I have chosen to take 3 sticks, now there is " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3 - comchoice3) + " sticks left\n");
         } 
         else if (choice3 == 2)
         { 
           comchoice3 = 2;
           System.out.println ("You have chosen to take 2 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3) + " sticks left");
           System.out.println ("I have chosen to take 2 sticks, now there is " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3 - comchoice3) + " sticks left\n");
         } 
         else if (choice3 == 3)
         {  
           comchoice3 = 1;
           System.out.println ("You have chosen to take 3 sticks, there are now " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3) + " sticks left");
           System.out.println ("I have chosen to take 1 sticks, now there is " + (sticks - choice1 - comchoice1 - choice2 - comchoice2 - choice3 - comchoice3) + " sticks left\n");
         }
       }
    }
    
    else if (start.equalsIgnoreCase ("no") || start.equalsIgnoreCase ("n")) 
    {
      System.out.println ("Okay, maybe another time then");    
    }
   
  }// end main
}// end class


