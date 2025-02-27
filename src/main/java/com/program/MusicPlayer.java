package com.program;

import com.musicapp.Music;
import java.lang.Thread;

public class MusicPlayer {
    private static String guitar = "I[Guitar] ";
    private static String timeSignature = "3/4 ";
    private static String happyBirthday = "Ge. Gs | Aq Gq Cq | Bh Ge. Gs | Aq Gq Dq | Ch Ge. Gs | Gq Eq Cq | Bq Gq Fe. Fs | Eq Cq Dq | Ch";


    public static void main(String[] args) {
        try {
            Music.play(guitar + timeSignature + happyBirthday);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
