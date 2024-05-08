import java.util.ArrayList;
import java.util.Random;

public class Combat {
    private TextUI textUI = new TextUI();
    private Town town = new Town();
    private FileIO fileIO = new FileIO();
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
            primaryPlayerPokemon = playerPokemonList.get(0);
        }

        if (enemyPokemonList.isEmpty()) {
            textUI.displayMsg("No enemy Pokémon data loaded. Check file path: " + enemyPokemonFile);
        }
    }


    public void battleRound(Player player, Player enemy) {
        Pokemon playerPokemon = primaryPlayerPokemon;
        Pokemon enemyPokemon = getRandomPokemon();
        textUI.displayMsg("A battle has begun between " + playerPokemon.getName() + " and " + enemyPokemon.getName() + "!");
        boolean playerTurn = true;

        while (playerPokemon.getHp() > 0 && enemyPokemon.getHp() > 0) {
            if (playerTurn) {
                textUI.displayMsg(player.getName() + "'s " + playerPokemon.getName() + " attacks " + enemyPokemon.getName() + "!");
                damageToOpponent(playerPokemon, enemyPokemon, player);

                if (enemyPokemon.getHp() <= 0) {
                    break;
                }
            } else {
                textUI.displayMsg(enemy.getName() + "'s " + enemyPokemon.getName() + " attacks " + playerPokemon.getName() + "!");
                damageToPlayerPokemon(enemyPokemon, playerPokemon, player);

                if (playerPokemon.getHp() <= 0) {
                    break;
                }
            }
            playerTurn = !playerTurn;
        }

        if (playerPokemon.getHp() > 0) {
            textUI.displayMsg(player.getName() + "'s " + playerPokemon.getName() + " wins the battle!");
        } else {
            textUI.displayMsg(enemy.getName() + "'s " + enemyPokemon.getName() + " wins the battle!");
        }
    }


    public int calculateDamage(Pokemon attacker, Pokemon defender) {
        double defenseScaling = 1.0;
        if (defender.getDefensePower() >= 40 && defender.getDefensePower() < 80) {
            defenseScaling = 0.8;
        } else if (defender.getDefensePower() >= 80 && defender.getDefensePower() < 120) {
            defenseScaling = 0.7;
        } else if (defender.getDefensePower() >= 120 && defender.getDefensePower() < 160) {
            defenseScaling = 0.6;
        } else if (defender.getDefensePower() >= 160 && defender.getDefensePower() < 200) {
            defenseScaling = 0.55;
        } else if (defender.getDefensePower() > 200) {
            defenseScaling = 0.50;
        }

        double typeMultiplier = typeMultiplier(attacker.getTypeOne(), defender.getTypeOne());

        return (int) (attacker.getAttackPower() * defenseScaling * typeMultiplier);

    }

    // personal note for asimovich maybe this needs to be looked over again since it might not work as intended
    private double typeMultiplier(String attackerType, String defenderType) {
        switch (attackerType) {
            case "Normal":
                if (defenderType.equals("Rock") || defenderType.equals("Ghost")) return 0.0;
                break;
            case "Fire":
                if (defenderType.equals("Fire") || defenderType.equals("Water") || defenderType.equals("Rock") || defenderType.equals("Dragon")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Ice") || defenderType.equals("Bug")) return 2.0;
                break;
            case "Water":
                if (defenderType.equals("Water") || defenderType.equals("Grass") || defenderType.equals("Dragon")) return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Ground") || defenderType.equals("Rock")) return 2.0;
                break;
            case "Electric":
                if (defenderType.equals("Electric") || defenderType.equals("Grass") || defenderType.equals("Dragon")) return 0.5;
                if (defenderType.equals("Water") || defenderType.equals("Flying")) return 2.0;
                if (defenderType.equals("Ground")) return 0.0;
                break;
            case "Grass":
                if (defenderType.equals("Fire") || defenderType.equals("Grass") || defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Bug") || defenderType.equals("Dragon")) return 0.5;
                if (defenderType.equals("Water") || defenderType.equals("Ground") || defenderType.equals("Rock")) return 2.0;
                break;
            case "Ice":
                if (defenderType.equals("Water") || defenderType.equals("Ice")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Ground") || defenderType.equals("Flying") || defenderType.equals("Dragon")) return 2.0;
                break;
            case "Fighting":
                if (defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Psychic") || defenderType.equals("Bug")) return 0.5;
                if (defenderType.equals("Normal") || defenderType.equals("Ice") || defenderType.equals("Rock")) return 2.0;
                if (defenderType.equals("Ghost")) return 0.0;
                break;
            case "Poison":
                if (defenderType.equals("Poison") || defenderType.equals("Ground") || defenderType.equals("Rock") || defenderType.equals("Ghost")) return 0.5;
                if (defenderType.equals("Grass")) return 2.0;
                break;
            case "Ground":
                if (defenderType.equals("Grass") || defenderType.equals("Bug")) return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Electric") || defenderType.equals("Poison") || defenderType.equals("Rock")) return 2.0;
                if (defenderType.equals("Flying")) return 0.0;
                break;
            case "Flying":
                if (defenderType.equals("Electric") || defenderType.equals("Rock")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Fighting") || defenderType.equals("Bug")) return 2.0;
                break;
            case "Psychic":
                if (defenderType.equals("Psychic")) return 0.5;
                if (defenderType.equals("Fighting") || defenderType.equals("Poison")) return 2.0;
                if (defenderType.equals("Ghost")) return 0.0;
                break;
            case "Bug":
                if (defenderType.equals("Fire") || defenderType.equals("Fighting") || defenderType.equals("Poison") || defenderType.equals("Flying") || defenderType.equals("Ghost")) return 0.5;
                if (defenderType.equals("Grass") || defenderType.equals("Psychic")) return 2.0;
                break;
            case "Rock":
                if (defenderType.equals("Fighting") || defenderType.equals("Ground")) return 0.5;
                if (defenderType.equals("Fire") || defenderType.equals("Ice") || defenderType.equals("Flying") || defenderType.equals("Bug")) return 2.0;
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

    public void damageToOpponent(Pokemon attacker, Pokemon defender, Player attackerPlayer) {
        int damage = calculateDamage(attacker, defender);
        defender.setHp(Math.max(defender.getHp() - damage, 0));
        textUI.displayMsg(defender.getName() + " took " + damage + " damage!");

        if (defender.getHp() <= 0) {
            textUI.displayMsg(defender.getName() + " fainted!");
            enemyDefeat(attacker, attackerPlayer);
        }
    }

    public void damageToPlayerPokemon(Pokemon enemy, Pokemon playerPokemon, Player player) {
        int damage = calculateDamage(enemy, playerPokemon);
        playerPokemon.setHp(Math.max(playerPokemon.getHp() - damage, 0));
        textUI.displayMsg(playerPokemon.getName() + " took " + damage + " damage from " + enemy.getName() + "!");

        if (playerPokemon.getHp() <= 0) {
            playerPokemonFaint(player);
        }
    }


    private void enemyDefeat(Pokemon attacker, Player attackerPlayer) {
        textUI.displayMsg("Enemy " + attacker.getName() + " defeated!");
        attackerPlayer.addFunds(500);
        attacker.levelUp();
    }

    private void playerPokemonFaint(Player player) {
        textUI.displayMsg("One of " + player.getName() + "'s Pokémon has fainted.");

        if (pokemonsDefeated(player)) {
            textUI.displayMsg("All of " + player.getName() + "'s Pokémon have fainted! Sending to the PokéCenter...");
            town.pokeCenter(player);
            playerDefeatPenalty(player);
        }
    }

    private boolean pokemonsDefeated(Player player) {
        for (Pokemon pokemon : player.getPokemonParty()) {
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
    private Pokemon getRandomPokemon() {
        Random random = new Random();
        return enemyPokemonList.get(random.nextInt(enemyPokemonList.size()));
    }

    public Pokemon getPrimaryPlayerPokemon() {
        return primaryPlayerPokemon;
    }
}
