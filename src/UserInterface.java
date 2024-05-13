public class UserInterface {
    TextUI UI = new TextUI();


    public void userOptions(Player player) throws InterruptedException {
        StartMenu startMenu = new StartMenu();
        CombatInterface combat = new CombatInterface();
        Explore explore = new Explore();
        UI.displayMsg("Select your option below:");
        UI.displayMsg("1: Pokémon\n2: Bag\n3: Town\n4: Explore\n5: Find Pokémon\n6: Save\n7: Exit Game");
// //UI.displayMsg("  __________________________\n" +
//        "   |                          |\n" +
//        "   | .----------------------. |\n" +
//        "   | |  .----------------.  | |\n" +
//        "   | |  | 1: Explore     |  | |\n" +
//        "   | |))| 2: Town        |  | |\n" +
//        "   | |  | 3: Player Bag  |  | |\n" +
//        "   | |  | 4: Find Pokémon|  | |\n" +
//        "   | |  | 5: Save        |  | |\n" +
//        "   | |  | 6: Exit Game   |  | |\n" +
//        "   | |  |                |  | |\n" +
//        "   | |  '----------------'  | |\n" +
//        "   | |__GAME BOY____________/ |\n" +
//        "   |          ________        |\n" +
//        "   |    .    (Nintendo)       |\n" +
//        "   |  _| |_   \"\"\"\"\"\"\"\"   .-.  |\n" +
//        "   |-[_   _]-       .-. (   ) |\n" +
//        "   |   |_|         (   ) '-'  |\n" +
//        "   |    '           '-'   A   |\n" +
//        "   |                 B        |\n" +
//        "   '-----------------------`");
        boolean whileKey = false;

        while (!whileKey) {

            String option = UI.userInput();
            switch (option) {
                case "1":
                    combat.changePokemonInParty(player);
                    whileKey = true;
                    break;
                case "2"://Bag
                    explore.playerInfo(player);
                    whileKey = true;
                    break;

                case "3"://Town
                    townOrPokeCenter(player);
                    whileKey = true;
                    break;

                case "4": //explore
                    explore.exploreInterface(player);
                    whileKey = true;
                    break;

                case "5": //random encounter
                    int delay = 1000;
                    String sentence = ("Searching.. .. ... ...!\n");
                    for (String s : sentence.split(" ")) {
                        System.out.println(s);
                        Thread.sleep(delay);
                    }
                    combat.combatRandom(player);
                    whileKey = true;
                    break;
                case "6": //Save game
                    whileKey = true;
                    startMenu.savePlayer(player);
                    UI.displayMsg("Your game has been saved!");
                    userOptions(player);
                    break;
                case "7": //Exit game
                    whileKey = true;
                    UI.displayMsg("You have exited the game.");
                    break;
                default:
                    UI.displayMsg("Invalid input, please try again");

            }
        }
    }

    public void pokeMartOptions(Player player) throws InterruptedException {
        Town town = new Town();
        UserInterface user = new UserInterface();
        UI.displayMsg(".  .  .  .  .  .  .  .  .  .  .  .  .  _N_.  .  .  .  .  .  .  .  .  .  .  .  . \n" +
                " : . : . : . : . : . : . : . : . : . : |=|. : . : . : . : . : . : .__ . : . : . \n" +
                ":.:.:.:.:.:.:.:.:.:.' '________________|=|_________________________||_______  _.\n" +
                "::::::::::::'''__..--==-=-=-=-=-=-=-=- |=| -=-=-=-=-=-=-=-=-=-=-=-[!!]=-__.--~-=\n" +
                "::::'''__..--===-=-=-=-=-=-=-=-=-=-=-=-  /-=-=-=-=-=-=-=-=-=-=-=-=__.--~=-=-=-=-\n" +
                "'..--==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-/-=-=-=-=-=-=-=-=-=-__.--~-=-=-=-=-=-=-=\n" +
                " [=====================================/-=-=-=-=-=-=-=-=.--~=-=-=-=-=-=-=-=-=-=-\n" +
                ":.. | _ .  .  .   _______ .  .  .  _  /-=-=-=-=-=-=-=-= [=======================\n" +
                "::: |[_]_________| POKÉMART |________[_][====================| .  ·--·-------·--· .\n" +
                "::: | | ||_____|__¯¯¯¯¯¯¯__|____|| | |/ ║__║__║__║__║ ||  |    |::| | | | |::|  \n" +
                "::: |   ||_____|_____|_____|____||   |  ║__║__║__║__║ ||__|  . |::|-+-+-+-|::|  \n" +
                "::: | . ||_____|_____|_____|____|| .{@} ║__║__║__║__║ | __|    |::|_|_|_|_|::| .\n" +
                "::: |   ||_____|_____|_____|____|| _\\|/_____________  |___| .  |__|=======|__|  \n" +
                "----|___||_____|_____|_____|____||[=================]'----| adl  .   {@}   .  {@\n" +
                ",',',',/ , ' , ' , ' . ` . ` . ` .\\`  ` ` ~ ~ ' ' '/ - = -| \\|/      \\|/      \\|");
        UI.displayMsg("Welcome to the PokéMart!");
        UI.displayMsg("1: Buy items\n2: Sell items\n3: Leave shop");

        boolean whileKey = false;

        while (!whileKey) {

            String option = UI.userInput();
            switch (option) {
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

            user.townOrPokeCenter(player);
        }
    }

    public void townOrPokeCenter(Player player) throws InterruptedException {
        Town town = new Town();

        System.out.println("                                                          |>>>\n" +
                "                   _                      _                |\n" +
                "    ____________ .' '.    _____/----/-\\ .' './========\\   / \\\n" +
                "   //// ////// /V_.-._\\  |.-.-.|===| _ |-----| u    u |  /___\\\n" +
                "  // /// // ///==\\ u |.  || | ||===||||| |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                " |===|  |u|==|++++|==| pokécenter   .::::::::.    pokémart    | T |....| V |..\n" +
                " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n" +
                "   |       |  [ === =] /.::::::;;::::::::::::::;;;:::::::.\\ [===  =]   |\n" +
                "___|_______|__[ == ==]/.::::::;;;:::::::::::::::;;;:::::::.\\[=  == ]___|_____");
        UI.displayMsg("You are now in the town..\nWhat place do you want to visit?");
        UI.displayMsg("1: PokéCenter\n2: PokéMart\n3: Leave town");
        switch (UI.userInput()) {
            case "1":
                town.pokeCenter(player);
                break;
            case "2":
                pokeMartOptions(player);
                break;
            case "3":
                userOptions(player);
                break;
            default:
                System.out.println("Invalid input. Try again!");
                townOrPokeCenter(player);
                break;

        }

    }

    public void startMenu() throws InterruptedException {
        TextUI ui = new TextUI();
        System.out.println("****************************************************************************************************");
        ui.displayMsg("  ▄███████▄  ▄██████▄     ▄█   ▄█▄    ▄████████  ▄█     █▄   ▄██████▄     ▄████████  ▄█       ████████▄  \n" +
                "  ███    ███ ███    ███   ███ ▄███▀   ███    ███ ███     ███ ███    ███   ███    ███ ███       ███   ▀███ \n" +
                "  ███    ███ ███    ███   ███▐██▀     ███    █▀  ███     ███ ███    ███   ███    ███ ███       ███    ███ \n" +
                "  ███    ███ ███    ███  ▄█████▀     ▄███▄▄▄     ███     ███ ███    ███  ▄███▄▄▄▄██▀ ███       ███    ███ \n" +
                "▀█████████▀  ███    ███ ▀▀█████▄    ▀▀███▀▀▀     ███     ███ ███    ███ ▀▀███▀▀▀▀▀   ███       ███    ███ \n" +
                "  ███        ███    ███   ███▐██▄     ███    █▄  ███     ███ ███    ███ ▀███████████ ███       ███    ███ \n" +
                "  ███        ███    ███   ███ ▀███▄   ███    ███ ███ ▄█▄ ███ ███    ███   ███    ███ ███▌    ▄ ███   ▄███ \n" +
                " ▄████▀       ▀██████▀    ███   ▀█▀   ██████████  ▀███▀███▀   ▀██████▀    ███    ███ █████▄▄██ ████████▀  \n" +
                "                          ▀                                               ███    ███ ▀                    ");
        System.out.println("*****************************************************************************************************");
        System.out.println("START YOUR ADVENTURE!");
        System.out.println("\nSelect an option below:");
        ui.displayMsg("\n1: Continue game\n2: New game\n3: Exit Game");

        startMenuOptions();
    }

    public void startMenuOptions() throws InterruptedException {
        StartMenu menu = new StartMenu();
        switch (UI.userInput()) {
            case "1":
                menu.continueGame();
                break;
            case "2":
                menu.newGame();
                break;
            case "3":
                break;
            default:
                UI.displayMsg("Invalid input, please try again");
                startMenuOptions();

        }
    }


}
