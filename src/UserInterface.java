public class UserInterface {

    TextUI UI = new TextUI();


    public void userOptions(Player player) throws InterruptedException {
        CombatInterface combat = new CombatInterface();
        Explore explore = new Explore();
        UI.displayMsg("Select your option below:");
        UI.displayMsg("1: Explore\n2: Town\n3: Player\n4: Exit");

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
                case "3"://player
                    combat.combatOptions(player);
                    whileKey = true;
                    break;
                case "4": //exit
                    whileKey = true;
                    break;
                default: UI.displayMsg("Invalid input, please try again");

            }
        }
    }

    public void townOrPokeCenter(Player player) throws InterruptedException {
        Town town = new Town();
        System.out.println("You are now in the town..\nWhat place do you want to visit?");
        System.out.println("1 for PokeCenter - 2 for PokeMart - 0 to exit");
        switch(UI.userInput()){
            case "1":
                town.pokeCenter(player);
            case "2":
                town.pokeMart(player);
                case "0":
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
                //userOptions(Player player);
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
