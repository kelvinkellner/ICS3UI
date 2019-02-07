public class Move
{
  String name;
  String type;
  String desc;
  int pow;
  int acc;

  Move(String name, String type, String desc, int pow, int acc)
  {
    this.name = name;
    this.type = type;
    this.desc = desc;
    this.pow = pow;
    this.acc = acc;
  }
  
  /*
  ((2A/5+2)*B*C)/D)/50)+2)*X)*Y/10)*Z)/255

  A = attacker's Level
  B = attacker's Attack or Special
  C = attack Power
  D = defender's Defense or Special
  X = same-Type attack bonus (1 or 1.5)
  Y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
  Z = a random number between 217 and 255
  */
}