import java.io.*;

public class ECOO2017P1
{
  public static void main(String[] args) throws IOException
  {
    FileReader filein = new FileReader("DATA11.txt");
    BufferedReader read = new BufferedReader(filein);
    boolean stillValid = true;
    
    do
    {
      String [] tempS = new String[3];
      int [] tempI = new int[tempS.length];
      
      for (int i = 0; i < tempS.length; i++)
      {
        tempS[i] = read.readLine();
        if (tempS[i] != null && i != 1)
          tempI[i] = Integer.parseInt(tempS[i]);
        else if (i != 1)
          stillValid = false;
      }
      
      if (stillValid)
      {     
        int cost = tempI[0];
        String floatS = tempS[1];
        int people = tempI[2];
        
        String [] floatA = floatS.split(" ");
        double [] perc = new double[4];
        
        for (int i = 0; i < floatA.length; i++)
        {
          perc[i] = Double.parseDouble(floatA[i]);
          perc[i] = Math.round(perc[i] * people);
        }
        
        double total = 0;
        
        total += perc[0] * 12;
        total += perc[1] * 10;
        total += perc[2] * 7;
        total += perc[3] * 5;
        
        if (total >= cost)
          System.out.println("NO");
        else
          System.out.println("YES");
      }
    } while(stillValid);
    
    read.close();
  }
}