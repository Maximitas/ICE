import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Explore {
    private CombatInterface combat = new CombatInterface();

    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private ArrayList<Item> item = new ArrayList<>();
    String itemFile = "Data/Item.csv";
    UserInterface user = new UserInterface();

    public void itemList() {
        this.item = io.readItemsFromBag(itemFile);

        if (item.isEmpty()) {
            ui.displayMsg("Oak: No item data found: " + item);

        }
    }

    public void explore(Player player) throws InterruptedException {
        int rand = random(4);
        int delay = 1000;
        String sentence = ("Exploring. .. ... ...!\n");
        for (String s : sentence.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);
        }

        if (rand < 1) {
            fishing(player);
        } else if (rand < 2) {
            findItem(player);
        } else if (rand < 3) {
            randomDialogue(player);
        } else if (rand < 4) {
            combat.combatRandom(player);
        }

    }

    public void gatesOfPokemon(Player player) throws InterruptedException {
        TextUI ui = new TextUI();
        CombatInterface combat = new CombatInterface();

        boolean key = true;
        ui.displayMsg("Gates of Pokémon");
        ui.displayMsg("1: 1F (Lvl 1-10)\n2: 2F (Lvl 10-25)\n3: 3F (25-70)\n4: Leave Gates of Pokémon");
        while (key) {
            switch (ui.userInput()) {
                case "1": //lvl 5-10;
                    combat.combat5to10(player);
                    key = false;
                    break;
                case "2": //lvl 10-25
                    combat.combat10to25(player);
                    key = false;
                    break;
                case "3": //25-70
                    combat.combat25to50(player);
                    key = false;
                    break;
                case "4":
                    ui.displayMsg("Leaving Gates of Pokémon");
                    exploreInterface(player);
                    key = false;
                    break;
                default:
                    ui.displayMsg("Oak: That isn't the right input!");
                    break;

            }
        }

    }

    public void randomSelect(Player player) throws InterruptedException {
        int rand = random(4);
        if (rand < 1) {
            fishing(player);
        } else if (rand < 2) {
            findItem(player);
        } else if (rand < 3) {
            randomDialogue(player);
        } else if (rand < 4) {
            combat.combatRandom(player);
        }
    }

    public void exploreInterface(Player player) throws InterruptedException {
        int delay = 1000;
        Music.clip.stop();
        Music.playMusic("src/Soundtracks/Pokémon Red & Blue - The Road to Cerulean from Mt. Moon.wav");

        ui.displayMsg("     .               *             .       __      .            .-.   .         \n" +
                " *            .           .   *      . __.'  `.         *   .    ) )         *  \n" +
                "   ..     *         .           _____.'        `-----.___       `-'    *    .   \n" +
                "  /  \\           /\\    /\\     .'  ·   ·   ·   ·   ·   ·  `._____         /\\   .'\n" +
                "-'    `'`'`..'`.'  `..'  `'`./ · ·   ·   ·   · ·   ·   · ·   · ·`.-'`-'`'  `.'··\n" +
                "  ·  .-------.    ·  .------'   · · ·   ·   · ·   · ·   ·   · · · \\  ·   · /· · \n" +
                "     !-------!      /· · · ·   ·   · · ·   · ·   · ·   · · ·   _ · `.__   /··· ·\n" +
                "·   ·| POKÉ  | ·   ;· · · · · · · · _ · · · · · · · · · · · ·_|-| ·    `./·:· · \n" +
                "     | WORLD |   .'··_ ··· ··· ··· |-|_··· ··· ··· ··· ··· ·|-|:|· · ·   \\_···:·\n" +
                "-----|[=] [=]| _::·_|-|_:.------.:_|:|-|_:··_:_·_·: _:_ ··: |:|:| ····=· |-|:.--\n" +
                "-----|[=] [=]||-|_|-|:|-|!------!|-|:|:|-|_|-|-|-|_|-|-|.------------|¨|---.|!--\n" +
                "[][][|[=] [=]||:_ .------|[][][]||:_:___:_-_:_:_:_-_:_: ||_|_|_|_/\\|_!_!_|_|:|[]\n" +
                "[][][|[=] [=]|:|-|!------|[][][]|:|-¡++/\\++++/\\++++/\\++¡|_|_|_|_//\\\\|_|_|_||:|[]\n" +
                "[][][|[=] [=]|:|:||[][][]|[][][]|:|:|+/__\\++/__\\++/__\\+|||_|_|_//__\\\\|_|_|_|:|[]\n" +
                "[][][|[=] [=]|·=·:|[][][]|[][][]|·=·|/ HH \\/ HH \\/ H·=·||_|_|_//||||\\\\|_|_||:|[]");
        System.out.println("Choose an option! ");
        System.out.println("1: Fishing\n2: Items searching\n3: Gates of Pokémon \n4: NPC dialogue\n5: Find random Pokémons\n6: Random events\n7: Stop exploring");
        boolean z = true;
        while (z) {
            String user = ui.userInput();
            switch (user) {
                case "1":
                    fishing(player);
                case "2":
                    findItem(player);
                case "3":
                    gatesOfPokemon(player);
                    z = false;
                case "4":
                    randomDialogue(player);
                    z = false;
                case "5":
                    String sentence = ("Searching.. .. ... ...!\n");
                    for (String s : sentence.split(" ")) {
                        System.out.println(s);
                        Thread.sleep(delay);
                    }
                    combat.combatRandom(player);
                case "6":
                    explore(player);
                    randomSelect(player);
                case "7":
                    Music.clip.stop();
                    UserInterface ui2 = new UserInterface();
                    ui2.userOptions(player);


                default:
                    System.out.println("Oak: That isn't the right input!");
                    break;

            }
        }
    }


    public int random(int k) {
        Random random = new Random();
        return random.nextInt(k);
    }

    public void fishing(Player player) throws InterruptedException {
        ui.displayMsg("You entered Route 12 (Fishing area)");
        ui.displayMsg("You are now fishing..");
        int randy = random(150);
        if (randy < 50) {

            showMagikarp(1000);

            //Input er pokemons number - 1 for at add til player pokemon bag
            addPokemonToPlayerPokemon(128);
            exploreInterface(player);

        } else if (randy < 100) {
            showPikachu(1000);
            exploreInterface(player);

        } else if (randy < 150) {
            showGyarados(1000);
            combat.combatOptionsGyarados(player);

        }

    }

    public int itemInitializer() {
        itemList();
        return random(item.size());
    }

    public void playerInfo(Player player) throws InterruptedException {
        ui.displayMsg("Pokemon Party: " + player.getPokemonParty().size());
        ui.displayMsg("Player: " + player.getName());
        ui.displayMsg("Player currency: " + player.getWallet());
        ui.displayMsg("Player bag: " + player.getBag());
        user.userOptions(player);
    }


    public void findItem(Player player) throws InterruptedException {
        ui.displayMsg("Exploring the world... In the search for items!");
        int randy = random(100);
        if (randy < 20) {
            ui.displayMsg("You spot a Mysterious Cave..");
            ui.displayMsg("Entering the cave...");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found a " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            exploreInterface(player);

        } else if (randy < 40) {
            ui.displayMsg("You spot an Abandoned House..");
            ui.displayMsg("Entering the house...");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found a " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            exploreInterface(player);

        } else if (randy < 60) {
            ui.displayMsg("You visited the Safari Zone..");
            ui.displayMsg("Entering the Safari Zone...");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found a " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            exploreInterface(player);

        } else if (randy < 80) {
            ui.displayMsg("You spotted the Diglett Cave..");
            ui.displayMsg("Entering Diglett Cave.");
            showDigglet(1000);
            addPokemonToPlayerPokemon(49);
            ui.displayMsg("You found a Diglett!");

            exploreInterface(player);

        } else if (randy < 100) {
            ui.displayMsg("You came near Sky Pillar");
            ui.displayMsg("Entering Sky Pillar.");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found a " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            exploreInterface(player);
        }
    }

    public void randomDialogue(Player player) throws InterruptedException {
        ui.displayMsg("Exploring the area... In the search for knowledge!");

        int randy = random(5);

        if (randy < 1) {
            dialogGirlyTrainee(player);
        } else if (randy < 2) {
            dialogFishingMan(player);
        } else if (randy < 3) {
            dialogIdiotKid(player);
        } else if (randy < 4) {
            dialogOldman(player);
        } else if (randy < 5) {
            dialogLegendary(player);
        }

    }

    public void dialogLegendary(Player player) throws InterruptedException {
        ui.displayMsg("You are out about to reach the top of Mt. Silver.");
        ui.displayMsg("While enjoying your hike you spot an individual int the distance.");
        ui.displayMsg("You talk to the individual who introduces himself as Red and he wants to battle you?!");
        ui.displayMsg("Do you accept this challenge. Y/N");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("Y")) {
            ui.displayMsg("You are now battling Red!");
            combat.combatOptionsRed(player);
        } else if (input.equalsIgnoreCase("N")) {
            ui.displayMsg("Next time, take the challenge, you wont regret it.. Goodluck!");

        }

        exploreInterface(player);

    }

    public void dialogOldman(Player player) throws InterruptedException {
        ui.displayMsg("You see an old man near a statue...");
        ui.displayMsg("Do you want to approach him? Y/N");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("Y")) {
            ui.oldManTalk();
            combat.combatOptionsDarkrai(player);
        } else if (input.equalsIgnoreCase("N")) {
            ui.displayMsg("He looked worried.. Maybe i should've talk to him.");

        }
        exploreInterface(player);
    }


    public void dialogIdiotKid(Player player) throws InterruptedException {
        ui.displayMsg("You see a kid sitting near a tree...");
        ui.displayMsg("Do you want to approach him? Y/N");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("Y")) {
            ui.displayMsg("When i think about it, you too, are all alone in this world..");
        } else if (input.equalsIgnoreCase("N")) {
            ui.displayMsg("Trust me, you dodged a bullet..");

        }
        exploreInterface(player);

    }


    public void dialogFishingMan(Player player) throws InterruptedException {

        ui.displayMsg("You see a man fishing...");
        ui.displayMsg("Do you want to approach him? Y/N");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("Y")) {
            ui.fishingmanTalk();
        } else if (input.equalsIgnoreCase("N")) {
            ui.displayMsg("No sushi today..");

        }
        exploreInterface(player);
    }

    public void dialogGirlyTrainee(Player player) throws InterruptedException {
        ui.displayMsg("You meet a random Pokémon Trainer..");
        ui.displayMsg("Do you want to approach her? Y/N");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("Y")) {
            ui.displayMsg("I want to become Strong... But i can't seem to catch any Pokémon.. What do i do?");
            ui.displayMsg("Tell her what to do:");
            String answer = ui.userInput();
            ui.displayMsg("You told her: " + answer);
            ui.displayMsg("Hmm.. I never thought about that, I'll do my best!");
        } else if (input.equalsIgnoreCase("N")) {
            ui.displayMsg("You fled the area");

        }
        exploreInterface(player);
    }

    public void showMagikarp(int delay) throws InterruptedException {

        String sentence = ("... ... ... ...!  \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠁⢻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠁⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⣦⣄⠀⣠⡾⠟⠁⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣇⠈⠛⢿⣟⠁⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠙⠷⠄⠀⠀⠀⠀⠀⠘⣿⢶⣶⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⢈⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣾⡟⠛⢿⣷⣦⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⠿⠟⠛⠉⠉⠀⠀⠈⣇⠈⠉⠛⢿⠿⣶⣤⡀⠀⠀⠀⢀⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣴⡾⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣄⣀⣠⠞⠀⠀⠙⠻⣷⣤⣴⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⣴⡿⠋⠀⠀⢀⣠⠤⠤⠤⣄⠀⠀⠀⠀⠀⢇⠀⠈⠳⣄⠀⠀⠀⠈⠙⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡶⠶⣿⣿⡿⣿⠟\n" +
                "⠀⣀⣴⣿⠋⠀⠀⠀⢰⠏⠀⠀⠀⠀⠈⢳⡀⠀⠀⠀⢸⠀⠀⢀⠎⠀⠀⠀⠀⠀⠈⡿⣷⡄⠀⠀⠀⠀⣀⣴⠟⢋⣡⡶⠛⠉⠀⢰⡏⠀\n" +
                "⢸⣟⠙⢧⡀⠀⠀⠀⣟⠀⠀⠀⠃⠀⠀⢰⠇⠀⠀⠀⠀⡗⠒⠣⡀⠀⠀⠀⠀⠀⠀⢱⠈⢿⣆⠀⢀⡼⠋⢀⡴⠟⠁⠀⠀⠀⠀⡿⠀⠀\n" +
                "⠀⠻⣦⡄⠘⡆⠀⠀⠻⣦⡀⠀⠀⣀⡴⠋⠀⠀⢀⣠⡴⠿⠶⠶⠿⢦⣤⣤⣤⣤⣀⣸⣄⠀⣻⣦⠟⠀⣴⣏⠤⠄⠒⠒⠒⠒⣾⠃⠀⠀\n" +
                "⠀⠀⢿⠺⠀⢱⠀⠀⠀⠈⠛⠛⠛⠉⠀⣀⡴⠞⠋⢁⠴⠒⠒⠊⣉⡉⠓⠒⠒⠒⣶⣿⡿⠛⠉⣿⢀⡾⠋⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⠀\n" +
                "⠀⠀⢸⣀⡇⢸⠀⠀⠀⠀⣠⣤⣀⠀⢸⠋⠀⢀⣴⠥⠒⠊⠉⠁⠀⠀⠀⢀⣴⠟⠉⢠⠃⠀⠀⠈⣾⠁⠀⠀⠀⠀⠀⠀⣴⠃⠀⠀⠀⠀\n" +
                "⠀⣠⣾⣫⠃⡜⠀⠀⠀⠀⠈⠳⣌⢳⡀⠀⢰⣟⠓⠢⠄⡀⠀⠀⠀⢀⣴⠟⠁⠀⠀⡸⠀⠀⠀⣸⡇⠀⠀⠀⠀⠀⠀⣸⠃⠀⠀⠀⠀⠀\n" +
                "⢰⢧⡟⠁⢠⣇⡀⠀⠀⠀⠀⠀⠸⡄⢷⠀⠀⠈⠛⢦⣀⠀⠀⠀⢠⡟⠁⠀⠀⠀⢀⠇⠀⢀⠜⣿⢧⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀\n" +
                "⡞⢸⠛⠒⠿⣷⣬⡓⠢⠤⣀⡀⠀⣇⠸⡆⠀⠀⠀⡾⠛⢷⣄⣰⡟⠀⠀⠀⠀⠀⣞⣠⡴⡟⠀⣿⠈⢆⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀\n" +
                "⢧⠸⡇⠀⠀⠈⠙⠿⣷⣶⣤⣈⡉⣿⠀⡧⠤⣀⣞⠁⠀⣀⠟⠟⠀⠀⠀⣀⣤⣾⠿⠋⠀⣿⠀⣿⠀⠈⠣⡀⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀\n" +
                "⠘⣆⢳⠀⠀⠀⠀⠀⠀⠈⠙⠛⠿⡇⠀⣷⣶⣿⣤⣭⣭⣤⣴⣶⣶⡾⠿⠛⠉⠁⠀⠀⠀⠸⣇⢸⡇⠀⠀⠈⠢⣀⡾⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠹⣦⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⢸⡿⠃⠀⠀⠀⠀⠀⠀⠀⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⢿⡄⠀⠀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠘⣇⢧⠀⠀⠀⠀⠀⠀⠀⢰⠃⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣿⣦⣸⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠸⡎⢧⠀⠀⠀⠀⠀⢀⣾⡄⢸⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⢹⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢱⠸⡆⠀⠀⠀⠀⠘⠻⡇⢸⣤⡶⠟⢿⣤⡀⠀⣾⠿⣶⣤⣀⣻⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⣤⡇⠀⠀⠀⠀⠀⠀⢻⡘⣇⠀⠀⠀⠙⠻⣶⡟⠀⠀⠉⠙⠛⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠈⢧⡙⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢰⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠑⢦⣽⣦⣄⣀⠀⠀⠀ \n" +
                "YOU CAUGHT A MAGIKARP⠀");


        for (String s : sentence.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }


    }

    public void showTreasureChest(int delay) throws InterruptedException {

        String sentenceTreasureChest = ("... ... ... ...!\n " +
                "⠀⠀⠀⠀⠀⠰⠿⠿⠿⢿⣿⣷⣶⣶⣶⣦⣤⣤⣤⣤⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢰⣶⣦⠀⣶⣤⣤⣤⣤⣍⣉⣉⣉⡙⠛⠛⠛⠛⠏⣰⣿⡆⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢿⡿⢠⣿⣿⣿⣿⣿⣿⣿⣿⠻⣿⣿⣿⣿⣿⣆⠸⣿⡇⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠘⡇⢸⣿⣿⣿⣿⣿⣿⣿⡏⠀⠹⠟⠙⣿⣿⣿⠄⢻⡇⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠊⣉⡉⢋⣩⡉⠻⠛⠁⣾⣀⣴⡀⢛⡉⢠⣷⠈⠇⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣠⣼⣿⣿⣿⣿⣿⣷⣿⠀⢿⣿⣿⣿⡿⢁⠚⠛⠃⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠤⠾⠿⣿⡿⠛⣿⣿⣿⣿⣿⣷⣦⣌⣉⣉⣠⣾⡷⠂⣠⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⢰⣶⣶⣶⣦⠀⠀⣤⣌⣉⠉⣉⡙⠛⠛⠛⠻⠟⢁⣴⣾⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⣆⠻⣿⣿⢇⣸⠀⣯⢉⣿⠀⣿⣿⣿⣿⣿⣷⠀⣿⣿⣿⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⣿⣷⡔⠐⣾⣿⠀⠛⠚⠿⠀⣿⣿⣿⣿⣿⣿⠀⣿⣿⣿⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⣿⣿⣿⣶⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⣿⠀⣿⣿⣿⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣿⣿⠿⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠰⣦⡄⠀⠀⠈⠉⠉⠉⠉⠛⠛⠛⠛⠻⠿⠿⠿⠿⠀⠛⢁⣀⡀⠲⠖⠀⠀\n" +
                "YOU FOUND A TREASURE CHEST");

        for (String s : sentenceTreasureChest.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }
    }


    public void showPikachu(int delay) throws InterruptedException {
        String sentencePika = ("... ... ... ...!\n" +
                "⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀ ⠀ ⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿\n"
                + "YOU CAUGHT NOTHING⠀");

        for (String s : sentencePika.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }

    }

    public void showGyarados(int delay) throws InterruptedException {
        String sentencePika = ("... ... ... ...!  \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣼⡏⢹⣧⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀\n" +
                "⣾⡟⠻⠷⣶⣤⣀⣀⣠⣶⣿⣿⣿⣿⠇⠸⣿⣿⣿⣿⣶⣄⣀⣀⣤⣶⠾⠟⢻⣷\n" +
                "⠙⠿⣶⣄⡀⠉⠛⠻⢿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⣿⣿⡿⠟⠛⠉⢀⣠⣶⠿⠋\n" +
                "⠀⠀⠈⠙⢻⣷⣦⣀⠀⠀⠉⠛⠻⠟⠀⠀⠻⠟⠛⠉⠀⠀⣀⣴⣾⡟⠋⠁⠀⠀\n" +
                "⠀⢀⣀⣤⣿⣿⡏⠛⠿⣶⣄⡀⠀⠀⠀⠀⠀⠀⢀⣠⣶⠿⠛⢹⣿⣿⣤⣀⡀⠀\n" +
                "⣾⣿⣿⣿⣿⣿⡇⠀⠐⠟⠙⢻⣷⣦⣤⣤⣴⣾⡟⠋⠻⠀⠀⢸⣿⣿⣿⣿⣿⣷\n" +
                "⢿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣿⣿⣿⣿⣿⣿⡿\n" +
                "⠀⠈⠙⠛⠿⣿⣿⣿⣿⣿⣿⡟⢿⣿⠿⠿⣿⡿⢻⣿⣿⣿⣿⣿⣿⠿⠛⠋⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⡇⠸⡟⠀⠀⢻⠇⢸⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⡷⢶⣶⣦⣴⣶⠶⢾⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠈⠉⠛⠿⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⠿⠛⠉⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⢰⣧⠀⠀⣼⡆⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⣾⣿⣶⣶⣿⣷⣾⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "YOU FOUND A GYARADOS! PREPARE FOR BATTLE!⠀");

        for (String s : sentencePika.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }

    }

    public void showDigglet(int delay) throws InterruptedException {
        String sentencePika = ("... ... ... ...!  \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣾⣿⣿⣿⣶⣦⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡏⠈⣿⣿⣿⣿⠀⣿⣿⣇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⣿⣿⣷⣶⣿⣿⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⣠⣶⣾⣿⣿⣿⣶⣤⡈⠙⢿⣿⠉⠀⠀⠙⣿⣿⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠈⢿⣷⣶⣶⣾⣿⣿⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢠⣿⣿⣿⡇⠈⣿⣿⣿⡏⠀⣿⣿⡆⠈⠋⢉⣀⣀⣀⣈⠉⠉⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢸⣿⣿⣿⣷⣶⣿⣿⣿⣿⣶⣿⡟⠁⣠⣾⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀\n" +
                "⠀⠀⢸⣿⣿⣿⣿⣿⠁⠀⠀⢹⣿⡟⢀⣾⣿⣿⡿⢿⣿⣿⣿⡿⢿⣿⣧⠀⠀⠀\n" +
                "⠀⠀⢸⣿⣿⣿⣿⣿⣷⣶⣶⣿⣿⠁⢸⣿⣿⣿⡇⢸⣿⣿⣿⡇⢀⣿⣿⡆⠀⠀\n" +
                "⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿⣿⡇⠀⠀\n" +
                "⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⣧⡀⠀⢀⣼⣿⣿⣿⡇⠀⠀\n" +
                "⠀⠀⠘⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠀⠸⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠃⠀⠀⠀\n" +
                "⠀⣠⣴⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣦⣄⠀\n" +
                "⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "YOU FOUND A DIGGLET!");

        for (String s : sentencePika.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }

    }


    public void addPokemonToPlayerPokemon(int input) {

        ArrayList<Pokemon> allPokemons = io.loadPokemonFromFile("Data/Pokemon.csv");
        Pokemon playerPokemon = allPokemons.get(input);
        io.savePokemonToPlayerPokemons("Data/PlayerPokemons.csv", playerPokemon);
    }
}