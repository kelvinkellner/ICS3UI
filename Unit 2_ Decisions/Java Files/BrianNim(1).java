//Brian Nguyen
//Nim Game
//March 18th, 2018
//Mrs. Cooper
import java.util.*;
import java.util.Scanner;
class BrianNim
{
  public static void main(String[] args)
  {
    Scanner myInput= new Scanner(System.in);
    boolean ansIsValid=true;
    do
    {
    //Explaining rules of the game
    System.out.print("Nim\n");
    System.out.print("There are 13 sticks, you can choose to pick up 3 2 or 1 stick(s). The objective of the game is to not be left with the last stick");
    //Allowing player to go first or second
    System.out.println("\nYou have the choice to either go first or second.\nType first if you want to go first.\nType second if you want to go second");
    String ans=myInput.nextLine();
    //Game Senario if the player wishes to go first. The computer always win in this version
    int firstMove = 0;
    int secondMove = 0;
    int thirdMove = 0;
    int fourthMove = 0; 
    int fifthMove = 0;
    int sixthMove = 0;
    int seventhMove = 0;
    int eigthMove = 0;
    int ninthMove = 0;
    int tenthMove = 0;
    int eleventhMove = 0;
    int twelfthMove = 0;
    if (ans.equals("first"))
    {
      int stick=13;//Creating variable for sticks
      boolean replay;//Declaring variable for replay
      //All possibilities for the first round
        firstMove = 0;
        secondMove = 0;
        thirdMove = 0;
        do{
          do{
            do{
              do{
          System.out.println("\nYou are going first\nPlease insert a number, which will be the amount you will pick up. Remember that you can only pick up 1, 2 or 3 sticks");
          firstMove=myInput.nextInt();//player's move
          if (firstMove<=3)//what computer will do for specific move
          {
            stick=stick-firstMove;
            System.out.println("You pick up " + firstMove + " stick(s), leaving " + stick + " sticks left.");
            System.out.println("\nThe computer proceeds to pick up " + (4-firstMove) + " sticks, leaving 9 sticks left.");
          }
          else
          {
            System.out.println("You didn't listen to the rules. The game will now restart");
          }
        }
        while(firstMove>3);
        stick=9;//the number of sticks will always be the same after the first round so the computer will win
        System.out.println("\nIt is now your turn. Please insert the amount of sticks you want to pick up. Remember that you can only pick up 1, 2 or 3 sticks");
        secondMove=myInput.nextInt();
        if (secondMove<=3)
          {
            stick=stick-secondMove;
            System.out.println("You pick up " + secondMove + " stick(s), leaving " + stick + " sticks left.");
            System.out.println("\nThe computer proceeds to pick up " + (4-secondMove) + " sticks, leaving 5 sticks left.");
          }
          else
          {
            System.out.println("You didn't listen to the rules. The game will now restart");
        }
      }
        while(secondMove>3);
        stick=5;
        if (thirdMove<=3)
          {
            System.out.println("\nIt is now your turn. Please insert the amount of sticks you want to pick up. Remember that you can only pick up 1, 2 or 3 sticks");
            thirdMove=myInput.nextInt();
            stick=stick-thirdMove;
            System.out.println("You pick up " + thirdMove + " stick(s), leaving " + stick + " sticks left.");
            System.out.println("\nThe computer proceeds to pick up " + (4-thirdMove) + " sticks, leaving 1 stick left.");
            System.out.println("You are left with the last stick and therefore lost.");
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
        String question=myInput.nextLine();//inputting answer
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
     //Version of game where the player goes second
    else if (ans.equals("second"))
    {
      //declaring variables for game
      int stick = 13;
      int urstick = 0;
      int itsstick = 0;
      boolean replay;
      Random randNum= new Random();
      //impossible for the game to end in four moves, so letting the game play out
      do
      {
        do
        {
          do
          {
            do
            {
      if(firstMove<=3 && secondMove <=3 && thirdMove<=3 && fourthMove<=3  && fifthMove<=3 && sixthMove<=3 && seventhMove<=3 && eigthMove<=3 && ninthMove<=3 && tenthMove<=3 && eleventhMove<=3 && twelfthMove<=3)
      {
        if(urstick!=1 && itsstick!=1)
        {
          do {
      firstMove = randNum.nextInt(3)+1;
      itsstick = stick-firstMove;
      System.out.print("The computer picks up " + firstMove + " sticks, leaving " + itsstick + " sticks left.");
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");     
      secondMove = myInput.nextInt();
      urstick = itsstick-secondMove;
      System.out.println("You've picked up " + secondMove + " sticks, leaving " + urstick + "sticks left.");
      thirdMove = randNum.nextInt(3)+1;
      itsstick = urstick-thirdMove;
      System.out.print("The computer picks up " + thirdMove + " sticks, leaving " + itsstick + " sticks left.");
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      fourthMove = myInput.nextInt();
      urstick = itsstick-fourthMove;
      System.out.println("You've picked up " + fourthMove + " sticks, leaving " + urstick + " sticks left.");
      if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
//End of game text
          myInput.nextLine();
          System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
          String question=myInput.nextLine();//inputting answer
          if (question.equals("yes"))
         {
           replay=true;
         }
         else
         {
           replay=false;
         }
         } 
         }
        } while(replay==true);
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
      if (urstick>3 && itsstick>3)
      {
      fifthMove = randNum.nextInt(2)+1;
      itsstick = urstick-fifthMove;
      System.out.print("The computer picks up " + fifthMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else
      {
      fifthMove = randNum.nextInt(3)+1;
      itsstick = urstick-fifthMove;
      System.out.print("The computer picks up " + fifthMove + " sticks, leaving " + itsstick + " sticks left.");
      }
        if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      sixthMove = myInput.nextInt();
      urstick = itsstick-sixthMove;
      System.out.println("You've picked up " + sixthMove + " sticks, leaving " + urstick + "sticks left.");
      }
      else
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      sixthMove = myInput.nextInt();
      urstick = itsstick-sixthMove;
      }
        }
         if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if(urstick==0)
      { 
        System.out.println("You can't take all the sticks, the game will now restart");
      }
      else
      {
      System.out.println("You've picked up " + sixthMove + " sticks, leaving " + urstick + "sticks left.");  
      }
        }
         if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      seventhMove = randNum.nextInt(3)+1;
      itsstick = urstick-seventhMove;
      System.out.print("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==3 &&itsstick==3)
      {
      seventhMove = randNum.nextInt(2)+1;
      itsstick = urstick-seventhMove;
      System.out.print("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==2 && itsstick==2)
      {
      seventhMove = randNum.nextInt(1)+1;
      itsstick = urstick-seventhMove;
      System.out.print("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
        }
         if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      eigthMove = myInput.nextInt();
      urstick = itsstick-eigthMove;
      System.out.println("You've picked up " + eigthMove + " sticks, leaving " + urstick + "sticks left.");
      }
      else
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      eigthMove = myInput.nextInt();
      urstick = itsstick-eigthMove;
      }
        }
         if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if(urstick==0)
      {
      System.out.println("You can't take all of the sticks. The game will now restart.");
      }
      else
      {
      System.out.println("You've picked up " + eigthMove + " sticks, leaving " + urstick + "sticks left.");
      }
        }
      if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {  
      if (urstick>3 && itsstick>3)
      {
      ninthMove = randNum.nextInt(3)+1;
      itsstick = urstick-ninthMove;
      System.out.print("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==3 &&itsstick==3)
      {
      ninthMove = randNum.nextInt(2)+1;
      itsstick = urstick-ninthMove;
      System.out.print("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==2 && itsstick==2)
      {
      ninthMove = randNum.nextInt(1)+1;
      itsstick = urstick-ninthMove;
      System.out.print("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
      }
        }
        if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      tenthMove = myInput.nextInt();
      urstick = itsstick-tenthMove;
      System.out.println("You've picked up " + tenthMove + " sticks, leaving " + urstick + "sticks left.");
      }
      else
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      tenthMove = myInput.nextInt();
      urstick = itsstick-tenthMove;
      }
      if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if(urstick==0)
      {
      System.out.println("You can't take all of the sticks. The game will now restart.");
      }
      else
      {
      System.out.println("You've picked up " + tenthMove + " sticks, leaving " + urstick + "sticks left.");
      }
        }
        if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      eleventhMove = randNum.nextInt(3)+1;
      itsstick = urstick-eleventhMove;
      System.out.print("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==3 &&itsstick==3)
      {
      eleventhMove = randNum.nextInt(2)+1;
      itsstick = urstick-eleventhMove;
      System.out.print("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
      else if(urstick==2 && itsstick==2)
      {
      eleventhMove = randNum.nextInt(1)+1;
      itsstick = urstick-eleventhMove;
      System.out.print("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
      }
        }
        if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
        }
        else if(itsstick==1)
        {
          System.out.println("You are left with the last stick and therefore lose");
        }
        else
        {
      if (urstick>3 && itsstick>3)
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      twelfthMove = myInput.nextInt();
      urstick = itsstick-twelfthMove;
      System.out.println("You've picked up " + twelfthMove + " sticks, leaving " + urstick + "sticks left.");
      }
      else
      {
      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
      twelfthMove = myInput.nextInt();
      urstick = itsstick-twelfthMove;
      }
      if(urstick==0)
      {
      System.out.println("You can't take all of the sticks. The game will now restart.");
      }
      else
      {
      System.out.println("You've picked up " + twelfthMove + " sticks, leaving " + urstick + "sticks left.");
      }
      }
      }
      }
      else
      {
        //Restarting game if players don't pick up the right amount of sticks
        System.out.println("You made an illegal move. The game will now restart.");
      }
            }
            while(firstMove>3);
          }
          while(secondMove>3);
        }
        while(thirdMove>3);
      }
      while(fourthMove>3);
    myInput.close();
    }
    else
    {
      System.out.println("You didn't insert the apporpriate answer.\nAgain....");
    }
    if(!ans.equals("first") && !ans.equals("second"))
    {
      ansIsValid=false; 
    }
    }
    while(ansIsValid==false);
  }//end main
}//end class
