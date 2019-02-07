//Brian Nguyen
//Nim Game
//March 18th, 2018
//Mrs. Cooper
import java.util.*;
public class BrianNim
{
  public static void main(String[] args)
  {
    Scanner myInput= new Scanner(System.in);
    //Explaining rules of the game
    do
    {
      //declaring variables for game
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
      int stick = 13;
      int urstick = 0;
      int itsstick = 0;
      boolean replay = false;
      Random randNum= new Random();      
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    //impossible for the game to end in four moves, so letting the game play out
                    
                    //beginning of first turn
                    firstMove = randNum.nextInt(3)+1;
                    itsstick = stick-firstMove;
                    System.out.print("The computer picks up " + firstMove + " sticks, leaving " + itsstick + " sticks left.");
                    //end of first turn
                    
                    //beginning of second turn
                    System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                    secondMove = myInput.nextInt();
                    if(secondMove==3 | secondMove==2 | secondMove==1) 
                    {
                      urstick = itsstick-secondMove;
                      System.out.println("You've picked up " + secondMove + " sticks, leaving " + urstick + " sticks left.");
                    }
                    else //punishing players for picking up more than 3 sticks
                    {
                      System.out.println("You didn't listen to the rules. The program will now close");
                      System.exit(1);
                    } 
                    //end of second turn
                    
                    //beginning of third turn
                    thirdMove = randNum.nextInt(3)+1;
                    itsstick = urstick-thirdMove;
                    System.out.print("\nThe computer picks up " + thirdMove + " sticks, leaving " + itsstick + " sticks left.");
                    //end of third turn
                    
                    //beginning of fourth turn
                    System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                    fourthMove = myInput.nextInt();
                    if(fourthMove==3 | fourthMove==2 | fourthMove==1)
                    {
                      urstick = itsstick-fourthMove;
                      System.out.println("You've picked up " + fourthMove + " sticks, leaving " + urstick + " sticks left.");
                    }
                    else //punishing player for picking up more than 3 sticks
                    {
                      System.out.println("You didn't listen to the rules. The program will now close.");
                      System.exit(1);
                    }
                    //end of fourth turn
                    
                    //beginning of fifth turn
                    if(urstick!=1)
                    {
                      //preventing computer from picking up all of the sticks
                      if (urstick==3) 
                      {
                        fifthMove = randNum.nextInt(2)+1;
                        itsstick = urstick-fifthMove;
                        System.out.println("The computer picks up " + fifthMove + " sticks, leaving " + itsstick + " sticks left.");
                      }
                      else if (urstick==2)
                      {
                        fifthMove = randNum.nextInt(1)+1;
                        itsstick = urstick-fifthMove;
                        System.out.println("The computer picks up " + fifthMove + " sticks, leaving " + itsstick + " sticks left.");
                      }
                      else 
                      {
                        fifthMove = randNum.nextInt(3)+1;
                        itsstick = urstick-fifthMove;
                        System.out.println("The computer picks up " + fifthMove + " sticks, leaving " + itsstick + " sticks left.");
                      }
                      replay = false;
//declaring this as false again because game loops back to the beginning after this turn, and I assume that the reason for this is because the replay variable somehow is changed from false to true, therefore meeting the condition in the while statement
                    }
                    //end of fifth turn
                    //beginning of potential game ending on fourth turn
                    else if(urstick==1)
                    {
                      System.out.println("The computer is left with the last stick and therefore you win.");
                      myInput.nextLine();
                      //beginning of replay question
                      System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
                      String question=myInput.next();//inputting answer
                      if (question.equals("yes"))
                      {
                        replay=true;
                      }
                      else if (question.equals("no"))
                      {
                        System.exit(1);//ends program
                      }
                      else
                      {
                        System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
                        System.exit(1);
                      }
                    }
                  }
                  while(replay==true);
                  //end of potential game ending for fourth turn
                  
                  //beginning of sixth turn  
                  if(itsstick!=1)
                  {
                    if (itsstick>3)
                    {
                      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                      sixthMove = myInput.nextInt();
                      //beginning of restrictions for making the wrong move
                      if (sixthMove<=3)
                      {
                        urstick = itsstick-sixthMove;
                        System.out.println("You've picked up " + sixthMove + " sticks, leaving " + urstick + " sticks left.");
                      }
                      else if (sixthMove>3)
                      {
                        System.out.println("You didn't listen to the rules. The program will now close");
                        System.exit(1);
                      }
                    }
                    else if (itsstick==3)
                    {
                      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                      sixthMove = myInput.nextInt();
                      if (sixthMove<=2)
                      {
                        urstick = itsstick-sixthMove;
                        System.out.println("You've picked up " + sixthMove + " sticks, leaving " + urstick + " sticks left.");
                      }
                      else
                      {
                        System.out.println("You didn't listen to the rules. The program will now close.");  
                        System.exit(1);
                      }
                    }
                    else if (itsstick==2)
                    {
                      System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                      sixthMove = myInput.nextInt();
                      if (sixthMove==1)
                      {
                        System.out.println("You've picked up " + sixthMove + " sticks, leaving " + urstick + " sticks left."); 
                      }
                      else
                      {
                        System.out.println("You didn't insert an appropriate answer. The program will now close.");
                        System.exit(1);
                      }
                    }
                  }
                  //end of sixth turn
                  //beginning of potential game ending for fifth turn
                  else if (itsstick==1)
                  {
                    System.out.println("You are left with the last stick and therefore lost.");
                    //beginning of replay question
                    System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
                    String question=myInput.next();//inputting answer
                    if (question.equals("yes"))
                    {
                      replay=true;
                    }
                    else if (question.equals("no"))
                    {
                      
                      System.exit(1);
                    }
                    else
                    {
                      System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
                       
                    }
                  }
                }
                while(replay==true);
                //end of potential game ending for fifth turn
                
                //beginning of seventh turn
                if(urstick!=1)
                {
                  if (urstick>3)
                  {
                    seventhMove = randNum.nextInt(3)+1;
                    itsstick = urstick-seventhMove;
                    System.out.println("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
                  }
                  else if(urstick==3 &&itsstick==3)
                  {
                    seventhMove = randNum.nextInt(2)+1;
                    itsstick = urstick-seventhMove;
                    System.out.println("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
                  }
                  else if(urstick==2 && itsstick==2)
                  {
                    seventhMove = randNum.nextInt(1)+1;
                    itsstick = urstick-seventhMove;
                    System.out.print("The computer picks up " + seventhMove + " sticks, leaving " + itsstick + " sticks left.");
                  }
                  replay = false;//game also wants to restart here for some reason
                }
                //end of seventh move
                //beginning of potential game ending for sixth turn
                else if (urstick==1)
                {
                  System.out.println("The computer is left with the last stick and therefore you win.");
                  myInput.nextLine();
                  //beginning of replay question
                  System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
                  String question=myInput.next();//inputting answer
                  if (question.equals("yes"))
                  {
                    replay=true;
                  }
                  else if (question.equals("no"))
                  {
                    
                    System.exit(1);//ends program
                  }
                  else
                  {
                    System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
                    
                    System.exit(1);
                  }
                }
              }
              while(replay==true);
              //end of potential game ending for sixth turn
              //beginning of eigth move
              if(itsstick!=1)
              {
                if (itsstick==3)
                {
                  System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                  eigthMove = myInput.nextInt();
                  if(eigthMove>=3)
                  {
                    System.out.println("You didn't insert an appropriate answer. The program will now close.");
                    System.exit(1);
                  }
                  else if(eigthMove==2 | eigthMove==1)
                  {
                    urstick = itsstick-eigthMove;
                    System.out.println("You've picked up " + eigthMove + " sticks, leaving " + urstick + "sticks left.");
                  }
                }
                else if (itsstick==2)
                {
                  System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                  eigthMove = myInput.nextInt();
                  if(eigthMove>=2)
                  {
                    System.out.println("You didn't insert an appropriate answer. The program will now close.");
                    System.exit(1);
                  }
                  else if(eigthMove==2 | eigthMove==1)
                  {
                    urstick = itsstick-eigthMove;
                    System.out.println("You've picked up " + eigthMove + " sticks, leaving " + urstick + "sticks left.");            
                  }
                }
                else
                {
                  System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
                  eigthMove = myInput.nextInt();
                  if(eigthMove==3 | eigthMove==2 | eigthMove==1)
                  {
                    urstick = itsstick-eigthMove;
                    System.out.println("You've picked up " + eigthMove + " sticks, leaving " + urstick + "sticks left.");
                  }
                  else
                  {
                    System.out.println("You didn't follow the rules. The program will now close.");
                    System.exit(1);
                  }
                }
              }
              //end of eigth move
              //beginning of potential game ending for seventh turn
              else if (itsstick==1)
              {
                System.out.println("You are left with the last stick and therefore lost.");
                myInput.nextLine();
                //beginning of replay question
                System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
                String question=myInput.next();//inputting answer
                if (question.equals("yes"))
                {
                  replay=true;
                }
                else if (question.equals("no"))
                {
                  
                  System.exit(1);
                }
                else
                {
                  System.out.println("You didn't insert an appropriate answer, the program will now close.");
                  
                  System.exit(1);
                }
              }
              //end of potential game ending for seventh turn
            }
            while(replay==true);
            //beginning of ninth turn
            if(urstick!=1)
            {
              if (urstick>3)
              {
                ninthMove = randNum.nextInt(3)+1;
                itsstick = urstick-ninthMove;
                System.out.println("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
              }
              else if (urstick==3)
              {
                ninthMove = randNum.nextInt(2)+1;
                itsstick = urstick-ninthMove;
                System.out.println("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
              }
              else if (urstick==2)
              {
                ninthMove = randNum.nextInt(1)+1;
                itsstick = urstick-ninthMove;
                System.out.println("The computer picks up " + ninthMove + " sticks, leaving " + itsstick + " sticks left.");
              }
              replay = false; //game also wants to restart here
            }
            //end of ninth turn
            //beginning of potential game ending for eigth turn
            else if (urstick==1)
            {
              System.out.println("The computer is left with the last stick and therefore you win.");
              myInput.nextLine();
              //beginning of replay question
              System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
              String question=myInput.next();//inputting answer
              if (question.equals("yes"))
              {
                replay=true;
              }
              else if (question.equals("no"))
              {
                
                System.exit(1);//ends program
              }
              else
              {
                System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
                
                System.exit(1);
              }
            }
          }
          while(replay==true);
          //ending of potential game ending for eigth turn
          
          //beginning of tenth turn
          
          if(itsstick!=1)
          {
            if (itsstick==3)
            {
              System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
              tenthMove = myInput.nextInt();
              if (tenthMove==1 | tenthMove==2)
              {
                urstick = itsstick-tenthMove;
                System.out.println("You've picked up " + tenthMove + " sticks, leaving " + urstick + "sticks left.");
              }
              else
              {
                System.out.println("You made an illegal move. The program will now close.");
                System.exit(1);
              }
            }
            else if (itsstick==2)
            {
              System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
              tenthMove = myInput.nextInt();
              if(tenthMove==1)
              {
                urstick = itsstick-tenthMove;
                System.out.println("You've picked up " + tenthMove + " sticks, leaving " + urstick + "sticks left.");
              }
              else
              {
                System.out.println("You made an illegal move. The program will now close.");
                System.exit(1); 
              }
            }
            else if (itsstick>3)
            {
              System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
              tenthMove = myInput.nextInt();
              if (tenthMove==1 | tenthMove==2 | tenthMove==3)
              {
                urstick = itsstick-tenthMove;
                System.out.println("You've picked up " + tenthMove + " sticks, leaving " + urstick + "sticks left.");
              }
              else
              {
                System.out.println("You made an illegal move. The program will now close.");
                System.exit(1);
              }
            }
          }
          //end of tenth turn
          //beginning of potential game ending for ninth turn
          else if (itsstick==1)
          {
            System.out.println("You are left with the last stick and therefore lost.");
            myInput.nextLine();
            //beginning of replay question
            System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
            String question=myInput.next();//inputting answer
            if (question.equals("yes"))
            {
              replay=true;
            }
            else if (question.equals("no"))
            {
              
              System.exit(1);
            }
            else
            {
              System.out.println("You didn't insert an appropriate answer, the program will now close.");
              
              System.exit(1);
            }
          }
        }
        while(replay==true);
        //end of potential game edning for ninth turn
        
        //beginning of eleventh turn
        if(urstick!=1)
        {
          if (urstick>3)
          {
            eleventhMove = randNum.nextInt(3)+1;
            itsstick = urstick-eleventhMove;
            System.out.println("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
          }
          else if(urstick==3)
          {
            eleventhMove = randNum.nextInt(2)+1;
            itsstick = urstick-eleventhMove;
            System.out.println("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
          }
          else if(urstick==2)
          {
            eleventhMove = randNum.nextInt(1)+1;
            itsstick = urstick-eleventhMove;
            System.out.println("The computer picks up " + eleventhMove + " sticks, leaving " + itsstick + " sticks left.");
          }
        }
        //end of eleventh turn
        //beginning of potential game ending for tenth turn
        else if(urstick==1)
        {
          System.out.println("The computer is left with the last stick and therefore you win.");
          myInput.nextLine();
          //beginning of replay question
          System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
          String question=myInput.next();//inputting answer
          if (question.equals("yes"))
          {
            replay=true;
          }
          else if (question.equals("no"))
          {
            
            System.exit(1);//ends program
          }
          else
          {
            System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
            
            System.exit(1);
          }
        }
      }
      while(replay==true);
      
      //end of potential game ending for tenth turn
      //beginning of twelfth turn
      if(itsstick!=1)
      {
        if (itsstick==3)
        {
          System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
          twelfthMove = myInput.nextInt();
          if (twelfthMove==1 | twelfthMove==2)
          {
            urstick = itsstick-twelfthMove;
            System.out.println("You've picked up " + twelfthMove + " sticks, leaving " + urstick + "sticks left.");
          }
          else
          {
            System.out.println("You made an illegal move. The program will now close.");
            System.exit(1);
          }
        }
        else if (itsstick==2)
        {
          System.out.println("\nIt is your turn. Please insert the amount of sticks you want to pick up.");
          twelfthMove = myInput.nextInt();
          if(twelfthMove==1)
          {
            urstick = itsstick-twelfthMove;
            System.out.println("You've picked up " + twelfthMove + " sticks, leaving " + urstick + "sticks left.");
          }
          else
          {
            System.out.println("You made an illegal move. The program will now close.");
            System.exit(1); 
          }
        }
      }
      //end of twelfth turn
      //beginning of potential game ending for eleventh and twelfth turn
      else if(itsstick==1)
      {
        System.out.println("You are left with the last stick and therefore lost.");
        myInput.nextLine();
        //beginning of replay question
        System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
        String question=myInput.next();//inputting answer
        if (question.equals("yes"))
        {
          replay=true;
        }
        else if (question.equals("no"))
        {
          
          System.exit(1);//ends program
        }
        else
        {
          System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
          
          System.exit(1);
        }
      }
      else if(urstick==1)
      {
        System.out.println("The computer is left with the last stick and therefore you win.");
        myInput.nextLine();
        //beginning of replay question
        System.out.println("\nIf you want to try again, type yes. If not then type no and the program will close."); //Asking player to play again
        String question=myInput.next();//inputting answer
        if (question.equals("yes"))
        {
          replay=true;
        }
        else if (question.equals("no"))
        {
          
          System.exit(1);//ends program
        }
        else
        {
          System.out.println("You didn't insert a appropriate answer, you will now need to rerun the game");
          
          System.exit(1);
        }
      }
      while(replay==true);
      myInput.close();
    }
    while (true);
  }
}


