import java.io.*;

public class ECOO2017P2Attempt2
{
  public static void main(String[] args) throws IOException
  {
    FileReader file = new FileReader("DATA21.txt");
    BufferedReader reader = new BufferedReader(file);
    
    boolean isNewComp = false;
    
    int countJ = 0;
    int countC = 0;
    
    do
    {
      
      String line = reader.readLine();
      int n = Integer.parseInt(line);
      
      String next = reader.readLine();
      String store[] = next.split(" ");
      
      String names[][] = new String[n][2];
      int scores[][] = new int[n][3];
      int totals[] = new int[n];
      
      if (store[0].equals("J"))
      {
        for (int i = 1; i <= 3; i++)
        {
          scores[countJ][i-1] = Integer.parseInt(store[i]);
        }
        countJ++;
      }
      else if (store[0].equals("*"))
      {
        isNewComp=true;
      }
      else
      {
        names[countC][0] = next;
        names[countC][1] = countJ;
        countC++;
        countJ = 0;
      }
      
      while (!isNewComp);
      
      reader.close();
    } while (1 < 2); // end comp
  }
}