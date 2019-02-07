import java.util.*;

public class CountingSortAttempt
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    scan.nextLine();
    String input = scan.nextLine();
    String [] inS = input.split(" ");
    int [] in = new int[n];
    
    for (int i = 0; i < n; i++)
      in[i] = Integer.parseInt(inS[i]);
    
    int [] count = new int[n];
    int counter = 0;
    
    for (int i = 0; i < n; i++)
    {
      counter = 0;
      
      for (int x = 0; x < n; x++)
      {
        if (i == in[x])
          counter++;
      }
      
      count[i] = counter;
    }
    
    // PRINT //
    String out = "";
    for (int i = 0; i < n; i++)
      out+=count[i];
    System.out.println(out);
    
    int [] sum = new int[n];
    counter = 0;
    
    for (int i = 0; i < n; i++)
    {
      if (i > 0)
        counter = sum[i-1];
      sum[i] = count[i] + counter;
    }
    
    // PRINT //
    out = "";
    for (int i = 0; i < n; i++)
      out+=sum[i];
    System.out.println(out);
    
    int [] output = new int[n];
    counter = 0;
    int dif = 0;
    
    for (int i = 0; i < n; i++)
    {
      if (i > 0)
        counter = sum[i-1];
      else
        counter = 1;
      dif = sum[i] - counter;
      
      if (dif == 0)
        output[i] = counter;
      for (int x = 0; x < dif; x++)
        output[i+x] = i;
    }
    
    // PRINT //
    out = "";
    for (int i = 0; i < n; i++)
      out+=output[i];
    System.out.println(out);
    
    //1 4 1 2 7 5 2
  }
}