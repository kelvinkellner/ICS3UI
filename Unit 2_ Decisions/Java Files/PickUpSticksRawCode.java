// Kelvin Kellner
// Pick Up Sticks
// This is a game where the user and a computer will take turns picking up sticks. Whoever picks up the final stick loses.
// Ms. Cooper
// 1 March 2018

import java.util.*;

public class PickUpSticksRawCode
{
  
  public static void main(String[] args)
  {
    System.out.println("\nWelcome to Pick Up Sticks!");
    System.out.println("To play this game, you and a computer will take turns picking up sticks.");
    System.out.println("There are typically 13 sticks to begin with. On each turn, you can pick up 1-3 sticks.");
    System.out.println("Whoever picks up the final stick loses the game.\n");
    startGame();
  }
  
  public static void startGame()
  {
    Scanner scan = new Scanner(System.in);
    boolean validAnswer = false;
    
    // WHO STARTS? //
    boolean userFirst = false; 
    
    do {
      System.out.println("Would you like to take the first turn? (y/n)");
      String answer = scan.nextLine();
      
      if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
      {
        validAnswer = true;
        userFirst = true;
      }
      else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
      {
        validAnswer = true;
        userFirst = false;
      }
      else
        System.out.println("Sorry, I didn't get that :(\nPlease make sure you entered a valid answer (y/n)\n");
    } while (validAnswer == false);
    
    // HOW MANY STICKS ARE WE USING? //
    int sticks = 13;
    validAnswer = false;
        
    do {
      System.out.println("\nHow many sticks would you like to play with? (default: 13)");
      
      try
      {
        sticks = scan.nextInt();
        scan.nextLine();
        validAnswer = true;
      }
      catch (Exception e)
      {
        scan.nextLine();
        validAnswer = false;
      }
      
      if (!validAnswer)
        System.out.println("I don't think that was an integer :(\n");
      else if (sticks > 100)
        System.out.println("That's a lot of sticks, I think you'll be playing for a while...\nWhy don't you try a smaller number :)\n");
      else if (sticks < 5)
        System.out.println("Oh, come on. You're gonna need more sticks than that...\n");
      else
        System.out.println("Alright! " + sticks + " sticks it is then!\nLet's get started :)\n");
    } while (validAnswer == false || sticks > 100 || sticks < 5);
    
    // RUNNING THE GAME //
    boolean gameRunning = true;
    boolean playerWon = false;
    
    if (!userFirst)
      sticks = computersTurn(sticks);
    
    do
    {
      sticks = playersTurn(sticks);
      
      if (sticks == 0)
      {
        gameRunning = false;
        playerWon = false;
        System.out.println("There are no more sticks!\n");
      }
      else if (sticks == 1)
      {
        gameRunning = false;
        playerWon = true;
        computersTurn(sticks);
      }
      else
        sticks = computersTurn(sticks);
    } while (gameRunning);
    
    gameOver(playerWon);
    scan.close();
  }
  
  // THE COMPUTER'S TURN //
  public static int computersTurn(int sticks)
  {
    int sticksTaken;
    int remainder = sticks % 4;
    
    if (sticks == 1)
      sticksTaken = 1;
    else if (remainder != 1)
    {
      sticksTaken = remainder - 1;
      if (sticksTaken == -1)
        sticksTaken = 3;
    }
    else
    {
      Random random = new Random();
      sticksTaken = random.nextInt(3) + 1;
    }
        
    sticks -= sticksTaken;
    System.out.println("The computer picked up " + sticksTaken + " " + isPlural(sticksTaken) + ".");
    drawSticks(sticks);
    System.out.println("There are now " + sticks + " " + isPlural(sticks) + " remaining.\n");
    return sticks;
  }
  
  // THE PLAYER'S TURN //
  public static int playersTurn(int sticks)
  {
    Scanner scan = new Scanner(System.in);
    int sticksTaken = 0;
    
    System.out.println("There are currently " + sticks + " " + isPlural(sticks) + " remaining. How many sticks would you like to pick up? (1-3)");
    
    boolean isError = false;
    
    do
    {
      try
      {
        sticksTaken = scan.nextInt();
        scan.nextLine();
        isError = false;
      }
      catch (Exception e)
      {
        scan.nextLine();
        isError = true;
      }
      
      if (isError)
        System.out.println("\nSorry, that's not an integer. Try entering a number from 1-3 :)");
      else if (sticksTaken > 3 || sticksTaken <= 0)
        System.out.println("\nSorry, you can only take 1-3 sticks! Please try again :)");
      else if (sticksTaken > sticks)
        System.out.println("\nThere aren't that many sticks left over! Please try a different number :)");
    } while (sticksTaken > 3 || sticksTaken <= 0 || sticksTaken > sticks || isError);
     
    sticks -= sticksTaken;
    System.out.println("You picked up " + sticksTaken + " " + isPlural(sticksTaken) + ".\n");
    
    scan.close();
    return sticks;
  }
  
  // ENDING THE GAME //
  public static void gameOver(boolean playerWon)
  {
    System.out.println("Game Over!");
    
    if (playerWon)
      System.out.println("CONGRATULATIONS! You were smart enough to beat the computer :)");
    else
      System.out.println("SORRY! The computer beat you :(");
    
    Scanner scan = new Scanner(System.in);
    boolean validAnswer = false;
    
    do {
      System.out.println("\nWould you like to play another round? (y/n)");
      String answer = scan.nextLine();
      
      if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
      {
        validAnswer = true;
        System.out.println("\nAwesome!\nLet's play another :)\n\n\n\n\n\n\n\n");
        startGame();
      }
      else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
      {
        validAnswer = true;
        System.out.println("\nOkay, well thanks for playing!\nGoodbye :)\n");
      }
      else
        System.out.println("Sorry, I didn't get that :(\nPlease make sure you entered a valid answer (y/n)\n");
    } while (validAnswer == false);
    scan.close();
  }
  
  // DRAWING GRAPHICS FOR THE # OF STICKS //
  public static void drawSticks(int sticks)
  {
    String line = "";
    for (int i = sticks; i > 0; i--)
      line += " |";
    System.out.println(line);
  }
  
  // IS "STICK" PLURAL? //
  public static String isPlural(int input)
  {
    if (input == 1)
      return "stick";
    else
      return "sticks";
  }
}