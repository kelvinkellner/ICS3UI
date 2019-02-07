public class Time
{
  public static void main(String[] arg)
  {
    int hour = 13;
    int min = 48;
    int sec = 24;
    
    int tilMidnight = (24*60*60 - (13*60*60 + (48 * 60) + 24));
    int secondsTilMidnight = tilMidnight % 60;
    int minutesTilMidnight = (tilMidnight - secondsTilMidnight) % (60*60);
    int hoursTilMidnight = (tilMidnight - secondsTilMidnight - (minutesTilMidnight * 60)) % (24*60*60);
    
    System.out.println("It has been: " + hour + " hours, " + min + " minutes, and " + sec + " seconds since midnight.");
    System.out.println("It will be a new day in: " + hoursTilMidnight + " hours, " + minutesTilMidnight + " minutes, and " + secondsTilMidnight + " seconds.");
  } // end main
} // end class