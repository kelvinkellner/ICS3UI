import java.io.*;

public class FileWriting
{
  public static void main(String[] args) throws IOException
  {
    FileWriter writer = new FileWriter("datafile.txt");
    PrintWriter printer = new PrintWriter(writer);
    
    printer.println("I CAN PRINT");
    printer.close();
    
    FileReader reader = new FileReader("datafile.txt");
    BufferedReader buff = new BufferedReader(reader);
    
    String line = "";
    String block = "";
    
    while (line != null)
    {
      line = buff.readLine();
      if (line != null)
        block += line;
    }
    
    System.out.println(block);
    buff.close();
  }
}