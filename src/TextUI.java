import java.util.Scanner;

public class TextUI {
    Scanner scan = new Scanner(System.in);

    public String userInput() {
        return scan.nextLine();
    }

    public void displayMsg(String msg) {
        System.out.println(msg);
    }
    public void fishingmanTalk() throws InterruptedException {
        TextUI ui = new TextUI();
        ui.displayMsg("These lakes have a lot of history\n");
        ui.displayMsg("There was a legend about a big fish..\n");
        ui.displayMsg("Tales say that the one, that gets the hold of it, will become a strong Pokémon Trainer");
        ui.displayMsg("It was about this season it should appear...");
        ui.displayMsg("So far i have only found Magikarps, but one day i might see it!");
    }

    public void oldManTalk() throws InterruptedException {
        TextUI ui = new TextUI();
        ui.displayMsg("Soon that beast will return...");
        ui.displayMsg("I hope there is good enough Pokémon Trainers out in this world..");
        ui.displayMsg("I heard last time it came, the world was near extinction");
        ui.displayMsg("But one unbelievable savior came, and dispelled the darkness. No one knows what happened to him or the beast.. ");
        ui.displayMsg("But that was 600 years ago, what do we do now!");
        ui.displayMsg("Faith... Faith.. That's all, faith.");
        ui.displayMsg("I haven't slept for years..");
        ui.displayMsg("... , ... , ..., ..., ");
        ui.displayMsg("... What is happening!");
        ui.displayMsg("Why is everything black!");
        ui.displayMsg("... 'A Shadowy figure appears'...");
    }

}