import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class Town {
    private UserInterface user = new UserInterface();
    private TextUI textUI = new TextUI();
    private FileIO fileIO = new FileIO();
    private String name;
    private int price;

    public void pokeCenter(Player player) throws InterruptedException {
        int delay = 1500;
        Music.clip.stop();
        Music.playMusic("src/Pokemon Blue⧸Red - Pokemon Center.wav");
        ArrayList<Pokemon> pokemons = player.getPokemonParty();
        String sentence = ("Healing.. .. ... ...!\n");
        for (String s : sentence.split(" ")) {
            System.out.println(s);
            Thread.sleep(delay);

        }
        textUI.displayMsg("All Pokémon have been fully healed at the PokéCenter!");

        for (Pokemon pokemon : pokemons) {
            pokemon.restoreHP();
            fileIO.savePokemonData("Data/PlayerPokemons.csv", pokemons);

        }
        user.townOrPokeCenter(player);


    }


    public void buy(Player player) throws InterruptedException {
        Item[] itemsForSale = {
                new Item("Poke Ball", 200),
                new Item("Potion", 100),
                new Item("Super Potion", 500),
                new Item("Revive", 1500)
        };

        FileIO file = new FileIO();

        ArrayList<Item> playerBag = file.readItemsFromBag("Data/Bag.csv");
        file.saveItemsToBag("Data/Bag.csv", playerBag);

        textUI.displayMsg("Here's what we have for sale:");
        int index = 1;
        for (Item item : itemsForSale) {
            textUI.displayMsg(index++ + ". " + item.getName() + " - P$" + item.getPrice());
        }
        textUI.displayMsg("Your current balance: P$" + player.getWallet());
        textUI.displayMsg("Enter the number of the item you want to buy, or 0 to exit: ");

        String choiceInput = textUI.userInput();

        try {
            int choice = Integer.parseInt(choiceInput);
            if (choice == 0) {
                textUI.displayMsg("Thank you for visiting the PokéMart!");
                user.townOrPokeCenter(player);

            }

            if (choice < 1 || choice > itemsForSale.length) {
                textUI.displayMsg("Oak: That isn't the right input!");
                buy(player);
            }

            Item selectedItem = itemsForSale[choice - 1];
            if (player.getWallet() >= selectedItem.getPrice()) {
                player.setWallet(player.getWallet() - selectedItem.getPrice());
                textUI.displayMsg("You have bought a " + selectedItem.getName() + ".");
                textUI.displayMsg("Remaining balance: P$" + player.getWallet());
                player.addItemToBag(selectedItem);
            } else {
                textUI.displayMsg("You do not have enough money to buy this item.");
            }
        } catch (NumberFormatException e) {
            textUI.displayMsg("Oak: That isn't the right input!");
        }
        buy(player);
        user.townOrPokeCenter(player);

    }

    public void sell(Player player) throws InterruptedException {
        FileIO file = new FileIO();
        ArrayList<Item> playerBag = file.readItemsFromBag("Data/Bag.csv");
        file.saveItemsToBag("Data/Bag.csv", player.getBag());


        System.out.println(playerBag);

        if (playerBag.isEmpty()) {
            textUI.displayMsg("You have no items to sell.");
            user.pokeMartOptions(player);
        }

        textUI.displayMsg("Here's what you have for sale:");
        int index = 1;
        for (Item item : playerBag) {
            textUI.displayMsg(index++ + ". " + item.getName() + " - P$" + (item.getPrice() / 2));
        }
        textUI.displayMsg("Enter the number of the item you want to sell, or 0 to exit: ");

        String choiceInput = textUI.userInput();

        try {
            int choice = Integer.parseInt(choiceInput);
            if (choice == 0) {
                textUI.displayMsg("Thank you for visiting the PokéMart!");
                return;
            }

            if (choice < 1 || choice > playerBag.size()) {
                textUI.displayMsg("Oak: That isn't the right input!");
                return;
            }


            Item selectedItem = playerBag.get(choice - 1);
            int sellPrice = selectedItem.getPrice() / 2;

            player.removeItemFromBag(selectedItem);
            player.addFunds(sellPrice);
            file.saveItemsToBag("Data/Bag.csv", playerBag);
            textUI.displayMsg("You have sold a " + selectedItem.getName() + " for P$" + sellPrice + ".");
            textUI.displayMsg("Remaining balance: P$" + player.getWallet());
        } catch (NumberFormatException e) {
            textUI.displayMsg("Oak: That isn't the right input!");
        }
        user.userOptions(player);
    }
}
