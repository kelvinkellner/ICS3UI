public class Player
{
  String name; // The player's display name
  boolean player; // This will be true if this is an actual player, and false if this is a CPU
  boolean won; // Indicates whether or not the player has won the game yet
  
  Card[] hand; // This hand will contain all the players cards
  
  Player(String name, boolean player, Card[] hand)
  {
    // Initialize Player
    this.name = name;
    this.player = player;
    this.hand = hand;
    this.won = false;
  }
}