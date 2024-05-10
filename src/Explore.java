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
            ui.displayMsg("No item data found: " + item);

        }
    }


    public void explore(Player player) throws InterruptedException {
        TextUI ui = new TextUI();
        int rand = random(4);

        if (rand < 1) {
            fishing(player);
        } else if (rand < 2) {
            findItem(player);
        } else if (rand <3 ) {
            randomDialogue(player);
        } else if (rand < 4) {
            combat.combatOptions(player);
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
            user.userOptions(player);

        } else if (randy < 100) {
            showPikachu(1000);
            user.userOptions(player);

        } else if (randy < 150) {
            showGyarados(1000);
            combat.combatOptionsGyarados(player);

        }

    }

    public int itemInitializer() {
        itemList();
        return random(item.size());
    }


    public void findItem(Player player) throws InterruptedException {
        ui.displayMsg("Exploring the world... (for items)");
        int randy = random(100);
        if (randy < 20) {
            ui.displayMsg("You see a Rock Tunnel..");
            ui.displayMsg("Entering Rock Tunnel.");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found: " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            //todo husk at add item to bagpack.
            user.userOptions(player);

        } else if (randy < 40) {
            ui.displayMsg("You see a Power Plant ..");
            ui.displayMsg("Entering Power Plant.");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found: " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            user.userOptions(player);

        } else if (randy < 60) {
            ui.displayMsg("You are visiting Safari Zone ..");
            ui.displayMsg("Entering Safari Zone.");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found: " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            user.userOptions(player);

        } else if (randy < 80) {
            ui.displayMsg("You are came near a Diglett's Cave ..");
            ui.displayMsg("Entering Diglett's Cave.");
            showTreasureChest(1000);
            addPokemonToPlayerPokemon(49);
            ui.displayMsg("You found: " + "Diglett ding, diglett ding - DIGLETT");
            ui.displayMsg("⊂(◉‿◉)つ");
            user.userOptions(player);

        } else if (randy < 100) {
            ui.displayMsg("You are came near a Pokemon Tower ..");
            ui.displayMsg("Entering Pokemon Tower.");
            showTreasureChest(1000);
            int number = itemInitializer();
            System.out.println("You found: " + item.get(number));
            io.saveItemToBag("Data/Bag.csv", item.get(number));
            user.userOptions(player);

        } else {
            ui.displayMsg("You got lost and found your way back..");
            user.userOptions(player);
        }
    }

    public void randomDialogue(Player player) throws InterruptedException {
        ui.displayMsg("Exploring the area... (for knowledge)");

        int randy = random(5);

        if (randy < 1) {
            dialogGirlyTrainee(player);
        } else if (randy < 2) {
            dialogFishingMan(player);
        } else if (randy <3) {
            dialogIdiotKid(player);
        } else if (randy <4) {
            dialogOldman(player);
        } else if (randy < 5) {
            dialogLegendary(player);
        }

    }

    public void dialogLegendary(Player player) throws InterruptedException {
        ui.displayMsg("You are enjoying yourself and see a group of people storming at you!");
        ui.displayMsg("It seems like you need to prepare for battle");
        ui.displayMsg("Some guy named 'Red' wants to battle you");
        ui.displayMsg("Do you want this. y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.displayMsg("You are now battling Red!");
            combat.combatOptionsRed(player);
        } else if (input.equals("n")) {
            ui.displayMsg("Next time, take the battle, you wont regret it..Goodluck");

        }
        user.userOptions(player);

    }

    public void dialogOldman(Player player) throws InterruptedException {
        ui.displayMsg("You see a old man near a statue...");
        ui.displayMsg("Do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.oldManTalk();
            combat.combatOptionsDarkry(player);
        } else if (input.equals("n")) {
            ui.displayMsg("He looked worried..maybe i should talk to him.");
            //todo enten tilbage til randomdialog eller menu

        }
        user.userOptions(player);
    }


    public void dialogIdiotKid(Player player) throws InterruptedException {
        ui.displayMsg("You see a kid sitting near a tree...");
        ui.displayMsg("Do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.displayMsg("When i think about it, you, too, are all alone in this world..");
        } else if (input.equals("n")) {
            ui.displayMsg("Trust me, you dodged a bullet..");
            //todo enten tilbage til randomdialog eller menu

        }
        user.userOptions(player);

    }


    public void dialogFishingMan(Player player) throws InterruptedException {

        ui.displayMsg("You see a man fishing...");
        ui.displayMsg("do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.fishingmanTalk();
            //todo enten tilbage til randomdialog eller menu
        } else if (input.equals("n")) {
            ui.displayMsg("No sushi today..");

        }
        user.userOptions(player);
    }

    public void dialogGirlyTrainee(Player player) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ui.displayMsg("You meet a random PokeTrainee..");
        ui.displayMsg("do you want to approach her? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.displayMsg("I want to become Strong... but i can't seem to catch any pokemons.. what do i do!");
            ui.displayMsg("Tell her what to do:");
            String answer = ui.userInput();
            ui.displayMsg("You told her: " + answer);
            ui.displayMsg("Hmm.. never thought about that, I'll do my best!");
            //todo enten tilbage til randomdialog eller menu
        } else if (input.equals("n")) {
            ui.displayMsg("You fled the area");

        }
        user.userOptions(player);
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

        String sentenceTreasureChest = ("... ... ... ...!\n " + "⠀\n" +
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
        String sentencePika = ("... ... ... ...!  \n" +
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
                 "YOU FOUND GYARADOS! PREPARE FOR BATTLE⠀");

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