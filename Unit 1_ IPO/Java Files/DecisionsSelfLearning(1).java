public class DecisionsSelfLearning
{
  public static void main(String[] args)
  {
    
    int grade = 0;
    
    while (grade < 100)
    {
      
    if (grade > 90)
      System.out.println("Holy shit my guy, you're killing it! Your grade was a: " + grade);
    else if (grade > 50) 
      System.out.println("Ayyy, you passed! Your grade was a: " + grade);
    else
      System.out.println("Oooooh, sorry man, you failed :( Your grade was a: " + grade);
    
    grade++;
    
    }//end while
    System.out.println("You're a perfect student now :)");
    
    int count = 0;
    for (int i=2; i<9999999999; i++){
      count++;
      System.out.println("2^" + count + " = " + i);
    }
  }//end main
}//end class