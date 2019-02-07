public class Card
{
  int suit; // Card colour (0: RED, 1: GREEN, 2: YELLOW, 3: BLUE, 4: WILD - a wild card that hasn't been played yet)
  int rank; // The rank of the card (0-9 - Regular Cards, 10 - Draw Two, 11 - Skip Turn, 12 - Reverse, 13 - Wild, 14 - Wild Draw Four)
  
  public Card(int suit, int rank)
  {
    // Initialize Cards
    this.suit = suit;
    this.rank = rank;
  }
}