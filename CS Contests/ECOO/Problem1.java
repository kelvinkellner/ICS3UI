import java.io.*;

public class Problem1
{
  public static void main(String[] args) throws IOException
  {
    FileReader file = new FileReader("DATA12.txt");
    BufferedReader read = new BufferedReader(file);
    
    String line = "";
    String something[] = new String[2];
    
    for (int k = 0; k < 10; k++)
    {
      line = read.readLine();
      something = line.split(" ");
      
      int t = Integer.parseInt(something[0]);
      int n = Integer.parseInt(something[1]);
      
      int count = 0;
      
      for (int i = 0; i < n; i++)
      {
        line = read.readLine();
        if(line.equals("E"))
        {
          if (count > 0)
            count--;
        }
        else if (line.equals("B"))
        {
          if (count > 0)
            count--;
          count+=t;
        }
      }
      
      if(count>0)
      {
        count--;
      }
      else
      {
        count=0;
      }
      
      System.out.println(count);
    }
    
    read.close();
  }
}