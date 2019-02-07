import java.util.*;

public class Player
{
  Pokemon[] pokemon;
  
  Player()
  {
    this.pokemon = new Pokemon[0];
  }
  
  Pokemon[] newPokemon(Defaults defaults, Pokemon[] list, String name)
  {
    return addPokemon(list, newPokemon(defaults, name));
  }
  
  Pokemon newPokemon(Defaults defaults, String name)
  {
    int match = -1;
    for(int i = 0; i < defaults.names.length; i++)
    {
      if (name.equalsIgnoreCase(defaults.names[i]))
        match = i;
    }
    
    Random random = new Random();
    int level = random.nextInt(10)+10;
    Pokemon pokemon = new Pokemon(defaults.names[match], level, (29+(level/10)), defaults.type[match], defaults.getMoves(match));
    return pokemon;
  }
  
  Pokemon[] addPokemon(Pokemon[] old, Pokemon pokemon)
  {
    Pokemon[] list = new Pokemon[old.length+1];
    for(int i = 0; i<old.length; i++)
      list[i] = old[i];
    list[old.length] = pokemon;
    return list;
  }
}