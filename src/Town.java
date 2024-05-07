import java.util.ArrayList;
import java.util.Scanner;

public class Town {
    private TextUI textUI = new TextUI();
    private String name;
    private int price;

    public void pokeCenter(Player player) {
        ArrayList<Pokemon> pokemons = player.getPokemonList();
        for (Pokemon pokemon : pokemons) {
            pokemon.restoreHP();
        }
        textUI.displayMsg("All Pokémon have been fully healed at the PokéCenter!");

    }

    public void pokeMart(Player player) {
        Scanner scanner = new Scanner(System.in);

        textUI.displayMsg("Welcome to the PokéMart!");
        textUI.displayMsg("Would you like to (1) Buy or (2) Sell items? Enter 0 to exit: ");
        int action = scanner.nextInt();

        if (action == 1) {
            buy(player, scanner);
        } else if (action == 2) {
            sell(player, scanner);
        } else {
            textUI.displayMsg("Thank you for visiting the PokéMart!");
        }
    }

    private void buy(Player player, Scanner scanner) {
        Item[] itemsForSale = {
                new Item("Poké Ball", 200),
                new Item("Potion", 100),
                new Item("Super Potion", 500),
                new Item("Revive", 1500)
        };

        textUI.displayMsg("Here's what we have for sale:");
        int index = 1;
        for (Item item : itemsForSale) {
            textUI.displayMsg(index++ + ". " + item.getName() + " - $" + item.getPrice());
        }
        textUI.displayMsg("Your current balance: $" + player.getWallet());
        textUI.displayMsg("Enter the number of the item you want to buy, or 0 to exit: ");

        int choice = scanner.nextInt();
        if (choice == 0) {
            textUI.displayMsg("Thank you for visiting the PokéMart!");
            return;
        }

        Item selectedItem = itemsForSale[choice - 1];
        if (player.getWallet() >= selectedItem.getPrice()) {
            player.setWallet(player.getWallet() - selectedItem.getPrice());
            textUI.displayMsg("You have bought a " + selectedItem.getName() + ".");
            textUI.displayMsg("Remaining balance: $" + player.getWallet());
            player.addItemToBag(selectedItem);
        } else {
            textUI.displayMsg("You do not have enough money to buy this item.");
        }
    }

    private void sell(Player player, Scanner scanner) {
        ArrayList<Item> playerBag = player.getBag();

        if (playerBag.isEmpty()) {
            textUI.displayMsg("You have no items to sell.");
            return;
        }

        textUI.displayMsg("Here's what you have for sale:");
        int index = 1;
        for (Item item : playerBag) {
            textUI.displayMsg(index++ + ". " + item.getName());
        }
        textUI.displayMsg("Enter the number of the item you want to sell, or 0 to exit: ");

        int choice = scanner.nextInt();
        if (choice == 0) {
            textUI.displayMsg("Thank you for visiting the PokéMart!");
            return;
        }

        Item selectedItem = playerBag.get(choice - 1);

        int sellPrice = selectedItem.getPrice() / 2;

        player.removeItemFromBag(selectedItem);
        player.addFunds(sellPrice);
        textUI.displayMsg("You have sold a " + selectedItem.getName() + " for $" + sellPrice + ".");
        textUI.displayMsg("Remaining balance: $" + player.getWallet());
    }
}