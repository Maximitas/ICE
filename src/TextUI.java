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
}