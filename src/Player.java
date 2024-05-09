import java.util.ArrayList;

public class Player {

    TextUI textUI = new TextUI();

    private ArrayList<Pokemon> pokeDex;
    private ArrayList<Item> bag;
    private ArrayList<String> playerInfo;
    private ArrayList<Pokemon> pokemonParty;
    private int wallet = 1000;
    private String name;


    public Player() {
        this.name = name;
        this.wallet = wallet;
        this.pokeDex = new ArrayList<>();
        this.bag = new ArrayList<>();
        this.playerInfo = new ArrayList<>();
        this.pokemonParty = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void addPokemon(Pokemon pokemon) {
        pokeDex.add(pokemon);
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokeDex;
    }

    public ArrayList<Pokemon> getPokemonParty() {
        FileIO file = new FileIO();
        this.pokemonParty = file.loadPokemonFromFile("Data/PlayerPokemons.csv");
        return pokemonParty;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void addItemToBag(Item item) {
        FileIO file = new FileIO();
        file.saveItemToBag("Data/Bag.csv", item);
        bag.add(item);

        textUI.displayMsg(item.getName() + " has been added to your bag.");
    }

    public ArrayList<Item> getBag() {
        return bag;
    }
    public void addFunds(int amount) {
        wallet += amount;
    }

    public void removeFunds(int amount) {
        wallet -= amount;
    }

    public boolean removeItemFromBag(Item item) {
        return bag.remove(item);
    }
}