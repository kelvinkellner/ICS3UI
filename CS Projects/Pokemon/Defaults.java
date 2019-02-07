public class Defaults
{
  String[] names;
  String[] moves;
  String[] type;
  String[] desc;
  int[] pow;
  int[] acc;
  
  Defaults()
  {
    String[] names = {"Bulbasaur","Squirtle","Charmander"};
    String[] moves = {"Vine Whip","Tackle","Growth","Mud-Slap","Water Gun","Pound","Work up","Minimize","Ember","Headbutt","Bulk up","Flash"};
    String[] type = {"Grass","Normal","Normal","Normal","Water","Normal","Normal","Normal","Fire","Normal","Normal","Normal"};
    String[] desc = {"Inflicts Damage","Inflicts Damage","Boosts Attack","Boosts Evasiveness","Inflicts Damage","Inflicts Damage","Boosts Attack","Boosts Evasiveness","Inflicts Damage","Inflicts Damage","Boosts Attack","Boosts Evasiveness"};
    int[] pow = {10,15,0,0,10,15,0,0,10,15,0,0};
    int[] acc = {80,100,100,100,80,100,100,100,80,100,100,100};
    this.names = names;
    this.moves = moves;
    this.type = type;
    this.desc = desc;
    this.pow = pow;
    this.acc = acc;
  }
  
  Move[] getMoves(int match)
  {
    Move[] moves = new Move[4];
    for(int i = 0; i < 4; i++)
    {
      int move = (match*4) + i;
      moves[i] = new Move(this.moves[move], this.type[move], this.desc[move], this.pow[move], this.acc[move]);
    }
    return moves;
  }
}
        