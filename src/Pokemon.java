import java.util.Random;

public class Pokemon {

    private int pokemonID;
    private int hp;
    private int lvl;
    private int attackPower;
    private int defensePower;
    private String name;
    private String typeOne;
    private String typeTwo;
    private String abilities;
    private String rarity;

    public Pokemon(int pokemonID, int hp, int lvl, int attackPower, int defensePower, String name, String typeOne, String typeTwo, String abilities, String rarity) {
        this.pokemonID = pokemonID;
        this.hp = hp;
        this.lvl = lvl;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.name = name;
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.abilities = abilities;
        this.rarity = rarity;
    }

    public int getPokemonID() {
        return pokemonID;
    }

    public int getHp() {
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public String getName() {
        return name;
    }

    public String getTypeOne() {
        return typeOne;
    }

    public String getTypeTwo() {
        return typeTwo;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getRarity() {
        return rarity;
    }


    public void levelUp() {
        Random rand = new Random();
        this.lvl++;
        this.attackPower += rand.nextInt(6) + 1;
        this.defensePower += rand.nextInt(6) + 1;
        this.hp += rand.nextInt(4)+1;
    }

}
