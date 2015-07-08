package io.mstream.roulette.domain.roulette;


import io.mstream.roulette.command.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Roulette extends Observable {

    private final NumbersGenerator numbersGenerator;
    private final Map<String, Player> players = new HashMap<>();

    public Roulette(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void addPlayer(Player player) {
        if (players.containsKey(player.getName())) {
            throw new IllegalArgumentException();
        }
        players.put(player.getName(), player);
    }

    public void roll() {
    }

}
