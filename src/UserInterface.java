public class UserInterface {

    TextUI UI = new TextUI();


    Player player = new Player();


    public void userOptions() throws InterruptedException {
        Explore explore = new Explore();
        UI.displayMsg("Select your option below:");
        UI.displayMsg("1: Explore\n2: Town\n3: Player\n4: Exit");

        boolean whileKey = false;

        while(!whileKey){

            String option = UI.userInput();
            switch(option){
                case "1":
                    explore.explore();
                whileKey = true;
                    break;
                case "2"://Town
                    townOrPokeCenter();
                    whileKey = true;
                    break;
                case "3"://player
                    whileKey = true;
                    break;
                case "4": //exit
                    whileKey = true;
                    break;
                default: UI.displayMsg("Invalid input, please try again");

            }
        }
    }

    public void townOrPokeCenter() throws InterruptedException {
        Town town = new Town();
        System.out.println("You are now in the town..\nWhat place do you want to visit?");
        System.out.println("1 for PokeCenter - 2 for PokeMart - 0 to exit");
        switch(UI.userInput()){
            case "1":
                town.pokeCenter(player);
            case "2":
                town.pokeMart(player);
                case "0":
                    userOptions();
            default:
                System.out.println("Try again");
                break;

        }

    }
}
