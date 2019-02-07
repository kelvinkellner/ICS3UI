import java.util.*;

public class ForLoopTasks
{
  public static void main(String[] args)
  {
    int sum = 0;
    
    for (int i = 1; i <= 20; i++)
    {
      sum += i;
    }
    
    System.out.println("The sum of ALL numbers from 1-10 is: " + sum);
    
    sum = 0;
    
    for (int i = 1; i <= 20; i++)
    {
      if (i % 2 == 0)
        sum += i;
    }
    
    System.out.println("The sum of EVEN numbers from 1-10 is: " + sum);
  }
  
  Scanner scan = new Scanner(System.in);
  boolean valid = false;
  
  System.out.println("What's your name?");
  String name = scan.nextLine();
  
  System.out.println("What's your favourite number?");
  
  do
  {
    int favNum = scan.nextInt();
    scan.nextLine();
    
    if (favNum < 0 || favNum > 100)
      System.out.println("Invalid answer. 1-10 only");
    else
      valid = true;
  } while(!valid);
  
  for (int i = favNum; i > 0; i--)
    System.out.println(name);
}