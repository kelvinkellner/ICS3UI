import java.io.*;

public class Problem2
{
  public static void main(String[] args) throws IOException
  {
    FileReader file = new FileReader("DATA22.txt");
    BufferedReader read = new BufferedReader(file);
    
    for (int l = 0; l < 10; l++)
    {
      
      String line = read.readLine();
      int r = Integer.parseInt(line);
      
      
      String index[] = new String[r];
      
      int min[] = new int[r];
      

      
      for (int j = 0; j < r; j++)
      {
        line = read.readLine();
        String array1[] = line.split(" ");
        index[j] =array1[0];
        
        int size = Integer.parseInt(array1[1]);
        int diameter[] = new int[size];
        
        for(int i=0;i<size;i++)
        {
          diameter[i] = Integer.parseInt(array1[i+2]);
        }
        
        
        for(int i=diameter.length;i>=2;i--)
        {
          for(int s=0;s+1<i;s++)
          {
            if(diameter[s]>diameter[s+1])
            {
              diameter = swap(s,s+1,diameter);
            }
          }
        }
        
        min[j] = diameter[0];
        
        
        
      }
      
      int smallest = min[0];
      
      for(int k=0;k<r;k++)
      {
        if(smallest>min[k])
        {
          smallest = min[k];
        }
      }
      
      
      
      System.out.print(smallest + " {");
      
      int counter = 0;
      
      for(int w=0;w<min.length;w++)
      {
        if(min[w] == smallest)
        {
          if (counter != 0)
            System.out.print("," + index[w]);
          else
          {
            System.out.print(index[w]);
            counter = 1;
          }
        }
      }
      
      System.out.print("}\n");
      
    }
    
    read.close();
  }  //end main
  
  public static int[] swap(int j, int k, int[] array)
  {
    int temp = array[j];
    array[j] = array[k];
    array[k] = temp;
    
    return array;
  }
}  //end class 