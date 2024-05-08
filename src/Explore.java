import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Explore {
    private CombatInterface combat = new CombatInterface();

    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private Player player = new Player();
    private ArrayList<Item> item = new ArrayList<>();
    String itemFile = "Data/Item.csv";
    UserInterface user = new UserInterface();

    public void itemList() {
        this.item = io.readItemsFromBag(itemFile);

        if (item.isEmpty()) {
            ui.displayMsg("No item data found: " + item);

        }
    }


    public void explore() throws InterruptedException {
        TextUI ui = new TextUI();
        int rand = random(3);

        if (rand < 1) {
            fishing();
        } else if (rand < 2) {
            findItem();
        } else if (rand < 3) {
            randomDialogue();
        } else if (rand < 0) {
            //combat();
        } else {

        }

    }

    public int random(int k) {
        Random random = new Random();
        return random.nextInt(k);
    }

    public void fishing() throws InterruptedException {
        ui.displayMsg("You entered Route 12 (Fishing area)");
        ui.displayMsg("You are now fishing..");
        int randy = random(40);
        if (randy < 20) {

            showMagikarp(1000);
            //todo mangler at add pokemon i bag
            user.userOptions();

        } else if (randy < 40) {
            //todo mangler at add pokemon i bag
            showPikachu(1000);
            user.userOptions();

        }

    }

    public int itemInitializer() {
        itemList();
        return random(item.size());
    }


    public void findItem() throws InterruptedException {
        System.out.println("Exploring the world... (for items)");
        int randy = random(100);
        if (randy < 20) {
            System.out.println("You see a Rock Tunnel..");
            System.out.println("Entering Rock Tunnel.");
            showTreasureChest(1000);
            itemInitializer();
            System.out.println("You found: " + item.get(itemInitializer()));
            //todo husk at add item to bagpack.
            user.userOptions();

        } else if (randy < 40) {
            System.out.println("You see a Power Plant ..");
            System.out.println("Entering Power Plant.");
            showTreasureChest(1000);
            itemInitializer();
            System.out.println("You found: " + item.get(itemInitializer()));
            user.userOptions();

        } else if (randy < 60) {
            System.out.println("You are visiting Safari Zone ..");
            System.out.println("Entering Safari Zone.");
            showTreasureChest(1000);
            itemInitializer();
            System.out.println("You found: " + item.get(itemInitializer()));
            user.userOptions();

        } else if (randy < 80) {
            System.out.println("You are came near a Diglett's Cave ..");
            System.out.println("Entering Diglett's Cave.");
            showTreasureChest(1000);
            itemInitializer();
            System.out.println("You found: " + "Diglett ding, diglett ding - DIGLETT");
            System.out.println("⊂(◉‿◉)つ");
            user.userOptions();

        } else if (randy < 100) {
            System.out.println("You are came near a Pokemon Tower ..");
            System.out.println("Entering Pokemon Tower.");
            showTreasureChest(1000);
            itemInitializer();
            System.out.println("You found: " + item.get(itemInitializer()));
            user.userOptions();

        } else {
            System.out.println("You got lost and found your way back..");
            user.userOptions();
        }
    }

    public void randomDialogue() throws InterruptedException {
        System.out.println("Exploring the area... (for knowledge)");

        int randy = random(100);

        if (randy < 20) {
            dialogGirlyTrainee();
        } else if (randy < 40) {
            dialogFishingMan();
        } else if (randy < 60) {
            dialogIdiotKid();
        } else if (randy < 80) {
            dialogOldman();
        } else if (randy < 95) {
            dialogLegendary();
        }

    }

    public void dialogLegendary() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ui.displayMsg("You are enjoying yourself and see a group of people storming at you!");
        ui.displayMsg("It seems like you need to prepare for battle");
        System.out.println("Some guy named 'Red' wants to battle you");
        ui.displayMsg("Do you want this. y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            //Battle red
        } else if (input.equals("n")) {
            System.out.println("Next time, take the battle, you wont regret it..Goodluck");
            //todo enten tilbage til randomdialog eller menu

        }
        user.userOptions();

    }

    public void dialogOldman() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ui.displayMsg("You see a old man near a statue...");
        ui.displayMsg("Do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            System.out.println("Soon this beast will come back alive.");
            System.out.println("i hope there is good enough poketrainers in this world..");
            System.out.println("I heard last time it came, the world was near extinction");
            System.out.println("But one unbelievable savior came, and harnest the darkness. no one knows what happend to him or the beast.. ");
            System.out.println("But that was 600 years ago, what do we do now!...");
            System.out.println("faith... faith.. thats all, faith.");
        } else if (input.equals("n")) {
            System.out.println("He looked worried..maybe i should talk to him.");
            //todo enten tilbage til randomdialog eller menu

        }
        user.userOptions();
    }


    public void dialogIdiotKid() throws InterruptedException {
        ui.displayMsg("You see a kid sitting near a tree...");
        ui.displayMsg("Do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            ui.displayMsg("When i think about it, you, too, are all alone in this world..");
        } else if (input.equals("n")) {
            System.out.println("Trust me, you dodged a bullet..");
            //todo enten tilbage til randomdialog eller menu

        }
        user.userOptions();
    }

    public void dialogFishingMan() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ui.displayMsg("You see a man fishing...");
        ui.displayMsg("do you want to approach him? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            System.out.println("These lakes has a lot of history in them\n");
            System.out.println("There was a legend about a big fish..\n");
            System.out.println("Tales says that the one, that gets the hold of it, will become a strong poketrainer");
            System.out.println("it was about this season it should appear...");
            System.out.println("So far i have only found magikarps, but one day i might see it!");
            //todo enten tilbage til randomdialog eller menu
        } else if (input.equals("n")) {
            System.out.println("No sushi today..");

        }
        user.userOptions();
    }

    public void dialogGirlyTrainee() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You meet a random PokeTrainee..");
        ui.displayMsg("do you want to approach her? y/n");
        String input = ui.userInput();
        if (input.equals("y")) {
            System.out.println("I want to become Strong... but i can't seem to catch any pokemons.. what do i do!");
            System.out.println("Tell her what to do:");
            String answer = ui.userInput();
            System.out.println("You told her: " + answer );
            System.out.println("Hmm.. never thought about that, I'll do my best!");
            //todo enten tilbage til randomdialog eller menu
        } else if(input.equals("n")) {
            System.out.println("You fled the area");

        }
        user.userOptions();
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
}