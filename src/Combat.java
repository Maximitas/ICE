import java.util.ArrayList;
import java.util.Random;

public class Combat {
    private TextUI textUI = new TextUI();
    private Town town = new Town();
    private FileIO fileIO = new FileIO();
    private UserInterface user = new UserInterface();
    private ArrayList<Pokemon> enemyPokemonList = new ArrayList<>();
    private ArrayList<Pokemon> playerPokemonList;
    private Pokemon primaryPlayerPokemon;
    String playerPokemonFile = "Data/PlayerPokemons.csv";
    String enemyPokemonFile = "Data/Pokemon.csv";


    public Combat() {
        this.playerPokemonList = fileIO.loadPokemonFromFile(playerPokemonFile);
        this.enemyPokemonList = fileIO.loadPokemonFromFile(enemyPokemonFile);

        if (playerPokemonList.isEmpty()) {
            textUI.displayMsg("No player Pokémon data loaded. Check file path: " + playerPokemonFile);
        } else {
            primaryPlayerPokemon = playerPokemonList.getFirst();
        }

        if (enemyPokemonList.isEmpty()) {
            textUI.displayMsg("No enemy Pokémon data loaded. Check file path: " + enemyPokemonFile);
        }
    }

    public void battleRound(Player player, Pokemon enemyPokemon) throws InterruptedException {
        if (enemyPokemon == null) {
            textUI.displayMsg("No enemy Pokémon available.");
            return;
        }

        if (primaryPlayerPokemon.getHp() > 0 && enemyPokemon.getHp() > 0) {
            performPlayerTurn(player, enemyPokemon);
        }
        if (enemyPokemon.getHp() > 0) {
                performEnemyTurn(enemyPokemon);
        }

        finalizeBattle(player, enemyPokemon);
    }

    private void performPlayerTurn(Player player, Pokemon enemyPokemon) {
        textUI.displayMsg(player.getName() + "'s " + primaryPlayerPokemon.getName() + " attacks!");
        dealDamage(primaryPlayerPokemon, enemyPokemon);
        displayEnemyPokemonStatus(enemyPokemon);
        if (enemyPokemon.getHp() <= 0) {
            enemyDefeat(primaryPlayerPokemon, player, enemyPokemon);
        }
    }

    private void performEnemyTurn(Pokemon enemyPokemon) {
        textUI.displayMsg("Wild " + enemyPokemon.getName() + " attacks!");
        dealDamage(enemyPokemon, primaryPlayerPokemon);
        displayPlayerPokemonStatus(primaryPlayerPokemon);

    }

    private void finalizeBattle(Player player, Pokemon enemyPokemon) throws InterruptedException {
        if (primaryPlayerPokemon.getHp() <= 0) {
            textUI.displayMsg(player.getName() + "'s " + primaryPlayerPokemon.getName() + " has fainted!");
            playerPokemonFaint(player);
        } else if (enemyPokemon.getHp() <= 0) {
            user.userOptions(player);
        }
    }

    private void displayPlayerPokemonStatus(Pokemon playerPokemon) {
        textUI.displayMsg("Status:");
        textUI.displayMsg(playerPokemon.getName() + " (Lvl " + playerPokemon.getLvl() + "): HP " + playerPokemon.getHp());

    }

    private void displayEnemyPokemonStatus(Pokemon enemyPokemon) {
        textUI.displayMsg("Status:");
        textUI.displayMsg(enemyPokemon.getName() + " (Lvl " + enemyPokemon.getLvl() + "): HP " + enemyPokemon.getHp());
    }

    public void dealDamage(Pokemon attacker, Pokemon defender) {
        int damage = calculateDamage(attacker, defender);
        defender.setHp(Math.max(defender.getHp() - damage, 0));
        textUI.displayMsg(defender.getName() + " took " + damage + " damage!");

        fileIO.savePokemonData(playerPokemonFile, playerPokemonList);
    }

    public int calculateDamage(Pokemon attacker, Pokemon defender) {
        double defenseScaling = calculateDefenseScaling(defender.getDefensePower());
        double typeMultiplier = typeMultiplier(attacker.getTypeOne(), defender.getTypeOne());
        return (int) (attacker.getAttackPower() * defenseScaling * typeMultiplier);
    }

    private double calculateDefenseScaling(int defensePower) {
        if (defensePower >= 200) {
            return 0.01;
        } else if (defensePower >= 160 && defensePower < 200) {
            return 0.05;
        } else if (defensePower >= 120 && defensePower < 160) {
            return 0.1;
        } else if (defensePower >= 80 && defensePower < 120) {
            return 0.2;
        } else if (defensePower >= 40 && defensePower < 80) {
            return 0.3;
        } else {
            return 0.4;
        }
    }

    private double typeMultiplier(String attackerType, String defenderType) {
        switch (attackerType) {
            case "Normal":
                if (defenderType.equals("Rock") || defenderType.equals("Ghost")) return 0.0;
                break;
            case "Fire":
                if (defenderType.equals("Fire") || defenderType.equals("Water") || defenderType.equals("Rock") || defenderType.equals("Dragon"))
                    return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Ice") || defenderType.equals("Bug"))
                    return 2.0;
                break;
            case "Water":
                if (defenderType.equals("Water") || defenderType.equals("Grass") || defenderType.equals("Dragon"))
                    return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Ground") || defenderType.equals("Rock"))
                    return 2.0;
                break;
            case "Electric":
                if (defenderType.equals("Electric") || defenderType.equals("Grass") || defenderType.equals("Dragon"))
                    return 0.5;
                if (defenderType.equals("Water") || defenderType.equals("Flying")) return 2.0;
                if (defenderType.equals("Ground")) return 0.0;
                break;
            case "Grass":
                if (defenderType.equals("Fire") || defenderType.equals("Grass") || defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Bug") || defenderType.equals("Dragon"))
                    return 0.5;
                if (defenderType.equals("Water") || defenderType.equals("Ground") || defenderType.equals("Rock"))
                    return 2.0;
                break;
            case "Ice":
                if (defenderType.equals("Water") || defenderType.equals("Ice")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Ground") || defenderType.equals("Flying") || defenderType.equals("Dragon"))
                    return 2.0;
                break;
            case "Fighting":
                if (defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Psychic") || defenderType.equals("Bug"))
                    return 0.5;
                if (defenderType.equals("Normal") || defenderType.equals("Ice") || defenderType.equals("Rock"))
                    return 2.0;
                if (defenderType.equals("Ghost")) return 0.0;
                break;
            case "Poison":
                if (defenderType.equals("Poison") || defenderType.equals("Ground") || defenderType.equals("Rock") || defenderType.equals("Ghost"))
                    return 0.5;
                if (defenderType.equals("Grass")) return 2.0;
                break;
            case "Ground":
                if (defenderType.equals("Grass") || defenderType.equals("Bug")) return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Electric") || defenderType.equals("Poison") || defenderType.equals("Rock"))
                    return 2.0;
                if (defenderType.equals("Flying")) return 0.0;
                break;
            case "Flying":
                if (defenderType.equals("Electric") || defenderType.equals("Rock")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Fighting") || defenderType.equals("Bug"))
                    return 2.0;
                break;
            case "Psychic":
                if (defenderType.equals("Psychic")) return 0.5;
                if (defenderType.equals("Fighting") || defenderType.equals("Poison")) return 2.0;
                if (defenderType.equals("Ghost")) return 0.0;
                break;
            case "Bug":
                if (defenderType.equals("Fire") || defenderType.equals("Fighting") || defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Ghost"))
                    return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Psychic")) return 2.0;
                break;
            case "Rock":
                if (defenderType.equals("Fighting") || defenderType.equals("Ground")) return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Ice") || defenderType.equals("Flying") || defenderType.equals("Bug"))
                    return 2.0;
                break;
            case "Ghost":
                if (defenderType.equals("Normal")) return 0.0;
                if (defenderType.equals("Psychic") || defenderType.equals("Ghost")) return 2.0;
                break;
            case "Dragon":
                if (defenderType.equals("Dragon")) return 2.0;
                break;
        }
        return 1.0;
    }


    private void enemyDefeat(Pokemon playerPokemon, Player player, Pokemon enemyPokemon) {
        textUI.displayMsg("Enemy " + enemyPokemon.getName() + " defeated!");
        player.addFunds(500);
        playerPokemon.levelUp();
        fileIO.savePokemonData(playerPokemonFile, playerPokemonList);

    }

    private void playerPokemonFaint(Player player) throws InterruptedException {
        textUI.displayMsg("One of " + player.getName() + "'s Pokémon has fainted.");

        if (pokemonsDefeated(player)) {
            textUI.displayMsg("All of " + player.getName() + "'s Pokémon have fainted! Sending to the PokéCenter...");
            town.pokeCenter(player);
            playerDefeatPenalty(player);
            user.userOptions(player);
        }
    }

    private boolean pokemonsDefeated(Player player) {
        for (Pokemon pokemon : player.getCurrentParty()) {
            if (pokemon.getHp() > 0) {
                return false;
            }
        }
        return true;
    }

    private void playerDefeatPenalty(Player player) {
        int penalty = 500;
        if (player.getWallet() >= penalty) {
            player.removeFunds(penalty);
            textUI.displayMsg("You lost " + penalty + " currency as a penalty.");
        } else {
            textUI.displayMsg("You don't have enough currency to pay the penalty.");
        }
    }

    Pokemon getRandomPokemon() {
        Random random = new Random();
        return enemyPokemonList.get(random.nextInt(enemyPokemonList.size()));
    }

    public Pokemon getPrimaryPlayerPokemon() {
        return primaryPlayerPokemon;

    }

    Pokemon getSpecificPokemonMewTo() {
        Random random = new Random();
       random.nextInt(enemyPokemonList.size());
        return enemyPokemonList.get(149);
    }
    Pokemon getSpecificPokemonDarkcry() {
        Random random = new Random();
        random.nextInt(enemyPokemonList.size());
        return enemyPokemonList.get(151);
    }
    Pokemon getSpecificPokemonGyarados() {
        Random random = new Random();
        random.nextInt(enemyPokemonList.size());
        return enemyPokemonList.get(129);
    }
}