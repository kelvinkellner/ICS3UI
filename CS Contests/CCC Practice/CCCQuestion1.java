import java.util.*;

public class CCCQuestion1
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    scan.nextLine();
    
    String t1 = scan.nextLine();
    String t2 = scan.nextLine();
    
    String [] tm1 = t1.split(" ");
    String [] tm2 = t2.split(" ");
    
    int [] team1 = new int[n];
    int [] team2 = new int[n];
    
    for (int i = 0; i < n; i++)
    {
      team1[i] = Integer.parseInt(tm1[i]);
      team2[i] = Integer.parseInt(tm2[i]);
    }
    
    int k = 0;
    
    int t1tot = 0;
    int t2tot = 0;
    
    for (int i = 0; i < n; i++)
    {
      t1tot += team1[i];
      t2tot += team2[i];
      
      if (t1tot == t2tot)
        k = i + 1;
    }
    
    System.out.println(k);
    scan.close();
  }
}