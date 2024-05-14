import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
Explore explore = new Explore();
Player player = new Player();
CombatInterface combatInterface = new CombatInterface();
//combatInterface.combatOptionsDarkrai(new Player());2
       // explore.dialogOldman(player);

        Music music = new Music();
       // Music.playMusic("""
       //        src/Pokémon-Theme-Song.wav""");
       //Music.playMusic("src/pokemon-battle.wav");
        Music.playMusicStart("src/vN8GiDHbO4.wav");

       // FileIO file = new FileIO();
       // file.loadPokemonFromFile("Data\\Pokemon.csv");

        UserInterface userInterface = new UserInterface();
        userInterface.startMenu();
      //  explore.gatesOfPokemon(player);
    }


}