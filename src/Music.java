import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    public static void main(String[] args) {
        playMusic("src/Pokémon-Theme-Song.wav"); // Replace with the path to your sound file
    }

    public static void playMusic(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

