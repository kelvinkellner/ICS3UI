import java.util.*;

public class Unit2Part1ExtraPractice
{
  public static void main(String[] args)
  {
    // Introduction
    System.out.println("Unit 2 Part 1 - Extra Practice");
  }
  
  public static question1
  {
    
    Scanner scan = new Scanner(System.in);
    int userInt;
    
    // Question 1
    System.out.println("\n--- Question 1 ---");
    
    int min = 1;
    int max = 12;
    
    int num1 = randomNumber(min,max);
    int num2 = randomNumber(min,max);
    
    System.out.println("What is " + num1 + " * " + num2 + "?");
    userInt = scan.nextInt();
    if (userInt == (num1*num2))
      System.out.println("Nice! That's correct :)\n");
    else
      System.out.println("Sorry! That's wrong :(\nThe correct answer was " + (num1*num2) + "\n");
    
    num1 = randomNumber(min,max);
    num2 = randomNumber(min,max);
    
    System.out.println("What is " + num1 + " + " + num2 + "?");
    userInt = scan.nextInt();
    if (userInt == (num1+num2))
      System.out.println("Nice! That's correct :)\n");
    else
      System.out.println("Sorry! That's wrong :(\nThe correct answer was " + (num1+num2) + "\n");
    
    num1 = randomNumber(min,max);
    num1 = randomNumber(min,max);
    
    System.out.println("What is " + num1 + "^" + num2 + "?");
    userInt = scan.nextInt();
    if (userInt == (int) Math.pow(num1,num2))
      System.out.println("Nice! That's correct :)\n");
    else
      System.out.println("Sorry! That's wrong :(\nThe correct answer was " + (int) Math.pow(num1,num2) + "\n");
    
    
    scan.close();
  }
  
  public static int randomNumber(int min, int max)
  {
    Random random = new Random();
    int num = random.nextInt((max - min) + 1) + min;
    return num;
  }
  
} // end class