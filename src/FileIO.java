import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileIO {

    public void loadPokemonFromFile(String filePath, ArrayList<Pokemon> pokemonList) {
        try (Scanner scan = new Scanner(new File(filePath))) {
            scan.nextLine();

            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");

                int id = Integer.parseInt(line[0]);
                int hp = Integer.parseInt(line[1]);
                int lvl = Integer.parseInt(line[2]);
                int attackPower = Integer.parseInt(line[3]);
                int defensePower = Integer.parseInt(line[4]);
                String name = line[5];
                String typeOne = line[6];
                String typeTwo = line[7];
                String abilities = line[8];
                String rarity = line[9];

                pokemonList.add(new Pokemon(id, hp, lvl, attackPower, defensePower, name, typeOne, typeTwo, abilities, rarity));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing value");
        }

    }

    public void saveItemsToBag(String filePath, ArrayList<Item> items) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("name,value\n");

            for (Item item : items) {
                writer.write(item.getName() + "," + item.getPrice() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing items to file: " + e.getMessage());
        }
    }

    public void savePokemonToPlayerPokemons(String filePath, ArrayList<Pokemon> pokemons) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("pokemonID,hp,lvl,attackPower,defensePower,name,typeOne,typeTwo,abilities,rarity\n");
            for (Pokemon pokemon : pokemons) {
                writer.write(pokemon.getName() + "," + pokemon.getTypeOne() + "," +
                        pokemon.getHp() + "," + pokemon.getAttackPower() + "," +
                        pokemon.getDefensePower() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing Pokémon to file: " + e.getMessage());
        }
    }

    public void readItemsFromBag(String filePath, ArrayList<Item> items) {
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
    }

    public void readPokemonFromPlayerPokemons(String filePath, ArrayList<Pokemon> pokemons) {
        try (Scanner scan = new Scanner(new File(filePath))) {
            scan.nextLine();

            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");

                int id = Integer.parseInt(line[0]);
                int hp = Integer.parseInt(line[1]);
                int lvl = Integer.parseInt(line[2]);
                int attackPower = Integer.parseInt(line[3]);
                int defensePower = Integer.parseInt(line[4]);
                String name = line[5];
                String typeOne = line[6];
                String typeTwo = line[7];
                String abilities = line[8];
                String rarity = line[9];

                pokemons.add(new Pokemon(id, hp, lvl, attackPower, defensePower, name, typeOne, typeTwo, abilities, rarity));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing value");
        }

    }
}