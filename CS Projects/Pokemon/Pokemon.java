public class Pokemon
{
  String name;
  int level;
  int health;
  String type;
  Move[] moves;
  
  Pokemon(String name, int level, int health, String type, Move[] moves)
  {
    this.name = name;
    this.level = level;
    this.health = health;
    this.type = type;
    this.moves = moves;
  }
}