package com.model;

import java.util.ArrayList;

public class SongCreator 
{
    private Song song;
    private int selectedMeasureIndex;

    public SongCreator(String title, String artist) {
        SongList.getInstance().addSong(title, artist);
        selectedMeasureIndex = 0;
    }
    public SongCreator(Song song) {
        this.song = song;
        selectedMeasureIndex = 0;
    }
    public void addMeasure(int timeSignatureTop, int timeSignatureBottom, String keySignature)
    {
        song.addMeasure(timeSignatureTop, timeSignatureBottom, keySignature);
        selectedMeasureIndex = song.getSong().size() - 1;
    }
    public void deleteSelectedMeasure()
    {

    }
    public void editSelectedMeasure()
    {

    }
    public void addNoteToSelectedMeasure(String name, int octave, double length, double position) {
        song.getMeasure(selectedMeasureIndex).addNote(name, octave, length, position);
    }
}