import java.util.ArrayList;

public class StartMenu {


    public void newGame(){
        Player user = new Player();

    }
    public void chooseStarterPokemon(){
        FileIO file = new FileIO();
        TextUI ui = new TextUI();
        ArrayList<Pokemon> allPokemons = file.loadPokemonFromFile("Data/Pokemon.csv");
        //Souts starting pokemons
        ui.displayMsg("Choose the pokemon you want.");
        ui.displayMsg("1: " + allPokemons.get(0).getName());
        ui.displayMsg("2: " + allPokemons.get(3).getName());
        ui.displayMsg("3: " + allPokemons.get(6).getName());
        ui.displayMsg("4: " + allPokemons.get(24).getName());

        int userInput = translatePokeindex(ui.userInputInt());

        Pokemon playerPokemon = allPokemons.get(userInput);

        file.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", playerPokemon);

    }
    public int translatePokeindex(int playerChoice){
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
