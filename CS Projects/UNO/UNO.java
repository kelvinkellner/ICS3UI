// Kelvin Kellner
// Mrs. Cooper
// UNO Card Game
// 17 June 2018

// Welcome to my UNO program!
// This program allows you to play the classic game of UNO with an unlimited number of players and CPUs.
// It has all the usual features, including special cards,
// However, you don't have the option to call UNO when a player has only one card,
// I couldn't figure out an effective way to add it since all players share the same computer :(
//
// Regardless,
// I hope you enjoy!
// - Kelvin :)

import java.util.*; // Import Java Utility Package

public class UNO
{
  public static void main(String[] args) throws InterruptedException
  {
    newGame(); // Initialize the game
  } // Close Main Method
  
  // NEW GAME: Sets up the deck, players, and CPUs needed to run the game
  public static void newGame() throws InterruptedException
  {
    // Welcome User to the Game
    Scanner scan = new Scanner(System.in); // Initialize Scanner
    System.out.println("\nWelcome to UNO!");
    Thread.sleep(400); // Sleep delays are used for the aesthetics, so it feels like a real game
    System.out.println("This game of UNO can be played with players and CPU's...");
    Thread.sleep(800);
    
    Deck deck = new Deck(); // Setup a brand new deck of cards to deal from
    
    // Adding Players //
    int users = 0; // # of Users that will be playing
    boolean valid = false; // This will be used to check for invalid options while setting up the game
    while(!valid) // Until valid # of user
    {
      System.out.println("\nHow many players would you like to play with?");
      if(scan.hasNextInt()) // If an integer is entered
      {
        users = scan.nextInt(); // # of users
        scan.nextLine(); // Clear Scanner
        if(users>=0)
          valid = true; // We have a valid answer, so we can exit the loop
      } // End If Integer Entered
      else
        scan.next(); // Clear Scanner (for if they don't enter an integer)
      if(!valid) // If we have recieved anything other than a positive integer or zero, print a message and restart the loop
        System.out.println("\nPlease enter a valid number.");
    } // Close While # of Players
    
    Player[] gamers = new Player[users]; // Create an array to store all the actual users playing the game (as opposed to cpus)
   
    for(int user = 0; user < gamers.length; user++) // For # of Users
    {
      System.out.println("\nWhat is Player " + (user+1) + "'s name?");
      String name = scan.nextLine(); // Save whatever name they use
      gamers[user] = new Player(name, true, deck.newHand()); // Create a new player object with a hand and add it to the array (notice the true here? this indicates a player account, CPU accounts use false)
    } // End Adding Players
    
    // Adding CPUs //
    int cpus = 0; // # of CPUS
    valid = false; // Reset the boolean to use again
    while(!valid)
    {
      System.out.println("\nHow many CPUS would you like to play with?");
      if(scan.hasNextInt()) // If an integer is used
      {
        cpus = scan.nextInt(); // # of CPUs
        scan.nextLine(); // Clear Scanner
        if(cpus>=0)
          valid = true; // If positive integer or zero, we can exit the loop
      }
      else
        scan.next(); // Clear Scanner
      if(!valid) // Print an error on an invalid entry
        System.out.println("\nPlease enter a valid number.");
    } // Close # of CPUs
    
    Player[] players = new Player[users+cpus]; // Create a new array that will store the Users and CPUs together
    
    for(int i=0;i<gamers.length;i++) // Duplicate the Users/Players into the new array
      players[i]=gamers[i];
    
    for(int user = 0; user < cpus; user++)
      players[user+gamers.length] = new Player("CPU " + (user+1), false, deck.newHand()); // Add the CPUs into the array after the players
    
    valid = false; // Reset boolean to default
    // Deal a non-special card to start the game
    while(!valid)
    {
      if(deck.pile[0].rank>=10) // If the top of the stock pile is a special card...
        deck.pile = deck.shuffle(deck.pile); // Shuffle, then loop again
      else // If it is a regular card...
      {
        deck.trash = deck.dealTo(deck.trash); // Deal it into play (the trash pile is used as the playing pile, so the top of the trash is the active card)
        valid = true; // Then exit the loop
      }
    } // End Deal First Card
    
    System.out.println("\nLet's get started!");
    Thread.sleep(1200);
    
    playGame(scan, deck, players); // Run the method that will be used to call turns and end the game whenever needed
    
  } // Close New Game
  
  // PLAY GAME: Calls each individual turn and ends the game when needed
  public static void playGame(Scanner scan, Deck deck, Player[] players)  throws InterruptedException
  {
    int playing = 0; // Will be used to check whether or not to move onto a new turn, or end the game because all players have won
    for(int i=0; i<players.length; i++) { // Check every player and CPU
      if(!players[i].won) // If any of them haven't won yet...
        playing++; // Add to the counter
    } // End Check Game Over
    if(playing <= 1) // If there is only one player or less in the game...
      gameOver(scan, players); // Then end the game
    else // If the game is still running, play the next turn
    {
      // Run a different method for player's and CPU's based on the boolean used in initialization
      if(players[deck.turn].player)
        playerTurn(scan, deck, players);
      else
        cpuTurn(scan, deck, players);
    }
  } // Close Play Turns
  
  // PLAYER TURN: Allows a player to play their turn in the game
  public static void playerTurn(Scanner scan, Deck deck, Player[] players) throws InterruptedException
  {
    Player player = players[deck.turn]; // Create a pointer towards the player who is playing right now (the turn variable in the deck indicates which player is playing)
    
    if(!player.won) // If the player hasn't already one yet
    {
      // THESE NEXT TWO LINES ARE OPTIONAL. What they do is they add a bunch of blank lines before a players turn. This is so that when a new player plays on the same computer, they won't see the previous players cards still on screen.
      //for(int i=0;i<100;i++) // Insert 100 blank spaces to clear the previous player's turn
      //  System.out.println("");
      
      System.out.println("\n--- " + player.name + "'s turn! ---");
      
      // If there are multiple players, add this as a measure to prevent the previous player from seeing the next player's cards (it works as a delay, so that as soon as one player finishes their turn, they have time to swap places with the next)
      // (the code is self explanitory)
      int realPlayers = 0;
      for(int i=0;i<players.length;i++)
      {
        if(players[i].player)
          realPlayers++;
      }
      if(realPlayers>1)
      {
        System.out.println("Press the \"enter\" key when you are ready to play.\n");
        scan.nextLine(); // This prevents the next player's cards being spammed onto the screen before players swap places onfront of the computer
      }
      
      // If a Draw card was played on the previous turn, pickup the cards
      if(deck.pickUp!=0) // deck.pickUp signifies how many cards the player must draw
      {
        System.out.println("\nA draw card was played!\nYou must draw " + deck.pickUp + " cards...");
        Thread.sleep(800);
      }
      for(int i=0; i<deck.pickUp; i++)
      {
        player.hand = deck.dealTo(player.hand); // Deal a card to their hand as many times as they are supposed to
        System.out.println("You drew a " + cardName(player.hand[player.hand.length-1])); // Print out the card name
        Thread.sleep(400);
      } // Close For Each Pick Up
      takeTurn(scan, deck, players, player); // Move onto the second half of their turn (for making a move)
    } // End If Still Playing
    else // If the Player has already won...
    {
      // Move on to the next player's turn
      deck.turn=nextTurn(players, deck.turn, deck.reversed); // (increase turn counter)
      playGame(scan, deck, players); // (call the method to call the next turn)
    }
  } // Close Player Turn
  
  // TAKE TURN: The second half of a player's turn where they make a decision on what card to play.
  // This is a separate method so that if they enter an invalid turn action, I can just repeat this method without the first portion of their turn.
  public static void takeTurn(Scanner scan, Deck deck, Player[] players, Player player) throws InterruptedException
  {
    System.out.println("\nCard in play: " + cardName(deck.trash[deck.trash.length-1]));
    Thread.sleep(400);
    
    System.out.println("\nYOUR HAND CONTAINS:");
    Thread.sleep(400);
    
    for(int i=0; i<player.hand.length; i++) // Print out their full hand with option numbers
    {
      System.out.println((i+1) + " - " + cardName(player.hand[i]));
      Thread.sleep(200);
    }
    
    System.out.println("DRAW - Draw a new card from the deck and end your turn");
    
    System.out.println("\nWhat would like to do? (type \"HELP\" for instructions)");
    if(scan.hasNextInt())
    {
      int action = scan.nextInt(); // Store any integer entries
      scan.nextLine(); // Clear Scanner
      if(!(action<=0) && !(action > player.hand.length)) // If the number represents an action for a card in their hand
      {
        Card card = player.hand[action-1]; // Make a pointer to the card they are trying to play
        if(playable(deck.trash, card)) // If the card can be played (same colour, same rank, or wild)
        {
          // Special Plays //
          // These methods will also print out a message based on the action they performed //
          deck.turn = specialPlay(players, card, deck.turn, deck.reversed); // Skip turn if needed, or do nothing
          deck = specialPlay(deck, card); // Find out if cards should be picked on the next turn if a draw card was played
          card = specialPlay(scan, card); // Change the game colour if a wild cards is used
          deck.reversed = specialPlay(deck.reversed, card); // Reverse the turn order if needed, or do nothing
          // (yay method overloading!)
          
          if(card.rank < 10) // If NOT a special card
            System.out.println("\nYou played a " + cardName(player.hand[action-1]) + "!"); // Print out the card they played
          
          player.hand = deck.trash(player.hand, action-1); // Play their card by moving it onto the top of the trash pile
    
          if(player.hand.length <= 0) // If they have no more cards...
          {
            player.won = true; // They have won
            System.out.println(player.name + " has no more cards left...\nYOU HAVE WON THE GAME!");
            Thread.sleep(1600);
          } // End If Won
          
          // Go to the next player's turn
          Thread.sleep(1200);
          deck.turn=nextTurn(players, deck.turn, deck.reversed);
          playGame(scan, deck, players);
        }
        else // If the card they tried to play can not be played (not same suit, or same rank, or a wild card)...
        { 
          System.out.println("\nSorry!\nThat card isn't playable :(\nPlease try again...");
          Thread.sleep(1200);
          takeTurn(scan, deck, players, player); // Allow them to pick a different action to play
        }
      }
      else // If number is too big/too small...
      {
        System.out.println("\nSorry!\nThat number did not match any of the actions available :(\nPlease try again...");
        Thread.sleep(1200);
        takeTurn(scan, deck, players, player); // Allow them to pick a different action to play
      }
    } // Close If Has Next Int
    else // If text was entered instead of a number
    {
      String reply = scan.nextLine(); // Save their response
      if(reply.equalsIgnoreCase("DRAW") || reply.equalsIgnoreCase("DEAL") || reply.equalsIgnoreCase("CARD") || reply.equalsIgnoreCase("HIT")) // If they asked for a new card
      {
        deck.pickUp=0; // Break the train of Draw cards (this prevents multiple players from having to Draw from the same card play)
        player.hand = deck.dealTo(player.hand); // Deal a new card to their hand
        System.out.println("\nYou drew a " + cardName(player.hand[player.hand.length-1]));
        Thread.sleep(1200);
        deck.turn=nextTurn(players, deck.turn, deck.reversed); // Next player's turn
        playGame(scan, deck, players);
      }
      else if(reply.equalsIgnoreCase("HELP")) // Print out the help options if asked for
      {
        // Printing Instructions //
        System.out.println("\n- INSTRUCTIONS -\n");
        System.out.println("Type the number left of the \"-\" (dash) to play a card, or type \"draw\" to get a new card from the pile.");
        System.out.println("You must press the \"enter\" key after you have typed the action you would like to play.");
        System.out.println("The objective of the game is to use up all of your cards.");
        System.out.println("You can only play cards of the same colour or rank as the card in play, with the exception of wild cards.");
        System.out.println("Wild cards can be played on any colour, and allow you to change the colour in play.");
        
        System.out.println("\nPress the \"enter\" key on your keyboard when you are ready to play your turn.");
        scan.nextLine();
        takeTurn(scan, deck, players, player); // Allow them to pick a new action when they're ready
      }
      else // If their answer was an invalid string...
      {
        System.out.println("\nSorry!\nThat entry did not match any of the actions available :(\nPlease try again...");
        Thread.sleep(1200);
        takeTurn(scan, deck, players, player); // Allow them to pick a different action to play
      }
    } // Close if Text is Entered
  } // End Take Turn
  
  // CPU TURN: Will allow a CPU to take their turn in the game
  public static void cpuTurn(Scanner scan, Deck deck, Player[] players) throws InterruptedException
  {
    Player cpu = players[deck.turn]; // Create a pointer to the CPU
    
    if(!cpu.won) // Only play this turn if the CPU hasn't won already
    {
      System.out.println("\n- " + cpu.name + " will now play their turn. -");
      Thread.sleep(400);
      
      if(deck.pickUp>0) // Pick Up cards if a Draw Card was used (the same as for a player, except it doesn't show the cards)
      {
        System.out.println("\nA draw card was played.");
        Thread.sleep(400);
        System.out.println("The computer picked up " + deck.pickUp + " cards.");
        for(int i=0;i<deck.pickUp;i++)
          cpu.hand = deck.dealTo(cpu.hand);
        Thread.sleep(1200);
      } // End Pick Up From Draw Card for CPU
      
      System.out.println("\n" + cpu.name + " is thinking of a move...");
      Thread.sleep(1200); // Artificial delay to make the game seem cooler than it is
      
      // CARD SELECTION FOR CPU MOVE // 
      Random random = new Random(); // Create Random object
      int selection = 0; // The index # of the card we're inspecting
      Card card = new Card(0,0); // A reference to the card we're inspecting (initialize as a blank card)
      boolean chosen = false; // Indicates whether or not a card has been selected for the CPU to play
      int loops = 0; // Represents the number of card selection attempts (loops)
      
      while(loops<cpu.hand.length*10 && chosen == false) // Loops until a card is chosen, or we find out there are no playable cards (using the hand length gives no bias to smaller or larger hands)
      {
        loops++; // Increase counter
        if(cpu.hand.length!=1)
          selection = random.nextInt(cpu.hand.length-1); // Select a random card to inspect from their hand
        else
          selection = 0; // The Random object throws an error if the number is not >=1, so when there is only one card, we don't want to run it with the number 0.
        card = cpu.hand[selection]; // Update the pointer to match our current card
        
        // Greatly increases the likelihood of playing a "Draw # Card" when they can be stacked on a previously played one
        if(loops<=cpu.hand.length) // For the first (handsize) loops (to prevent 100% chance of use, and stacking an unbearable amount of draw cards)...
        {
          // Only accept the card if it is a Draw # Card being stacked on a previously used Draw # Card
          if(deck.trash[deck.trash.length-1].rank == 10 || deck.trash[deck.trash.length-1].rank == 14)
          {
            if((card.rank == 10 || card.rank == 14) && playable(deck.trash, card) && (random.nextInt(3) > 0)) // The extra random nextInt check makes the card have a 2/3 chance of being played even if it's valid
              chosen = true;
          }
        } // Close If Stackable Draw Card
        else // After the first (handsize) loops
        {
          // Avoid using wild cards unless they are the only cards that are playable.
          if(loops<cpu.hand.length*7) // Wait til the last (handsize * 3) loops to even consider using wild cards
          {
            if(playable(deck.trash, card) && card.suit!=4)
              chosen = true;
          }
          else
          {
            if(playable(deck.trash, card))
              chosen = true;
          }
        } // Close After Trying Stackable Draw Cards
      } // End Card Selection (While Loop)
      
      // PERFORM CPU MOVE //
      if(!chosen) // If no playable card was found
      {
        System.out.println("There are no cards that " + cpu.name + " can play :(\nThe CPU drew a card.");
        deck.pickUp=0;
        cpu.hand = deck.dealTo(cpu.hand);
        Thread.sleep(400);
      }
      else // If a card was used
      {
        System.out.println(cpu.name + " played a " + cardName(card) + "."); // Print the card name first, then perform the actions (so that if we need to add extra lines onto the end, we can)
        Thread.sleep(1200);
        
        // If a Draw Card is played add to the count, otherwise reset it
        if(card.rank==14)
          deck.pickUp+=4;
        else if(card.rank==10)
          deck.pickUp+=2;
        else
          deck.pickUp=0;
        
        // If a Wild Card is played, use an algorithm to pick a new colour
        if(card.rank==13 || card.rank==14)
        {
          // Count the number of each colour of card in their hand
          int[] count = {0,0,0,0,0};
          for(int i=0;i<cpu.hand.length;i++)
            count[cpu.hand[i].suit]++;
          
          // Find largest quantity
          int max = 0;
          for(int i = 1; i < 4; i++) // For all suits (5th wild card suit is unnecessary)
          {
            if (count[i] > count[i-1])
              max = i; // (I don't care for ties)
          } // Close For
          
          card.suit = max; // Change the wild card suit to the one the CPU has the most cards of
          System.out.println("The colour was changed to " + getColour(card.suit) + ".");
          Thread.sleep(800);
        } // Close Wild Card
        
        if(card.rank==11) // If a skip turn card was played
        {
          deck.turn = nextTurn(players, deck.turn, deck.reversed);
          System.out.println(players[deck.turn].name + "'s turn was skipped.");
          Thread.sleep(800);
        } // Close Skip Turn Card
        
        if(card.rank==12) // Reverse card
          deck.reversed = !deck.reversed; // Change the boolean that signifies whether or not the playing order is reversed
        
        cpu.hand = deck.trash(cpu.hand, selection); // Move the card onto the top of the trash pile now
      } // Close Play Card
      
      if(cpu.hand.length <= 0) // If the CPU has no more cards...
      {
        cpu.won = true; // They have won
        System.out.println("\n" + cpu.name + " has no more cards left...\nTHEY HAVE WON THE GAME!");
        Thread.sleep(1600);
      } // End If Won
    } // End If Still Playing
    
    // Next player's turn
    deck.turn = nextTurn(players, deck.turn, deck.reversed);
    playGame(scan, deck, players);
  } // Close CPU Turn
  
  // ----------- PLEASE READ THIS ----------- ///
  // Okayyyy, so I used method overloading for all the special card actions,
  // But the thing is I coded the messages and actions in a way that prevents them from
  // From being used for CPUs, they only work for the player.
  // If I was smart I would have coded the methods in a generic way (similar to in the CPU turn)
  // And called them for both the player's and the CPUs.
  // But the time needed to change them isn't worth it for me at the moment,
  // So I'm sorry they are slightly innefficient.
  // Kelvin out :)
  
  // SPECIAL PLAY - SKIP TURN: If a skip card is played, add to the turn counter so the next player's turnwill be skipped
  public static int specialPlay(Player[] players, Card card, int turn, boolean reversed) throws InterruptedException
  {
    if(card.rank == 11) // If skip turn card
    {
      turn = nextTurn(players, turn, reversed);
      System.out.println("\nYou skipped " + players[turn].name + "'s turn!");
      Thread.sleep(800);
    }
    return turn;
  } // Close Special Play - Skip Turn
  
  // SPECIAL PLAY - DRAW CARDS: If a Draw Card was used, pick up that # of cards from the stock pile
  public static Deck specialPlay(Deck deck, Card card) // If draw card
  {
    // If a Draw card isn't used, then reset the counter, otherwise add the right amount to the counter
    if(card.rank == 14)
      deck.pickUp += 4;
    else if(card.rank == 10)
      deck.pickUp += 2;
    else
      deck.pickUp = 0;
    if(deck.pickUp!=0)
      System.out.println("\nThe next player must draw " + deck.pickUp + " cards!");
    return deck;
  } // Close Special Play - Draw Cards
  
  // SPECIAL PLAY - WILD CARD: Allow the player to choose a new playing colour
  public static Card specialPlay(Scanner scan, Card card)
  {
    if(card.rank == 14 || card.rank == 13) // If Wild Card
    {
      String choice = "";
      while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) // Keep repeating until they enter a valid number
      {
        System.out.println("\nWhat colour would you like to put into play?\n1 - RED\n2 - GREEN\n3 - YELLOW\n4 - BLUE");
        choice = scan.nextLine();
        if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4"))
        {
          card.suit = Integer.parseInt(choice)-1; // Change the Wild Card's suit to the appropriate colour
          System.out.println("\nThe colour was changed to " + getColour(card.suit) + ".");
        }
        else
          System.out.println("\nInvalid option.\nPlease try again...");
      }
    }
    return card;
  } // Close Special Play - Wild Card
  
  // SPECIAL PLAY - REVERSE ORDER: If a reverse card was played, reverse the playing order
  public static boolean specialPlay(boolean reversed, Card card)
  {
    if(card.rank == 12) // If reverse card
    {
      reversed=!reversed; // Flip value
      System.out.println("\nYou reversed the playing order!");
    }
    return reversed;
  } // Close Special Play - Reverse Order
  
  // GAME OVER: When there aren't enough players left to play the game, allow them to restart or exit
  public static void gameOver(Scanner scan, Player[] players) throws InterruptedException
  {
    System.out.println("\n--- GAME OVER ---\n\nWould you like to play again?");
    String answer = scan.nextLine();
    if(answer.equalsIgnoreCase("YES") || answer.equalsIgnoreCase("Y"))
      newGame();
    else if(answer.equalsIgnoreCase("NO") || answer.equalsIgnoreCase("N"))
      System.exit(1);
  } // Close Game Over
  
  // NEXT TURN: Adds/Subtracts from the turn counter, depending on whether or not the play order is reversed
  public static int nextTurn(Player[] players, int turn, boolean reversed)
  {
    // Add if not reversed, subtract if reversed
    if(!reversed)
      turn++;
    else
      turn--;
    
    if(turn>=players.length) // If we have exceeded the final players turn,
      turn=turn-players.length; // Go back to the first player's turn.
    if(turn<0) // If the final player is reached on reverse,
      turn=players.length+turn; // Go back to the first player in reverse.
    
    return turn;
  } // Close Next Turn
  
  // PLAYABLE (Utility Method): Checks whether or not a card is valid to play onto the pile
  public static boolean playable(Card[] pile, Card card)
  {
    if(pile[pile.length-1].suit == card.suit || pile[pile.length-1].rank == card.rank || card.suit == 4) // If the card has the same suit, same rank, or it is a wild card, then the card is playable
      return true;
    else
      return false;
  } // Close Playable
  
  // CARD NAME (Utility Method): Returns the Card's Display name
  public static String cardName(Card card)
  {
    if(card.rank==14)
      return getColour(card.suit) + " " + "Draw Four Card";
    else if(card.rank==13)
      return getColour(card.suit) + " " + "Wild Card";
    else if(card.rank==12)
      return getColour(card.suit) + " " + "Reverse Card";
    else if(card.rank==11)
      return getColour(card.suit) + " " + "Skip Turn";
    else if(card.rank==10)
      return getColour(card.suit) + " " + "Draw Two Card";
    else
      return getColour(card.suit) + " " + card.rank; // Regular Card
  } // Close Card Name
  
  // GET COLOUR (Utility Method): Returns the display word for the colour of any card, given the suit #
  public static String getColour(int suit)
  {
    if(suit==0)
      return "RED";
    else if(suit==1)
      return "GREEN";
    else if(suit==2)
      return "YELLOW";
    else if(suit==3)
      return "BLUE";
    else if(suit==4)
      return "WILD";
    else
      return "INVALID COLOUR";
  } // Close Get Colour
} // Close Class