import java.io.File;
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
            ui.displayMsg("Oak: No enemy Pokémon available.");
            return;
        }
        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        combatOptions(player, enemyPokemon);
    }
    Pokemon getRandomPokemonList(int minLvl, int maxLvl) {
        FileIO fileIO = new FileIO();
        ArrayList<Pokemon> allPokemons = fileIO.loadPokemonFromFile("Data/Pokemon.csv");
        ArrayList<Pokemon> lvlFilteredPokemons = new ArrayList<>();

        for (Pokemon pokemon : allPokemons) {
            if (pokemon.getLvl() >= minLvl && pokemon.getLvl() <= maxLvl) {
                lvlFilteredPokemons.add(pokemon);
            }
        }

        if (lvlFilteredPokemons.isEmpty()) {
            ui.displayMsg("Oak: No enemy Pokémon available in the specified level range.");
            return null;
        }

        return lvlFilteredPokemons.get(new Random().nextInt(lvlFilteredPokemons.size()));
    }

    public void combat5to10(Player player) throws InterruptedException {
        int minLvl = 5;
        int maxLvl = 10;
        Pokemon enemyPokemon = getRandomPokemonList(minLvl, maxLvl);

        if (enemyPokemon == null) {
            ui.displayMsg("Oak: No enemy Pokémon available in the specified level range.");
            return;
        }

        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        combatOptions(player, enemyPokemon);
    }
    public void combat10to25(Player player) throws InterruptedException {
        int minLvl = 10;
        int maxLvl = 25;

        Pokemon enemyPokemon = getRandomPokemonList(minLvl, maxLvl);


        if (enemyPokemon == null) {
            ui.displayMsg("Oak: No enemy Pokémon available in the specified level range.");
            return;
        }

        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        combatOptions(player, enemyPokemon);
    }
    public void combat25to50(Player player) throws InterruptedException {
        int minLvl = 25;
        int maxLvl = 50;
        Pokemon enemyPokemon = getRandomPokemonList(minLvl, maxLvl);


        if (enemyPokemon == null) {
            ui.displayMsg("Oak: No enemy Pokémon available in the specified level range.");
            return;
        }
        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        combatOptions(player, enemyPokemon);
    }

    public void combatOptions(Player player, Pokemon enemyPokemon) throws InterruptedException {
        Music.clip.stop();
        Music.playMusic("src/Soundtracks/pokemon-battle.wav");
        boolean battleActive = true;
        while (battleActive) {
            ui.displayMsg("Select your option below:");
            ui.displayMsg("1: Attack\n2: Run\n3: Party\n4: Bag");

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
                    battleActive = bag(enemyPokemon, player);
                    if(!battleActive) {
                        caughtPokemon(player, enemyPokemon);
                    }
                    break;
                default:
                    ui.displayMsg("Oak: That isn't the right input!");
            }
        }
    }


    public void fight(Player player, Pokemon enemyPokemon) throws InterruptedException {
        combat.battleRound(player, enemyPokemon);
    }

    public void caughtPokemon(Player player,Pokemon enemyPokemon) throws InterruptedException {
        Music.clip.stop();
        Music.playMusic("src/Soundtracks/red-and-blue-pokemon-red-and-blue-capture-sound-effect.wav");
        int delay=1000;
        String sentence = (".. .. ... ...!\n");
        for (String s : sentence.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);
        }
        ui.displayMsg("You caught a " + enemyPokemon.getName());
        userInterface.userOptions(player);
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

    public boolean bag(Pokemon enemyPokemon, Player player) {
        FileIO file = new FileIO();
        ArrayList<Item> items = file.readItemsFromBag("Data/Bag.csv");

        displayBag(items);
        boolean pokemonCaught = usePokeball(items, enemyPokemon, player);
        if (!pokemonCaught) {
            file.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", enemyPokemon);
        }
        return pokemonCaught;
    }

    public boolean usePokeball(ArrayList<Item> items, Pokemon enemyPokemon, Player player) {
        FileIO file = new FileIO();
        String option = ui.userInput();
        boolean pokemonCaught = true;
        try {
            if(!option.equals("0")) {
            Item item = items.get(Integer.parseInt(option) - 1);

                if (item.getName().equals("Poke Ball") || item.getName().equals("Master Ball") || item.getName().equals("Ultra Ball") || item.getName().equals("Great Ball")) {
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).equals(item)) {
                            items.remove(i);
                        }
                    }
                } else {
                    ui.displayMsg("That's not a Poke ball");
                }
                switch (item.getName()) {
                    case "Poke Ball":
                        if (random(100) < 40){
                            pokemonCaught = false;
                        } else {
                            ui.displayMsg("You failed to catch " + enemyPokemon.getName() + " using a Poke ball");
                        }
                        break;
                    case "Great Ball":
                        if (random(100) < 60){
                            pokemonCaught = false;
                        } else {
                            ui.displayMsg("You failed to catch " + enemyPokemon.getName() + " using a Great ball");
                        }
                        break;
                    case "Ultra Ball":
                        if (random(100) < 80){
                            pokemonCaught = false;
                        } else {
                            ui.displayMsg("You failed to catch " + enemyPokemon.getName() + " using a Ultra ball");
                        }
                        break;
                    case "Master Ball":
                        pokemonCaught = false;
                        break;
                }
            } else {
                combatOptions(player, enemyPokemon);
            }


        } catch (Exception e) {
            ui.displayMsg("Invalid input");
            usePokeball(items, enemyPokemon, player);
        }
        file.saveItemsToBag("Data/Bag.csv", items);
        return pokemonCaught;
    }

    public void viewBag() {
        FileIO file = new FileIO();
        ArrayList<Item> items = file.readItemsFromBag("Data/Bag.csv");
        if(!items.isEmpty()) {
            for (Item item : items) {
                ui.displayMsg(item.getName() + ", " + item.getPrice());
            }
            ui.displayMsg("\n");
        } else {
            ui.displayMsg("Your bag is empty");
        }
    }

    public void displayBag(ArrayList<Item> items) {

        ui.displayMsg("Choose an item below. If no items are available, press '0' to leave bag");
        for (int i = 0; i < items.size(); i++) {
            ui.displayMsg(i+1 + ": " + items.get(i));
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
        try{
            int option = Integer.parseInt(ui.userInput())-1;
        if(option < 0 || option >= playerPokemonList.size()){

        }

        Collections.swap(playerPokemonList, 0, option);
        fileIO.savePokemonData("Data/PlayerPokemons.csv", playerPokemonList);
        combat.setPrimaryPlayerPokemon(playerPokemonList.getFirst());
        ui.displayMsg(combat.getPrimaryPlayerPokemon().getName() + " has been switched out with " + playerPokemonList.get(option).getName());
        user.userOptions(player);
    }

        catch(Exception e){
            ui.displayMsg("Oak: That isn't the right input!");
            changePokemonInParty(player);

        }
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
                    ui.displayMsg("Oak: That isn't the right input!");
                }
            } catch (NumberFormatException e) {
                ui.displayMsg("Oak: That isn't the right input!");
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
            ui.displayMsg("Oak: No enemy Pokémon available.");
            return;
        }
        combatOptions(player, enemyPokemon);
    }

    public void combatOptionsDarkrai(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonDarkrai();
        if (enemyPokemon == null) {
            ui.displayMsg("Oak: No enemy Pokémon available.");
            return;
        }

        combatOptions(player, enemyPokemon);
    }

    public void combatOptionsGyarados(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonGyarados();
        if (enemyPokemon == null) {
            ui.displayMsg("Oak: No enemy Pokémon available.");
            return;
        }
        combatOptions(player, enemyPokemon);

    }
}