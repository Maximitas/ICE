import java.util.Scanner;

public class TextUI {

    public String userInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }


    public void displayMsg(String msg) {
        System.out.println(msg);
    }
}