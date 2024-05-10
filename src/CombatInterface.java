import java.util.Random;

public class CombatInterface {

    private TextUI ui = new TextUI();
    private Combat combat = new Combat();
    private UserInterface userInterface = new UserInterface();

    public void combatOptions(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getRandomPokemon();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
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
                    switchPokemon();
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
            ui.displayMsg("What the dog doing?!");
            fight(player, enemyPokemon);

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

    public void switchPokemon() {

    }

    public int random(int k) {
        Random random = new Random();
        return random.nextInt(k);
    }


    public void combatOptionsRed(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonMewTo();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        ui.displayMsg("Red sent out " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        boolean battleActive = true;
        while (battleActive) {
            ui.displayMsg("Select your option below:");
            ui.displayMsg("1: Fight\n2: Switch Pokémon\n3: Bag");

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
                    switchPokemon();
                    break;
                case "3":
                    bag(player);
                    break;
                default:
                    ui.displayMsg("Invalid input, please try again.");
            }
        }
    }
    public void combatOptionsDarkry(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonDarkcry();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        ui.displayMsg(" " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        boolean battleActive = true;
        while (battleActive) {
            ui.displayMsg("Select your option below:");
            ui.displayMsg("1: Fight\n2: Switch Pokémon\n3: Bag");

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
                    switchPokemon();
                    break;
                case "3":
                    bag(player);
                    break;
                default:
                    ui.displayMsg("Invalid input, please try again.");
            }
        }
    }
    public void combatOptionsGyarados(Player player) throws InterruptedException {
        Pokemon enemyPokemon = combat.getSpecificPokemonGyarados();
        if (enemyPokemon == null) {
            ui.displayMsg("No enemy Pokémon available.");
            return;
        }

        ui.displayMsg("A wild " + enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + ") has appeared!");
        boolean battleActive = true;
        while (battleActive) {
            ui.displayMsg("Select your option below:");
            ui.displayMsg("1: Fight\n2: Switch Pokémon\n3: Bag");

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
                    switchPokemon();
                    break;
                case "3":
                    bag(player);
                    break;
                default:
                    ui.displayMsg("Invalid input, please try again.");
            }
        }
    }

}
