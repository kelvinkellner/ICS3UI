// Kelvin Kellner
// Unit 3 Test 1 - For Loops
// Mrs. Cooper
// 28 March 2018

public class Unit3Test1ForLoops
{
  public static void main(String[] args)
  {
    
    int height = 5; // the height of the wave
    int waves = 3; // how many waves to draw
    
    
    // this for loop will contain the code that will draw each wave. repeat this loop for every wave you want to draw (3 times in the example)
    for (int i = 0; i < waves; i++)
    {
      
      int stars = 0; // this variable will store how many stars we will draw in each line
      boolean isIncreasing = true; // this variable will toggle whether or not we should add or delete a star from the next line of the wave (whether the wave is growing or shrinking)
      
      int max = (height * 2) - 1; // this number will represent how many lines each wave will take up. the wave is repeated twice, once forward, once backwards. the middle line in the wave, the longest one, will not be repeated. that's why we subtract one
      
      // this loop will draw each individual line in the wave
      for (int k = 0; k < max; k++)
      {
        // if the wave has already reached its maximum height, we will want to start decreasing the line size
        if (stars == height)
            isIncreasing = false;
        
        // increase/decrease the size of the next line in the wave, depending on whether or not we've hit the waves peak/max
        if (isIncreasing)
          stars++; // increase the wave size
        else
          stars--; // decrease the wave size
        
        String line = ""; // create an empty string to carry the line of stars to print
        for (int m = 0; m < stars; m++) // for how many stars we want to use
          line += "*"; // add a star to the line
        
        // draw the line of stars
        System.out.println(line);
        
      } // end individual wave loop
    } // end all waves
    
  } // end main method
} // end class