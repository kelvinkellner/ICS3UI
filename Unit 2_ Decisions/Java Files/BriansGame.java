//Brian Nguyen
//Nim Game
//March 18th, 2018
//Mrs. Cooper
import java.util.*;
import java.util.Scanner;
public class BriansGame
{
  public static void main(String[] args)
  {
    Scanner myInput= new Scanner(System.in);
    //Explaining rules of the game
    System.out.print("Nim\n");
    System.out.print("There are 13 sticks, you can choose to pick up 3 2 or 1 stick(s). The objective of the game is to not be left with the last stick");
    //Allowing player to go first or second
    System.out.println("\nYou have the choice to either go first or second.\nType first if you want to go first.\nType second if you want to go second");
    String ans=myInput.nextLine();
    
    if (ans.equals("first"))
    {
      int stick=13;//Creating variable for sticks
      boolean replay;//Declaring variable for replay
      //All possibilities for the first round
      do{
        int firstMove;
        do{
          System.out.println("\nPlease insert a number, which will be the amount you will pick up. Remember that you can only pick up 1, 2 or 3 sticks");
          firstMove=myInput.nextInt();
          if (firstMove==3)
          {
            stick = stick - 3;
            System.out.println("You took 3 sticks and there are " + stick + " sticks left."); //Showing the move that the player made
            System.out.println("The computer proceeds to take 1, leaving nine sticks left"); //Showing the move that the computer made
          }
          else if (firstMove==2)
          {
            stick = stick - 2;
            System.out.println("You took 2 sticks and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 2, leaving nine sticks left"); 
          }
          else if (firstMove==1)
          {
            stick = stick - 1;
            System.out.println("You took 1 stick and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 3, leaving nine sticks left"); 
          }
          else
          {
            System.out.println("You didn't listen to the rules. The game will now restart");
          }
        }
        while(firstMove>3);
        stick= 9;
        //All possibilities for the second round    
        int secondMove;
        do{
          System.out.println("\nThere are " + stick + " sticks left, please insert how many sticks you are going to pick up now \n(You still only have the choice of picking up 3 or 2 or 1 stick(s))");
          secondMove=myInput.nextInt();
          if (secondMove==3)
          {
            stick = stick - 3;
            System.out.println("You took 3 sticks and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 1, leaving five sticks left"); 
          }
          else if (secondMove==2)
          {
            stick = stick - 2;
            System.out.println("You took 2 sticks and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 2, leaving five sticks left"); 
          }
          else if (secondMove==1)
          {
            stick = stick - 1;
            System.out.println("You took 1 stick and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 3, leaving five sticks left"); 
          }
          else
          {
            System.out.println("You didn't listen to the rules. The game will now restart");
          }
        }
        while(secondMove>3);
        //All possibilities for the third round
        int thirdMove;
        do{  
          System.out.println("\nThere are " + stick + " sticks left, please insert how many sticks you are going to pick up now \n(You still only have the choice of picking up 3 or 2 or 1 stick(s))");
          thirdMove=myInput.nextInt();
          stick=1;
          if (thirdMove==3)
          {
            stick=stick - 3;
            System.out.println("You took 3 sticks and there are " + stick + " sticks left."); 
            System.out.print("The computer proceeds to take 1, leaving " + stick + " stick left\n");
            System.out.println("You are left with the last stick and therefore lost. Try again next time!");
          }
          else if (thirdMove==2)
          {
            stick=stick - 2;
            System.out.println("You took 2 sticks and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 2, leaving " + stick + " stick left\n");
            System.out.println("You are left with the last stick and therefore lost. Try again next time!");
          }
          else if (thirdMove==1)
          {
            stick=stick - 1;
            System.out.println("You took 1 stick and there are " + stick + " sticks left."); 
            System.out.println("The computer proceeds to take 3, leaving " + stick + " stick left\n");
            System.out.println("You are left with the last stick and therefore lost. Try again next time!");
          }
          else
          {
            System.out.println("You didn't listen to the rules. The game will now restart");
          }
        }
        while(thirdMove>3);
        //End of game text
        myInput.nextLine();
        System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
        String question=myInput.nextLine();
        if (question.equals("yes"))
        {
          replay=true;
        }
        else
        {
          replay=false;
        }
      }
      while(replay==true);
    }
    else
     {
       Scanner myInput2= new Scanner(System.in);
       int stick= 13;
       boolean replay;
         Random randNum= new Random();
      do{
      int firstMove = randNum.nextInt(3)+1;
      stick = stick-firstMove;
      System.out.print("The computer picks up " + firstMove + "sticks, leaving" + stick + " sticks left.");
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      myInput2.nextLine();
      int secondMove = myInput2.nextInt();
      stick = stick-secondMove;
      System.out.println("You've picked up" + secondMove + " sticks, leaving" + stick + "sticks left.");
      int thirdMove = randNum.nextInt(3)+1;
      stick = stick-thirdMove;
      System.out.print("The computer picks up " + thirdMove + "sticks, leaving" + stick + " sticks left.");
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      myInput2.nextLine();
      int fourthMove = myInput2.nextInt();
      stick = stick-fourthMove;
      System.out.println("You've picked up" + fourthMove + " sticks, leaving" + stick + "sticks left.");
 
        myInput2.nextLine();
        System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
        String question=myInput2.nextLine();
        if (question.equals("yes"))
        {
          replay=true;
        }
        else
        {
          replay=false;
        }
      }
      while(replay==true);
     }
     
    myInput.close();  
   
    
  }//end main
  }//end class
    
    



