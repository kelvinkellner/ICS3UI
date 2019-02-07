import java.util.*;

public class Deck
{
  Card[] pile; // The stock pile which cards are drawn from
  Card[] trash; // The trash card where cards are dealt onto
  
  int exchanges; // The number of shuffle exchanges to use when shuffling a deck
  int handSize; // The size of any new hands to be created
  
  // These variables are used in game and affect the players
  int pickUp; // How many cards the next player must pick up
  int turn; // The current player's turn number (index # in players array)
  boolean reversed; // Is the play order reversed?
  
  public Deck()
  {
    // Initialize deck
    this.exchanges = 100000;
    this.handSize = 7;
    
    this.pickUp = 0;
    this.turn = 0;
    this.reversed = false;
    
    this.pile = freshDeck();
    this.trash = new Card[0];
  }
  
  public Card[] freshDeck() // Creates a brand new deck to deal cards from
  {
    // Deck setup info taken from: http://play-k.kaserver5.org/Uno.html
    Card[] pile = new Card[0]; // Create an empty pile
    for(int suit = 0; suit < 4; suit++) // For each colour (4 times)
    {
      pile = newCard(pile, suit, 0); // Add a 0 card to the deck for each colour
      for(int loop=0; loop<2; loop++) // Extra loops, since there are more of some cards (2 times)
      {
        for(int rank = 1; rank <= 9; rank++) // Add 2 of each card from 1-9 for each colour
          pile = newCard(pile, suit, rank);
        
        // SPECIAL CARDS //
        pile = newCard(pile, suit, 10); // Draw two cards
        pile = newCard(pile, suit, 11); // Skip turn cards
        pile = newCard(pile, suit, 12); // Reverse cards
      } // End Extra Loop
      pile = newCard(pile, 4, 13); // Add 4 Wild cards (Wild cards use an extra suit of value 4 when they haven't been played)
      pile = newCard(pile, 4, 14); // Add 4 Wild draw four cards
    } // End Suit
    
    pile = shuffle(pile); // Shuffle the newly created deck
    return pile;
  } // Close Fresh Deck
  
  public Card[] shuffle(Card[] pile)
  {
    Random random = new Random();
    for(int i = 0; i < this.exchanges; i++)
    {
      // Continue to select and swap 2 random cards many many times
      int c1 = 0;
      int c2 = 0;
      if(pile.length>0) // Prevents errors from being thrown when the pile only contains 1 card
      {
        c1 = random.nextInt(pile.length-1);
        c2 = random.nextInt(pile.length-1);
      }
      Card temp = pile[c1];
      pile[c1] = pile[c2];
      pile[c2] = temp;
    } // End Exchange
    return pile;
  } // Close Shuffle
  
  public Card[] newHand()
  {
    Card[] hand = new Card[this.handSize]; // Create a new hand of the correct size
    for(int i = 0; i < hand.length; i++)
    {
      if(this.pile.length<1) // This allows multiple UNO decks to be added into the initialization of a game, allowing unlimited players.
        this.pile = freshDeck();
      
      hand[i] = this.pile[0]; // Deal to this hand
      this.pile = removeCard(this.pile, 0); // Then remove the card from the deck
    }
    return hand;
  } // Close New Hand
  
  public Card[] newCard(Card[] pile, int suit, int rank)
  {
    Card[] newPile = new Card[pile.length+1]; // Create a new pile 1 size bigger
    for(int i=0; i<pile.length; i++)
      newPile[i] = pile[i]; // Duplicate the old pile to the new one
    Card card = new Card(suit, rank); // Create a new card
    newPile[pile.length] = card; // Add the new card onto the end of the new pile
    return newPile;
  } // Close New Card
  
  public Card[] trash(Card[] pile, int place)
  {
    this.trash = newCard(this.trash, pile[place].suit, pile[place].rank); // Clone the card to the trash pile
    pile = removeCard(pile, place); // Remove the card from the deck
    return pile; // Update the pile
  } // Close Trash Card
  
  public Card[] removeCard(Card[] pile, int place)
  {
    Card[] newPile = new Card[pile.length-1]; // Create a new pile 1 size smaller
    for(int i = 0; i < place; i++) // Duplicate all cards before the Card's Place
      newPile[i] = pile[i];
    for(int i = place; i < newPile.length; i++) // Duplicate all cards after Card's Place (overwriting the place)
      newPile[i] = pile[i+1];
    return newPile; // Update pile
  } // Close Remove Card
  
  public Card[] dealTo(Card[] hand)
  {
    if(this.pile.length<=0 && this.trash.length<=1) // If there are no cards to use in the deck or in the trash
      System.out.println("\nThe stock pile is empty.\nA card could not be drawn.");
    else
    {
      if(this.pile.length<=0) // If the stock pile is empty
        recycle(); // Grab all the cards from the trash and redeal them into the stock pile
      hand = newCard(hand, this.pile[0].suit, this.pile[0].rank); // Deal a new card into the hand
      this.pile = removeCard(this.pile, 0); // And remove it from the stock pile
    }
    return hand;
  } // Close Deal To
  
  public void recycle()
  {
    Card top = new Card(this.trash[this.trash.length-1].suit, this.trash[this.trash.length-1].rank); // Grab the card from the top of the trash pile, the one in play
    this.trash = removeCard(this.trash, this.trash.length-1); // Remove that card from the trash pile
    System.out.println("\nThe stock pile is empty.\nWe're recycling the trash pile!");
    
    for(int i=0;i<this.trash.length;i++) { // Reset the colour of wild cards so that they can be used again properly
      if(this.trash[i].rank == 13 || this.trash[i].rank == 14)
        this.trash[i].suit = 4; }
    
    this.pile = shuffle(this.trash); // Shuffle the trash and place it onto the deck
    this.trash = new Card[1]; // Clear the trash
    this.trash[0] = top; // Add the top card back onto the trash pile so that the game can continue
  } // Close Recycle
}