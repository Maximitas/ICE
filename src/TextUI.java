import java.util.Scanner;

public class TextUI {
    Scanner scan = new Scanner(System.in);

    public String userInput() {
        return scan.nextLine();
    }

    public int userInputInt() {
        return scan.nextInt();
    }


    public void displayMsg(String msg) {
        System.out.println(msg);
    }
    public void fishingmanTalk() throws InterruptedException {
        TextUI ui = new TextUI();
        ui.displayMsg("These lakes has a lot of history in them\n");
        ui.displayMsg("There was a legend about a big fish..\n");
        ui.displayMsg("Tales says that the one, that gets the hold of it, will become a strong poketrainer");
        ui.displayMsg("it was about this season it should appear...");
        ui.displayMsg("So far i have only found magikarps, but one day i might see it!");
    }

    public void oldManTalk() throws InterruptedException {
        System.out.println("Soon this beast will come back alive.");
        System.out.println("i hope there is good enough poketrainers in this world..");
        System.out.println("I heard last time it came, the world was near extinction");
        System.out.println("But one unbelievable savior came, and harnest the darkness. no one knows what happend to him or the beast.. ");
        System.out.println("But that was 600 years ago, what do we do now!...");
        System.out.println("faith... faith.. thats all, faith.");
        System.out.println("I haven't slept for years..");
        System.out.println("... , ... , ..., ..., ");
        System.out.println("... What is happening!");
        System.out.println("Why is everything black!");
        System.out.println("...'Shadow figure appearing'...");
    }

}