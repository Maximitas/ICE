import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
Explore explore = new Explore();
Player player = new Player();
CombatInterface combatInterface = new CombatInterface();
//combatInterface.combatOptionsDarkrai(new Player());
       // explore.dialogOldman(player);

        Music music = new Music();
       // Music.playMusic("""
       //        src/Pokémon-Theme-Song.wav""");
       // Music.playMusic("src/pokemon-battle.wav");
        Music.playMusicStart("src/vN8GiDHbO4.wav");

        UserInterface userInterface = new UserInterface();
        userInterface.startMenu();

    }


}