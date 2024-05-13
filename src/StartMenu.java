import java.util.ArrayList;

public class StartMenu {
    FileIO file = new FileIO();
    TextUI ui = new TextUI();


    public void continueGame() throws InterruptedException {
        UserInterface userInterface = new UserInterface();
        ArrayList<String> playerData = file.readPlayerData("Data/PlayerData.csv");
        Player player = new Player();

        if (!playerData.isEmpty()) {
            player.setName(playerData.get(0));
            player.setWallet(Integer.parseInt(playerData.get(1)));

            userInterface.userOptions(player);
        } else {
            userInterface.startMenuOptions();
        }

    }

    public void savePlayer(Player player) {
        int balance = player.getWallet();
        String name = player.getName();

        file.savePlayerData("Data/PlayerData.csv", name, balance);
    }

    public void newGame() throws InterruptedException {
        UserInterface userInterface = new UserInterface();
        file.clearPlayerPokemonFile("Data/PlayerPokemons.csv");
        file.clearBagFile("Data/Bag.csv");
        file.clearPlayerFile("Data/PlayerData.csv");

        Player player = choosePlayerName();
        chooseStarterPokemon();

        userInterface.userOptions(player);
    }

    public Player choosePlayerName() {
        Player player = new Player();
        ui.displayMsg("Please type your name");
        String name = ui.userInput();
        player.setName(name);

        ui.displayMsg("You have chosen " + player.getName());
        return player;
    }

    public void chooseStarterPokemon() {
        ArrayList<Pokemon> allPokemons = file.loadPokemonFromFile("Data/Pokemon.csv");

        //Souts starting pokemons
        ui.displayMsg("Choose the pokemon you want.");
        ui.displayMsg("1: " + allPokemons.get(0).getName());
        ui.displayMsg("2: " + allPokemons.get(3).getName());
        ui.displayMsg("3: " + allPokemons.get(6).getName());
        ui.displayMsg("4: " + allPokemons.get(24).getName());

        int userInput = translatePlayerChoiceToPokeindex();

        Pokemon playerPokemon = allPokemons.get(userInput);

        file.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", playerPokemon);

    }

    public int translatePlayerChoiceToPokeindex() {
        do {
            String playerChoice = String.valueOf(ui.userInput());
            switch (playerChoice) {
                case "1":
                    return 0;
                case "2":
                    return 3;
                case "3":
                    return 6;
                case "4":
                    return 24;
                default:
                    ui.displayMsg("Invalid input. Please choose again.");
            }


        }
        while (true);
    }
}

