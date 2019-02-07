import java.util.*;

public class CCCQuestion2
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    scan.nextLine();
    String in = scan.nextLine();
    
    String [] numS = in.split(" ");
    int [] nums = new int[n];
    
    for (int i = 0; i < n; i++)
      nums[i] = Integer.parseInt(numS[i]);
    
    Arrays.sort(nums);
    
    // separate low + high tides
    int half = n/2;
    
    int [] lowTide = new int[half];
    int [] highTide = new int[(n - half)];
    
    // get low tides
    for (int i = 0; i < half; i++)
      lowTide[i] = nums[i];
    
    // reverse array
    for(int i=0; i<half/2; i++){
      int temp = lowTide[i];
      lowTide[i] = lowTide[half -i -1];
      lowTide[half -i -1] = temp;
    }
    
    // get high tides
    for (int i = 0; i < n; i++)
    {
      if (i > (half - 1))
        highTide[(i - half)] = nums[i];
    }
    
    int [] newOrder = new int[n];
    String out = "";
    
    // odd even / low high sorting
    for (int i = 0; i < n; i++)
    {
      if (i % 2 == 0)
        newOrder[i] = lowTide[(i/2)];
      else
        newOrder[i] = highTide[((i-1)/2)];
      out += newOrder[i] + " ";
    }
    
    System.out.println(out);
    scan.close();
    
    //10 50 40 7 3 110 90 2
  } 
}