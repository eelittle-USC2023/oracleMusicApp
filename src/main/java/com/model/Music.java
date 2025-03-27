package com.model;

import org.jfugue.player.Player;

/**
 * Creates a new player to play the JFugue song
 * @author Ethan Little / Ally Blackwell
 */
public class Music {
    private static Player player = new Player();
    public static void play(String song) {
        player.play(song);
    }
}
