import java.util.*;

public class Game
{
  public static void main(String[] args)
  {
    newPlayer();
  }
  
  public static void newPlayer()
  {
    Defaults defaults = new Defaults();
    Scanner scan = new Scanner(System.in);
    Player player = new Player();
    
    System.out.println("\nWelcome to Pokemon!\nWhich pokemon would you like to choose?");
    System.out.println("1 - Bulbasaur\n2 - Squirtle\n3 - Charmander");
    int answer = scan.nextInt();
    scan.nextLine();
    player.pokemon = player.newPokemon(defaults, player.pokemon, defaults.names[answer-1]);
    System.out.println("\nAwesome!\n" + player.pokemon[0].name + " is excited to be your starter pokemon :)");
    scan.close();
  }
}