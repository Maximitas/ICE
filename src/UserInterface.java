import java.io.File;

public class UserInterface {
    TextUI UI = new TextUI();


    public void userOptions(Player player) throws InterruptedException {
        StartMenu startMenu = new StartMenu();
        CombatInterface combat = new CombatInterface();
        Explore explore = new Explore();
        UI.displayMsg("Select your option below:");
        UI.displayMsg("1: Explore\n2: Town\n3: Player\n4: Find Pokémon\n5: Save\n6: Exit Game");

        boolean whileKey = false;

        while(!whileKey){

            String option = UI.userInput();
            switch(option){
                case "1":
                    explore.explore(player);
                whileKey = true;
                    break;
                case "2"://Town
                    townOrPokeCenter(player);
                    whileKey = true;
                    break;
                case "3"://player //todo Player info
                    combat.combatOptions(player);
                    whileKey = true;
                    break;
                    case "4":
                    combat.combatOptions(player);
                    whileKey = true;
                    break;
                case "5": //Save game
                    whileKey = true;
                    startMenu.savePlayer(player);
                    break;
                case "6": //Exit game
                    whileKey = true;
                default: UI.displayMsg("Invalid input, please try again");

            }
        }
    }

    public void pokeMartOptions(Player player) throws InterruptedException {
        Town town = new Town();
        UI.displayMsg("Welcome to the PokéMart!");
        UI.displayMsg("1: Buy items\n2: Sell items\n3: to exit ");

        boolean whileKey = false;

        while(!whileKey){

            String option = UI.userInput();
            switch(option) {
                case "1":
                    town.buy(player);
                    break;
                case "2":
                    town.sell(player);
                    whileKey = true;
                    break;
                case "3":
                    UI.displayMsg("Thank you for visiting the PokéMart!");
                    userOptions(player);
                    whileKey = true;
                    break;
                default:
                    UI.displayMsg("Invalid input, please try again");
            }


        }
    }

    public void townOrPokeCenter(Player player) throws InterruptedException {
        Town town = new Town();
        System.out.println("You are now in the town..\nWhat place do you want to visit?");
        System.out.println("1: PokeCenter\n2: PokeMart\n3: Exit");
        switch(UI.userInput()){
            case "1":
                town.pokeCenter(player);
            case "2":
                pokeMartOptions(player);
            case "3":
                userOptions(player);
            default:
                System.out.println("Try again");
                break;

        }

    }

    public void startMenu() throws InterruptedException {
        TextUI ui = new TextUI();
        ui.displayMsg("Welcome to Packetman\n\n");
        ui.displayMsg("Choose \n'1' Continue game\n'2' New game\n'3' Exit");

        startMenuOptions();
    }
    public void startMenuOptions() throws InterruptedException {
        StartMenu menu = new StartMenu();
        switch(UI.userInput()){
            case "1":
                menu.continueGame();
                break;
            case "2":
                menu.newGame();
                break;
            case "3":
                break;
            default: UI.displayMsg("Invalid input, please try again");
            startMenuOptions();

        }
    }


}
