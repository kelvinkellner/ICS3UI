import java.io.*;

public class Problem3
{
  public static void main(String[] args) throws IOException
  {
    FileReader file = new FileReader("DATA32.txt");
    BufferedReader read = new BufferedReader(file);
    
    for (int a = 0;a<10;a++)
    {
      
      String line = read.readLine();
      String array[] = line.split(" ");
      
      boolean match = true;
      int wrongs = 0;
      
      int n = Integer.parseInt(array[0]);
      int x = Integer.parseInt(array[1]); // even increasing
      int y = Integer.parseInt(array[2]); // odd subtract
      int z = Integer.parseInt(array[3]); // zero sets
      
      line = read.readLine();
      int size = line.length();
      
      String codesX[][] = new String[n][size];
      int codes[][] = new int[n][size];
      String q[] = new String[n];
      
      for (int i = 0;i<n;i++)
      {
        String row = "";
        codesX[i] = line.split("");
        
        for (int j=0;j<size;j++)
        {
          codes[i][j] = Integer.parseInt(codesX[i][j]);
          
          if (codes[i][j] == 0)
            codes[i][j] = z;
          else if (codes[i][j] % 2 == 0)
            codes[i][j] += x;
          else
          {
            codes[i][j] -= y;
            if (codes[i][j] < 0)
              codes[i][j] = 0;
          }
          row+=codes[i][j];
        }
        q[i] = row;
        line = read.readLine();
      }
      
      String answers[] = new String[n];
      
      for (int i = 0; i < n; i++)
      {
        answers[i] = read.readLine();
        if (!answers[i].equals(q[i]))
        {
          match = false;
          wrongs += 1;
        }
      }
      
      int wrong[] = new int[wrongs];
      int counter = 0;
      
      for (int i = 0; i < n; i++)
      {
        if (!answers[i].equals(q[i]))
        {
          wrong[counter] = i;
          counter++;
        }
      }
      
      counter = 0;
      
      if (match)
        System.out.println("MATCH");
      else
      {
        System.out.print("FAIL: ");
        for (int i = 0; i<wrong.length;i++)
        {
          if (counter == 0)
          {
            System.out.print(wrong[i]+1);
            counter++;
          }
          else
          {
            System.out.print("," + (wrong[i]+1));
          }
        }
        System.out.print("\n");
      }
      read.readLine();
    }
    read.close();
  }
}