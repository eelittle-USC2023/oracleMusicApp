package com.musicapp;

import org.jfugue.player.Player;

public class Music {
    private static Player player = new Player();
    public static void play(String notes) {
        player.play(notes);
    }
}
