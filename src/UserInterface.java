public class UserInterface {

    TextUI UI = new TextUI();
    Explore explore = new Explore();
    Town town = new Town();
    Player player = new Player();

    public void userOptions() throws InterruptedException {

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
}
