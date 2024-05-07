import java.util.ArrayList;

public class Town {
    private TextUI textUI = new TextUI();
    private String name;
    private int price;

    public void pokeCenter(Player player) {
        ArrayList<Pokemon> pokemons = player.getPokemonParty();
        for (Pokemon pokemon : pokemons) {
            pokemon.restoreHP();
        }
        textUI.displayMsg("All Pokémon have been fully healed at the PokéCenter!");
    }
        public void pokeMart(Player player) {
            textUI.displayMsg("Welcome to the PokéMart!");
            textUI.displayMsg("Would you like to (1) Buy or (2) Sell items? Enter 0 to exit: ");
            String actionInput = textUI.userInput();

            try {
                int action = Integer.parseInt(actionInput);
                if (action == 1) {
                    buy(player);
                } else if (action == 2) {
                    sell(player);
                } else {
                    textUI.displayMsg("Thank you for visiting the PokéMart!");
                }
            } catch (NumberFormatException e) {
                textUI.displayMsg("Invalid input. Please enter a valid number.");
            }
        }

        private void buy(Player player) {
            Item[] itemsForSale = {
                    new Item("Poké Ball", 200),
                    new Item("Potion", 100),
                    new Item("Super Potion", 500),
                    new Item("Revive", 1500)
            };

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
                    return;
                }

                if (choice < 1 || choice > itemsForSale.length) {
                    textUI.displayMsg("Invalid selection.");
                    return;
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
                textUI.displayMsg("Invalid input. Please enter a valid number.");
            }
        }

        private void sell(Player player) {
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

            String choiceInput = textUI.userInput();

            try {
                int choice = Integer.parseInt(choiceInput);
                if (choice == 0) {
                    textUI.displayMsg("Thank you for visiting the PokéMart!");
                    return;
                }

                if (choice < 1 || choice > playerBag.size()) {
                    textUI.displayMsg("Invalid selection.");
                    return;
                }

                Item selectedItem = playerBag.get(choice - 1);
                int sellPrice = selectedItem.getPrice() / 2;

                player.removeItemFromBag(selectedItem);
                player.addFunds(sellPrice);
                textUI.displayMsg("You have sold a " + selectedItem.getName() + " for P$" + sellPrice + ".");
                textUI.displayMsg("Remaining balance: P$" + player.getWallet());
            } catch (NumberFormatException e) {
                textUI.displayMsg("Invalid input. Please enter a valid number.");
            }
        }
    }
