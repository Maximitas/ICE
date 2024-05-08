import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        FileIO file = new FileIO();
        file.loadPokemonFromFile("Data\\Pokemon.csv", pokemons);


        UserInterface  userInterface = new UserInterface();
        userInterface.userOptions();
    }
}