import java.util.*;
//import java.io.*;

public class CardGames
{
  public static void main(String[] args)
  {
    mainMenu();
  }
  
  public static void mainMenu()
  {
    Scanner scan = new Scanner(System.in);
    
    int game;
    
    print("");
    print("Card Games.\n");
    print("1 - Go fish");
    print("");
    
    game = scan.nextInt();
    scan.nextLine();
    
    if (game == 1)
      playGoFish();
    
    scan.close();
  } // end main menu method
  
  
  public static void playGoFish()
  {
    int[] playerHand = newHand(7, false);
  }
  
  public static int[] newHand(int handSize, boolean withSuits)
  {
    Hand hand = new Hand();
    int [] newHand = hand.randomHand(handSize, withSuits);
    return newHand;
  }
  
  
  // UTILITY METHODS //
  
  public static void clearConsole()
  {
    // Inserts 100 blank lines to give the illusion of clearing the interactions panel
    int repeats = 100;
    
    String line = "";
    for (int i = 0; i < repeats; i++)
    {
      line += "\n";
    }
    
    System.out.println(line);
  } // end clearConsole method
  
  public static void wait(int ms)
  {
    // Waits a number of milliseconds
    try
    {
      Thread.sleep(ms);
    }
    catch (InterruptedException e)
    {
      Thread.currentThread().interrupt();
    }
  } // end wait method
  
  public static void print(String text)
  {
    // This method just saves a bit of time when printing lines, and adds spaces to make the format prettier
    String output = "     ";
    output += text;
    System.out.println(output);
  } // end print method
  
} // end main Class
  
  
  // CLASS OBJECTS //
  