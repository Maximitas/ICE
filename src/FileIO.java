import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileIO {
    public ArrayList<Pokemon> loadPokemonFromFile(String filePath) {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filePath))) {
            scan.nextLine();

            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");

                int id = Integer.parseInt(line[0]);
                int hp = Integer.parseInt(line[1]);
                int maxHp = Integer.parseInt(line[2]);  // Adjust index as necessary based on your file structure
                int lvl = Integer.parseInt(line[3]);
                int attackPower = Integer.parseInt(line[4]);
                int defensePower = Integer.parseInt(line[5]);
                String name = line[6];
                String typeOne = line[7];
                String typeTwo = line[8];
                String abilities = line[9];
                String rarity = line[10];

                pokemonList.add(new Pokemon(id, hp, maxHp, lvl, attackPower, defensePower, name, typeOne, typeTwo, abilities, rarity));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing value");
        }
        return pokemonList;
    }

    public void clearPlayerPokemonFile(String filePath){
        try (FileWriter writer = new FileWriter(filePath, false)){
            writer.write("pokemonID,hp,maxHp,lvl,attackPower,defensePower,name,typeOne,typeTwo,abilities,rarity");

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void clearBagFile(String filePath){
        try (FileWriter writer = new FileWriter(filePath, false)){
            writer.write("name,price");

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void clearPlayerFile(String filePath){
        try (FileWriter writer = new FileWriter(filePath, false)){
            writer.write("name,balance");

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void saveItemToBag(String filePath, Item item) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.write("\n" + item.getName() + "," + item.getPrice());

        } catch (IOException e) {
            System.out.println("Error writing items to file: " + e.getMessage());
        }
    }

    public void saveItemsToBag(String filepath, ArrayList<Item> items) {
        try (FileWriter writer = new FileWriter(filepath, false)) {
            writer.write("name,price");
            for (Item item : items) {
                writer.write("\n" + item.getName() + "," + item.getPrice());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void savePokemonToPlayerPokemons(String filePath, Pokemon playerPokemon) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("\n" + playerPokemon.getPokemonID() + "," + playerPokemon.getHp() + "," + playerPokemon.getMaxHp() + "," +playerPokemon.getLvl() + "," +
                    playerPokemon.getAttackPower() + "," + playerPokemon.getDefensePower() + "," +
                    playerPokemon.getName() + "," + playerPokemon.getTypeOne() + "," + playerPokemon.getTypeTwo() + "," +
                    playerPokemon.getAbilities() + "," + playerPokemon.getRarity());

        } catch (IOException e) {
            System.out.println("Error writing Pokémon to file: " + e.getMessage());
        }

    }

    public ArrayList<Item> readItemsFromBag(String filePath) {
        ArrayList<Item> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);

                items.add(new Item(name, price));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing value: " + e.getMessage());
        }
    return items;
    }

    public void savePokemonData(String filePath, ArrayList<Pokemon> pokemons) {
        clearPlayerPokemonFile(filePath);
            for (Pokemon p : pokemons) {
                savePokemonToPlayerPokemons(filePath, p);
            }

    }

    public ArrayList<String> readPlayerData(String filePath) {
        ArrayList<String> playerData = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filePath))) {
            scan.nextLine();
            if (scan.hasNextLine()) {
                String[] parts = scan.nextLine().split(",");
            String name = parts[0];
            String balance = parts[1];

            playerData.add(name);
            playerData.add(balance);
        } else {
                System.out.println("There is no save");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return playerData;
    }

    public void savePlayerData(String filePath, String name, int balance) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("\n" + name + "," + balance);

        } catch (IOException e) {
            System.out.println("Error writing items to file: " + e.getMessage());
        }

    }

}