import java.util.Random;

public class CombatInterface {

    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private UserInterface user = new UserInterface();
    private Combat combat = new Combat();
    private Player player = new Player();

    public void combatOptions() throws InterruptedException {

        ui.displayMsg("Select your option below:");
        ui.displayMsg("1: Fight\n2: Run\n3: Switch Pokémon\n4: Bag");

        boolean whileKey = false;

        while (!whileKey) {

            String option = ui.userInput();
            switch (option) {
                case "1":
                    fight();
                    whileKey = true;
                    break;
                case "2":
                    run();
                    whileKey = true;
                    break;
                case "3":
                    switchPokemon();
                    whileKey = true;
                    break;
                case "4":
                    bag();
                    whileKey = true;
                    break;
                default:
                    ui.displayMsg("Invalid input, please try again");

            }
        }
    }


    public void run() throws InterruptedException {
        double rand = random(1);
        if (rand < 0.7) {
            ui.displayMsg("You successfully ran away!");
            user.userOptions();
        } else {
            ui.displayMsg("What the dog doing?!");
            combatOptions();

        }
    }

    public void fight() {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        combat.battleRound(playerOne, playerTwo);

    }

    public void bag() {

    }

    public void switchPokemon() {

    }

    public void catchPokemon() {

    }

    public int random(int k) {
        Random random = new Random();
        return random.nextInt(k);
    }

}
