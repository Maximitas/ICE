import java.util.Random;

public class Pokemon {
    TextUI textUI = new TextUI();

    private int pokemonID;
    private int hp;
    private int maxHp;
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
        this.maxHp = hp;
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

    public void setHp(int hp) {
        this.hp = hp;
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

    public void restoreHP() {
        this.hp = maxHp;
        textUI.displayMsg(this.name + " has been fully healed.");
    }
        public void levelUp () {
            Random rand = new Random();
            this.lvl++;
            this.attackPower += rand.nextInt(6) + 1;
            this.defensePower += rand.nextInt(6) + 1;
            this.hp += rand.nextInt(4) + 1;

            textUI.displayMsg(this.getName() + " leveled up to " + this.getLvl());
            textUI.displayMsg("New stats: Attack Power = " + this.attackPower + ", Defense Power = " + this.defensePower + ", HP = " + this.hp);
        }
    }

