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
        if (tempS[i] != null)
          tempI[i] = Integer.parseInt(tempS[i]);
        else
          stillValid = false;
      }
      
      if (stillValid = true)
      {     
        int cost = tempI[0];
        String floatS = tempS[1];
        int people = tempI[2];
        
        String [] floatA = flotS.split(" ");
        double [] perc = new double[4];
        
        for (int i = 0; i < floatA.length; i++)
          perc[i] = Double.parseDouble(floatA[i]);
      }
    } while(stillValid);
    
    read.close();
  }
}