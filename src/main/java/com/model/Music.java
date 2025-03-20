package com.model;

import org.jfugue.player.Player;

public class Music {
    private static Player player = new Player();
    public static void play(String song) {
        player.play(song);
    }
}
