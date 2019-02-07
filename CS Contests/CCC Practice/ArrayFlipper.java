public class ArrayFlipper
{
  public static void main(String[] args)
  {
    int [] someArray = {0, 2, 12, 69, 3, 2, 1};
    flipArrayInt(someArray);
    
    for (int i = 0; i < someArray.length; i++)
      System.out.println(someArray[i]);
    
    String [] someOtherArray = {"Jimmy", "Bob", "Sherlock", "Charles"};
    flipArrayString(someOtherArray);
    
    for (int i = 0; i < someOtherArray.length; i++)
      System.out.println(someOtherArray[i]);
  }
  
  public static void flipArrayInt(int[] data)
  {
    for(int i = 0; i < data.length / 2; i++)
    {
      int temp = data[i];
      data[i] = data[data.length - i - 1];
      data[data.length - i - 1] = temp;
    }
  }
  
  public static void flipArrayString(String[] data)
  {
    for(int i = 0; i < data.length / 2; i++)
    {
      String temp = data[i];
      data[i] = data[data.length - i - 1];
      data[data.length - i - 1] = temp;
    }
  }
  
  public static void flipArrayDouble(double[] data)
  {
    for(int i = 0; i < data.length / 2; i++)
    {
      double temp = data[i];
      data[i] = data[data.length - i - 1];
      data[data.length - i - 1] = temp;
    }
  }
}