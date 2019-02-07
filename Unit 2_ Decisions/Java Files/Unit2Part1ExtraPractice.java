import java.util.*;

public class Unit2Part1ExtraPractice
{
  public static void main(String[] args)
  {
    // Introduction
    System.out.println("Unit 2 Part 1 - Extra Practice\n");
    
    // Call each question.
    // I added this so I can just comment out a question's method if I don't want it to run,
    // this way I don't have to run every single one while testing. It's a huge time saver :))
    question1();
    question2();
    question3();
    question4();
    question5();
    question6();
    question7();
    
    // Outro
    System.out.println("Thanks, I hoped you liked these programs :)\n - Kelvin Kellner");
  }
  
  
  // QUESTION 1 //
  public static void question1()
  {
    // Title
    System.out.println("--- Question 1 ---");
    
    Scanner scan = new Scanner(System.in);
    int userInt;
    
    int min = 2;
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
  } // Close Question 1
  
  
  // QUESTION 2 //
  public static void question2()
  {
    // Title
    System.out.println("--- Question 2 ---");
    
    Scanner scan = new Scanner(System.in);
    String country;
    String capital;
    
    System.out.println("Please enter a country, and I will tell you the capital city :)");
    country = scan.nextLine();
    
    if (country.equals("Canada"))
      capital = "Ottawa";
    else if (country.equals("United States") || country.equals("The United States") || country.equals("US") || country.equals("The U.S.") || country.equals("The U.S.") || country.equals("U.S.A"))
      capital = "Washington";
    else if (country.equals("India") || country.equals("The best country"))
      capital = "New Delhi";
    else if (country.equals("Russia") || country.equals("Mother Russia"))
      capital = "Moscow";
    else if (country.equals("Germany"))
      capital = "Berlin";
    else
    {
      System.out.println("Sorry, I don't know the capital of " + country + " :(\nMaybe check your capitalization next time?\n");
      capital = "N/A";
    }
    
    if (!capital.equals("N/A"))
      System.out.println("The capital city of " + country + " is " + capital + ".\n");
    
    scan.close();
  } // Close Question 2
  
  
  // QUESTION 3 //
  public static void question3()
  {
    //Title
    System.out.println("--- Question 3 ---");
    
    Scanner scan = new Scanner(System.in);
    int smallest = 0;
    boolean isError = false;
    
    System.out.println("Please enter 3 different integers, and I will tell you which one is the smallest :)");
    
    int num1 = scan.nextInt();
    int num2 = scan.nextInt();
    int num3 = scan.nextInt();
    scan.nextLine();
    
    if ((num1 < num2) && (num1 < num3))
      smallest = num1;
    else if ((num2 < num3) && (num2 < num1))
      smallest = num2;
    else if ((num3 < num1) && (num3 < num2))
      smallest = num3;
    else
    {
      System.out.println("Error. Did you enter more than one of the same integer? Or maybe they weren't integers at all. I'm not sure :(\n");
      isError = true;
    }
    
    if (!isError)
      System.out.println("Calculating...\nGot it!\nThe smallest number was " + smallest + " :)\n");
    
    scan.close();
  } // Close Question 3
  
  
  // QUESTION 4 //
  public static void question4()
  {
    // Title
    System.out.println("--- Question 4 ---");
    
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Welcome to \"The (Not A Real) Game\".");
    System.out.println("Please select a language.\n1 - English\n2 - French (Français)\n3 - Spanish (Español)\n4 - Italian (Italiano)\n");
    int choice = scan.nextInt();
    scan.nextLine();
    
    if (choice == 1)
      System.out.println("Hello, and welcome to my game! It's a pleasure to have you here :))\n");
    else if (choice == 2)
      System.out.println("Bonjour, et bienvenue dans mon jeu! C'est un plaisir de vous avoir ici :))\n");
    else if (choice == 3)
      System.out.println("Hola, y bienvenido a mi juego! Es un placer tenerte aquí :))\n");
    else if (choice == 4)
      System.out.println("Ciao, benvenuto nel mio gioco! È un piacere averti qui :))\n");
    else
      System.out.println("Sorry, " + choice + " is not a valid language indicator :(\n");
      
    scan.close();
  } // Close Question 4
  
  
  // QUESTION 5 //
  public static void question5()
  {
    // Title
    System.out.println("--- Question 5 ---");
    
    Scanner scan = new Scanner(System.in);
    int num1, num2, num3, high, mid, low;
    boolean isError = false;
    
    // Get user numbers
    System.out.println("Please enter 3 different integers, and this program will rerange them from lowest to highest.");
    num1 = scan.nextInt();
    num2 = scan.nextInt();
    num3 = scan.nextInt();
    scan.nextLine();
    
    // Initialize variables to prevent errors
    high = 0;
    mid = 0;
    low = 0;
    
    // Find highest
    if ((num1 > num2) && (num1 > num3))
      high = num1;
    else if ((num2 > num1) && (num2 > num3))
      high = num2;
    else if ((num3 > num1) && (num3 > num2))
      high = num3;
    else
      isError = true;
    
    // Find lowest
    if (isError);
    else if ((num1 < num2) && (num1 < num3))
      low = num1;
    else if ((num2 < num1) && (num2 < num3))
      low = num2;
    else if ((num3 < num1) && (num3 < num2))
      low = num3;
    else
      isError = true;
    
    // Use deductive reasoning to find middle
    if (isError);
    else if ((num1 != high) && (num1 != low))
      mid = num1;
    else if ((num2 != high) && (num2 != low))
      mid = num2;
    else if ((num3 != high) && (num3 != low))
      mid = num3;
    else
      isError = true;
    
    // Either print out the results, or throw an error message
    if (isError)
      System.out.println("Sorry, there was an error. Maybe you entered the same number more than once or something? I'm not sure, sorry :(\n");
    else
      System.out.println("Your numbers from lowest to highest are:\n" + low + "\n" + mid + "\n" + high + "\n");
      
    scan.close();
  } // Close Question 5
  
  
  // QUESTION 6 //
  public static void question6()
  {
    // Title
    System.out.println("--- Question 6 ---");
    
    Scanner scan = new Scanner(System.in);
    boolean isError = false;
    int roots = 0;
    double root1 = 0;
    double root2 = 0;
    
    System.out.println("This fancy program will find the roots of any quadratic formula. Please enter \"a\", in form y = ax^2 + bx + c");
    double a = scan.nextDouble();
    System.out.println("Now enter \"c\", in form y = ax^2 + bx + c");
    double b = scan.nextDouble();
    System.out.println("And lastly, enter \"c\", in form y = ax^2 + bx + c");
    double c = scan.nextDouble();
    scan.nextLine();
    
    // Check discriminant to find the number of roots
    double disc = ((b*b)-(4*a*c));
    if (disc < 0)
      roots = 0;
    else if (disc == 0)
      roots = 1;
    else if (disc > 0)
      roots = 2;
    else
      isError = true;
    
    // Calculate the roots and display the results
    if (isError);
    else if (roots == 0)
      System.out.println("y=" + a + "x^2 + " + b + "x + " + c + " has 0 real roots.\n");
    else if (roots == 1)
    {
      root1 = ((-b)/(2*a));
      System.out.println("y = " + a + "x^2 + " + b + "x + " + c + " has 1 real root.\nx = " + root1 + "\n"); 
    }
    else if (roots == 2)
    {
      root1 = (((-b) + Math.sqrt((b*b) - (4*a*c))) / (2*a));
      root2 = (((-b) - Math.sqrt((b*b) - (4*a*c))) / (2*a));
      System.out.println("y = " + a + "x^2 + " + b + "x + " + c + " has 2 real roots.\nx = " + root1 + "\nx = " + root2 + "\n");
    }
    
    scan.close();
  } // Close Question 6
  
  
  // QUESTION 7 //
  public static void question7()
  {
    // Title
    System.out.println("--- Question 7 ---");
    
    Scanner scan = new Scanner(System.in);
    
    int toonies, loonies, quarters, dimes, nickels, pennies;
    
    System.out.println("Hello!\nPlease type a monetary value in cents, and this program will tell you the most effective way to pay using only coins :)");
    int value = scan.nextInt();
    int left = value;
    scan.nextLine();
    
    toonies = returnCount(left, 200);
    left = deduceValue(left, 200, toonies);
    loonies = returnCount(left, 100);
    left = deduceValue(left, 100, loonies);
    quarters = returnCount(left, 25);
    left = deduceValue(left, 25, quarters);
    dimes = returnCount(left, 10);
    left = deduceValue(left, 10, dimes);
    nickels = returnCount(left, 5);
    left = deduceValue(left, 5, nickels);
    pennies = returnCount(left, 1);
    left = deduceValue(left, 1, pennies);
    
    System.out.print("To pay " + value + " cents in coins, you can use...");
    
    if (toonies > 0)
    {
      if (toonies == 1)
        System.out.print("\n" + toonies + " toonie");
      else
        System.out.print("\n" + toonies + " toonies");
    }
    
    if (loonies > 0)
    {
      if (loonies == 1)
        System.out.print("\n" + loonies + " loonie");
      else
        System.out.print("\n" + loonies + " loonies");
    }
    
    if (quarters > 0)
    {
      if (quarters == 1)
        System.out.print("\n" + quarters + " quarter");
      else
        System.out.print("\n" + quarters + " quarters");
    }
    
    if (dimes > 0)
    {
      if (dimes == 1)
        System.out.print("\n" + dimes + " dime");
      else
        System.out.print("\n" + dimes + " dimes");
    }
    
    if (nickels > 0)
    {
      if (nickels == 1)
        System.out.print("\n" + nickels + " nickel");
      else
        System.out.print("\n" + nickels + " nickels");
    }
    
    if (pennies > 0)
    {
      if (pennies == 1)
        System.out.print("\n" + pennies + " penny");
      else
        System.out.print("\n" + pennies + " pennies");
    }

    if (value <= 0)
      System.out.print("\nSorry, that value can't be payed for using coins. Either we ran into an error, or you're trying to pay for something that's free :/\n\n");
    else
      System.out.print(".\nHope that helps :))\n\n");
    
    scan.close();
  } // Close Question 7
  
  public static int returnCount(int value, int div)
  {
    int count = ((value - (value % div)) / div);
    return count;
  }
  
  public static int deduceValue(int start, int mult, int count)
  {
    int deducted = (start - (mult * count));
    return deducted;
  }
  
  public static int randomNumber(int min, int max)
  {
    Random random = new Random();
    int num = random.nextInt((max - min) + 1) + min;
    return num;
  }
  
} // end class