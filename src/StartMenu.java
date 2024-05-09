import java.util.ArrayList;

public class StartMenu {
    FileIO file = new FileIO();
    TextUI ui = new TextUI();
    UserInterface userInterface = new UserInterface();




    public void newGame() throws InterruptedException {
        file.clearPlayerPokemonFile("Data/PlayerPokemons.csv");
        file.clearBagFile("Data/Bag.csv");

        Player player = choosePlayerName();
        chooseStarterPokemon();

        userInterface.userOptions(player);
    }

    public Player choosePlayerName(){
        Player player = new Player();
        ui.displayMsg("Please type your name");
        String name = ui.userInput();
        player.setName(name);

        ui.displayMsg("You have chosen " + player.getName());
        return player;
    }

    public void chooseStarterPokemon(){
        ArrayList<Pokemon> allPokemons = file.loadPokemonFromFile("Data/Pokemon.csv");

        //Souts starting pokemons
        ui.displayMsg("Choose the pokemon you want.");
        ui.displayMsg("1: " + allPokemons.get(0).getName());
        ui.displayMsg("2: " + allPokemons.get(3).getName());
        ui.displayMsg("3: " + allPokemons.get(6).getName());
        ui.displayMsg("4: " + allPokemons.get(24).getName());

        int userInput = translatePlayerChoiceToPokeindex(ui.userInputInt());

        Pokemon playerPokemon = allPokemons.get(userInput);

        file.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", playerPokemon);

    }
    public int translatePlayerChoiceToPokeindex(int playerChoice){
        if(playerChoice == 1){
            playerChoice = 0;
        } else if(playerChoice == 2){
            playerChoice = 3;
        } else if(playerChoice == 3){
            playerChoice = 6;
        } else if(playerChoice == 4){
            playerChoice = 24;
        }
        return playerChoice;
    }
}
