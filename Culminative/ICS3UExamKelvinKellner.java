import java.util.*; // Import Java Class

public class ICS3UExamKelvinKellner
{
  public static void main(String[] args)
  {
    System.out.println("\nWelcome to the Olympic Diving Event Score Calculator!"); // Welcome user
    
    setupDB(); // Initiliaze the Database of diver scores
  } // End Main
  
  // SETUP DB: Creates and fills arrays with the info for all the divers
  public static void setupDB()
  {
    Scanner scan = new Scanner(System.in); // Initialize scanner
    System.out.println("\nHow many divers participated in the event?");
    int divers = scan.nextInt(); // Find and store the # of divers
    scan.nextLine(); // Clear scanner
    
    // Initialize empty arrays for the divers' info
    String[] names = new String[divers];
    int[] difficulty = new int[divers];
    int[][] scores = new int[divers][5];
    int[] total = new int[divers];
    
    for(int i = 0; i < divers; i++) // For the # of divers...
    {
      // Prompt the user for, and store, all the diver's info into the appropriate array
      System.out.println("\nWhat is the name of Diver #" + (i+1) + "?");
      names[i] = scan.nextLine();
      
      System.out.println("\nWhat was the difficulty of " + names[i] + "'s dive? (integers only)");
      difficulty[i] = scan.nextInt();
      
      System.out.println("\nWhat were the scores for " + names[i] + "'s dive? (integers only)");
      for(int k=0; k<5; k++) // Loop 5 times, once for each judge
      {
        System.out.println("Judge " + (k+1) + ":");
        scores[i][k] = scan.nextInt(); // Store that judge's score into the 2D array of scores
      }
      scan.nextLine(); // Clear scanner
      
      total[i] = total(scores[i], difficulty[i]); // Calculate and store the diver's total score
    } // Close diver info loop
    
    System.out.println("\nAwesome!\nWe've got all our info, now let's go to the main menu...");
    menu(scan, names, difficulty, scores, total); // Go to menu method
  } // End setupDB
  
  // MENU: Opens a menu of options for the user to pick and choose from
  public static void menu(Scanner scan, String[] names, int[] difficulty, int[][] scores, int[] total)
  {
    // Print out user options
    System.out.println("\nWhat would you like to do?");
    System.out.println("1 - Display winner");
    System.out.println("2 - Display scores");
    System.out.println("3 - Exit");
    
    System.out.println("\nPlease enter the number matching the action you would like to perform:");
    if(scan.hasNextInt()) // Check if the user entered an integer
    {
      int action = scan.nextInt(); // Store the action they have chosen
      scan.nextLine(); // Clear scanner
      
      // Call the appropriate method for each action
      if(action==1)
        winner(scan, names, difficulty, scores, total);
      else if(action==2)
        scores(scan, names, difficulty, scores, total);
      else if(action==3)
        exit(scan);
      else
      {
        // If the user's response was not valid, make them try again
        System.out.println("\nSorry, that was not a valid response :(\nPlease try again...");
        menu(scan, names, difficulty, scores, total);
      }
    } // Close if integer
    else
    {
      // If the user's response was not an integer, make them try again
      System.out.println("\nSorry, that was not a valid response :(\nPlease try again...");
      scan.next(); // Clear scanner
      menu(scan, names, difficulty, scores, total);
    }
  } // End menu
  
  // WINNER: Finds the diver with the highest score and celebrates them as the winner (does not account for ties)
  public static void winner(Scanner scan, String[] names, int[] difficulty, int[][] scores, int[] total)
  {
    int winner = 0; // This int will be used to store the current highest score as we cycle through all the divers
    
    for(int i=1; i<names.length; i++) // For each diver (skipping the first one since they start as the highest score by default)
    {
      if(total[i] > total[winner]) // If this diver had a higher score
        winner = i; // Update the new highest score
    }
    
    System.out.println("\n" + names[winner] + " was the winner with a score of " + total[winner] + "!");
    menu(scan, names, difficulty, scores, total);
  } // End winner
  
  // SCORES: Prints out a table of all the info for every diver
  public static void scores(Scanner scan, String[] names, int[] difficulty, int[][] scores, int[] total)
  {
    System.out.println("\nDiver #\tName\t\tJudge 1\tJudge 2\tJudge 3\tJudge 4\tJudge 5\t\tDifficulty\t\tTotal"); // Setup chart
    for(int i = 0; i<names.length; i++) // Print out the data for each diver
      System.out.println((i+1) +"\t" + names[i] +"\t\t" + scores[i][0] +"\t" + scores[i][1] +"\t" + scores[i][2] +"\t" + scores[i][3] +"\t" + scores[i][4] +"\t\t" + difficulty[i] +"\t\t" + total[i]);
    menu(scan, names, difficulty, scores, total);
  } // End scores
  
  // EXIT: Allows the program to end peacefully
  public static void exit(Scanner scan)
  {
    System.out.println("\nOkay!\nGoodbye :)\n");
    scan.close();
  } // End exit
  
  // TOTAL: Calculates and returns the total score for a diver
  public static int total(int[] scores, int difficulty)
  {
    // Create variables
    double total = 0;
    int highest = 0; // Index # of highest score
    int lowest = 0; // Index # of lowest score
    
    for(int i = 1; i < scores.length; i++) // Loop through all scores
    {
      if(scores[i] > scores[highest]) // If this value is higher...
        highest = i; // Make it the highest
    } // Close highest
    
    for(int i = 1; i < scores.length; i++) // Loop through all scores
    {
      if(scores[i] < scores[lowest]) // If this value is lower...
        lowest = i; // Make it the lowest
    } // Close lowest
    
    for(int i = 0; i < scores.length; i++) // Loop through all scores
      total += (double) scores[i]; // Add that score to the total
    
    total -= (double) (scores[highest] + scores[lowest]); // Subtract the highest and lowest scores since they don't count
    total = (total/(scores.length-2)); // Find the average
    total = total*difficulty; // Multiply by difficulty
    
    total = Math.round(total); // Round off all digits past the 1/10ths column
    return (int) total; // Return the total
  } // End total
} // End class