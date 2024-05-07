import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        ArrayList<Item> playerBag = new ArrayList<>();
        ArrayList<Pokemon> playerPokemons = new ArrayList<>();

        FileIO file = new FileIO();

        // file.loadPokemonFromFile("Data\\Pokemon.csv", pokemons);

        // file.saveItemsToBag("Data/Bag.csv", playerBag);
        // file.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", playerPokemons);

        // file.readItemsFromBag("Data/Bag.csv", playerBag);
        // file.readPokemonFromPlayerPokemons("Data/PlayerPokemons.csv", playerPokemons);

        UserInterface  userInterface = new UserInterface();
        userInterface.userOptions();
    }
}