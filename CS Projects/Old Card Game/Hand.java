import java.util.*;

public class Hand
{
  public static void main(String[] args)
  {
    int handSize = 0;
    boolean withSuits = false;
  } // end main
  
  public static int[] randomHand (int handSize, boolean withSuits)
  {
    int hand[] = new int[handSize];
    Random random = new Random();
    
    for (int i = 0; i < handSize; i++)
    {
      hand[i] = random.nextInt(13);
      if (withSuits)
        hand[i] += random.nextInt(4) * 100;
    }
    return hand;
  }
} // end class