import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FileIO file = new FileIO();
        file.loadPokemonFromFile("Data\\Pokemon.csv");

        UserInterface userInterface = new UserInterface();

        userInterface.startMenu();
    }
}