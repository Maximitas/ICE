import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileIO {

    public void loadPokemonFromFile(String filePath, ArrayList<Pokemon> pokemonList) {
        try (Scanner scan = new Scanner(new File(filePath))) {
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

                pokemonList.add(new Pokemon(id,hp,lvl,attackPower,defensePower,name,typeOne,typeTwo,abilities,rarity));

            }
    } catch (FileNotFoundException e) {
        System.out.println("File not found");
    } catch (NumberFormatException e) {
        System.out.println("Error parsing value");
    }
    }
}
