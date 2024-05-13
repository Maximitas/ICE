import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CombatInterface {

    private TextUI ui = new TextUI();
    private Combat combat = new Combat();
    private UserInterface userInterface = new UserInterface();

    public void combatRandom(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getRandomPokemon();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }
        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        combatOptions(player, enemyPokemon);
    }

    public void combatOptions(Player player, Pokemon enemyPokemon) throws InterruptedException {

        boolean battleActive = true;
        while (battleActive) {
            ui.displayMsg("Select your option below:");
            ui.displayMsg("1: Fight\n2: Run\n3: Switch Pokémon\n4: Bag");

            String option = ui.userInput();
            switch (option) {
                case "1":
                    fight(player, enemyPokemon);
                    if (enemyPokemon.getHp() > 0 && combat.getPrimaryPlayerPokemon().getHp() > 0) {
                        continue;
                    }
                    battleActive = false;
                    break;
                case "2":
                    run(player, enemyPokemon);
                    battleActive = false;
                    break;
                case "3":
                    switchPokemon(player);
                    break;
                case "4":
                    bag(player);
                    break;
                default:
                    ui.displayMsg("Invalid input, please try again.");
            }
        }
    }


    public void fight(Player player, Pokemon enemyPokemon) throws InterruptedException {
        combat.battleRound(player, enemyPokemon);
    }


    public void run(Player player, Pokemon enemyPokemon) throws InterruptedException {
        double rand = random(10);
        if (rand < 7) {
            ui.displayMsg("You successfully ran away!");
            userInterface.userOptions(player);
        } else {
            ui.displayMsg("Couldn't escape!");
            combatOptions(player, enemyPokemon);

        }
    }

    public void bag(Player player) {
        ui.displayMsg("Select an item:");
        ui.displayMsg("Poké Balls\n2Potions");

        String option = ui.userInput();
        switch (option) {
            case "1":

                break;
            case "2":

            default:
                ui.displayMsg("Invalid input, please try again.");
                bag(player);
                break;
        }
    }
    public void changePokemonInParty(Player player) throws InterruptedException {
        FileIO fileIO = new FileIO();
        UserInterface user = new UserInterface();
        ArrayList<Pokemon> playerPokemonList = combat.getPlayerPokemonList();
        ui.displayMsg("Choose a Pokémon to switch to your current party.");
        for (Pokemon pokemon : playerPokemonList) {
            if (pokemon.getHp() > 0) {
                ui.displayMsg(playerPokemonList.indexOf(pokemon) + 1 + ": " + pokemon.getName() + " (Lvl " + pokemon.getLvl() + ") - HP: " + pokemon.getHp());
            }

        }
        int option = ui.userInputInt() - 1;
        if(option < 0 || option >= playerPokemonList.size()){
         ui.displayMsg("Invalid input");
         changePokemonInParty(player);
        }

        Collections.swap(playerPokemonList, 0, option);
        fileIO.savePokemonData("Data/PlayerPokemons.csv", playerPokemonList);
        combat.setPrimaryPlayerPokemon(playerPokemonList.getFirst());
        ui.displayMsg(combat.getPrimaryPlayerPokemon().getName() + " has been switched out with " + playerPokemonList.get(option).getName());
        user.userOptions(player);
    }

    public void switchPokemon(Player player) {
        ArrayList<Pokemon> playerPokemonList = combat.getPlayerPokemonList();
        ui.displayMsg("Choose a Pokémon to switch:");

        for (Pokemon pokemon : playerPokemonList) {
            if (pokemon.getHp() > 0) {
                ui.displayMsg(playerPokemonList.indexOf(pokemon) + 1 + ": " + pokemon.getName() + " (Lvl " + pokemon.getLvl() + ") - HP: " + pokemon.getHp());

            }
        }

        while (true) {
            String input = ui.userInput();
            try {
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < playerPokemonList.size() && playerPokemonList.get(choice).getHp() > 0) {
                    if (playerPokemonList.get(choice) != combat.getPrimaryPlayerPokemon()) {
                        combat.setPrimaryPlayerPokemon(playerPokemonList.get(choice));
                        ui.displayMsg("You switched to " + combat.getPrimaryPlayerPokemon().getName());
                        break;
                    } else {
                        ui.displayMsg("You selected the currently active Pokémon. No change made.");
                        break;
                    }
                } else {
                    ui.displayMsg("Invalid choice, please try again.");
                }
            } catch (NumberFormatException e) {
                ui.displayMsg("Invalid input, please enter a number.");
            }
        }
    }

    public int random(int k) {
        Random random = new Random();
        return random.nextInt(k);
    }

    public void combatOptionsRed(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonMewto();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }
        combatOptions(player, enemyPokemon);
    }

    public void combatOptionsDarkrai(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonDarkrai();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        combatOptions(player, enemyPokemon);
    }

    public void combatOptionsGyarados(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonGyarados();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        combatOptions(player, enemyPokemon);

    }
}