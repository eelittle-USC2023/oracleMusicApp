package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataLoader extends DataConstants {
    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileReader reader = new FileReader(ACCOUNT_FILE_NAME);
            JSONArray accountsJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < accountsJSON.size(); i++) {
                JSONObject accountJSON = (JSONObject)accountsJSON.get(i);
                String username = (String)accountJSON.get(ACCOUNT_USERNAME);
                String password = (String)accountJSON.get(ACCOUNT_PASSWORD);
                String role = (String)accountJSON.get(ACCOUNT_ROLE);
                JSONArray savedSongsIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_SAVED_SONGS);
                ArrayList<Song> savedSongs = new ArrayList<Song>();
                for (int j = 0; j < savedSongsIDsJSON.size(); i++) {
                    savedSongs.add(SongList.getInstance().getSong(UUID.fromString((String)savedSongsIDsJSON.get(j))));
                }
                JSONArray savedLessonsIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_SAVED_LESSONS);
                ArrayList<Lesson> savedLessons = new ArrayList<Lesson>();
                for (int j = 0; j < savedSongsIDsJSON.size(); i++) {
                    savedLessons.add(LessonList.getInstance().getLesson(UUID.fromString((String)savedLessonsIDsJSON.get(j))));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return accounts;
    }

    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
			JSONArray songsJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < songsJSON.size(); i++ ) {
                JSONObject songJSON = (JSONObject)songsJSON.get(i);
                UUID id = UUID.fromString((String)songJSON.get(SONG_ID));
                String title = (String)songJSON.get(SONG_TITLE);
                String artistName = (String)songJSON.get(SONG_ARTIST_NAME);
                String difficulty = (String)songJSON.get(SONG_DIFFICULTY);
                String genre = (String)songJSON.get(SONG_GENRE);
                Instrument instrumentString = instrumentFromString((String)songJSON.get(SONG_INSTRUMENT));
                ArrayList<Measure> measures = new ArrayList<Measure>();
                JSONArray measuresJSON = (JSONArray)songJSON.get(SONG_MEASURES);
                for (int j = 0; j < measuresJSON.size(); j++) {
                    JSONObject measureJSON = (JSONObject)measuresJSON.get(j);
                    int timeSignatureTop = ((Long)measureJSON.get(MEASURE_TIME_SIGNATURE_TOP)).intValue();
                    int timeSignatureBottom =((Long)measureJSON.get(MEASURE_TIME_SIGNATURE_BOTTOM)).intValue();
                    String keySignature = (String)measureJSON.get(MEASURE_KEY_SIGNATURE);
                    ArrayList<Note> notes = new ArrayList<Note>();
                    JSONArray notesJSON = (JSONArray)measureJSON.get(MEASURE_NOTES);
                    for (int k = 0; k < notesJSON.size(); k++) {
                        JSONObject noteJSON = (JSONObject)notesJSON.get(k);
                        String noteName = (String)noteJSON.get(NOTE_NAME);
                        int octave = ((Long)noteJSON.get(NOTE_OCTAVE)).intValue();
                        int lengthNumerator = ((Long)noteJSON.get(NOTE_LENGTH_NUMERATOR)).intValue();
                        int lengthDenominator = ((Long)noteJSON.get(NOTE_LENGTH_DENOMINATOR)).intValue();
                        notes.add(new Note(noteName, octave, lengthNumerator, lengthDenominator));
                    }
                    measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, notes));
                }
                songs.add(new Song(id, title, artistName, difficulty, genre, instrumentString, measures));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
    private static Instrument instrumentFromString(String s) {
        if (s.equals("Guitar")) {
            return new Guitar();
        } else {
            return null;
        }
    }
    public static ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();

        return lessons;
    }
    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();

        return questions;
    }

    public static void main(String[] args) {
        ArrayList<Song> songs = DataLoader.getSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
    }

}