import java.util.*;

public class MovieCost
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    
    String name;
    int age;
    String day;
    
    // Get user's name and age, and the current day of the week
    System.out.println("What is your name?");
    name = scan.nextLine();
    System.out.println("What is your age (integers only)");
    age = scan.nextInt();
    scan.nextLine();
    System.out.println("What day is it today (please type full name, with capitalization)?");
    day = scan.nextLine();
    
    if (age < 0 || age > 120)
    {
      System.out.println("Error. Invalid age entered.");
    }
    else if (age >= 65)
    {
      if (day.equals("Tuesday"))
      {
        System.out.println("If you are " + age + " years old, you will need a seniors ticket.\nToday is also Tuesday! So you get an addition discount :)\nThe movie will cost $7.\nEnjoy the show!");
      }
      else
      {
        System.out.println("If you are " + age + " years old, you will need a seniors ticket.\nThe movie will cost $10.\nEnjoy the show!");
      }
    }
    else if (age <= 12)
    {
      if (day.equals("Tuesday"))
      {
        System.out.println("If you are " + age + " years old, you will need a child ticket.\nToday is also Tuesday! So you get an addition discount :)\nThe movie will cost $6.\nEnjoy the show!");
      }
      else
      {
        System.out.println("If you are " + age + " years old, you will need a child ticket.\nThe movie will cost $9.\nEnjoy the show!");
      }
    }
    else
    {
      if (day.equals("Tuesday"))
      {
        System.out.println("If you are " + age + " years old, you will need a regular ticket.\nToday is a Tuesday! So you get a discount :)\nThe movie will cost $9.\nEnjoy the show!");
      }
      else
      {
        System.out.println("If you are " + age + " years old, you will need a regular ticket.\nThe movie will cost $12.\nEnjoy the show!");
      }
    }
    
    scan.close();
  } // end main
} // end class