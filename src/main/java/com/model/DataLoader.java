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
                if(role.equals("Student")) {
                    JSONArray savedSongIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_SAVED_SONGS);
                    ArrayList<Song> savedSongs = new ArrayList<Song>();
                    for (int j = 0; j < savedSongIDsJSON.size(); j++) {
                        savedSongs.add(SongList.getInstance().getSong(UUID.fromString((String)savedSongIDsJSON.get(j))));
                    }
                    JSONArray savedLessonIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_SAVED_LESSONS);
                    ArrayList<Lesson> savedLessons = new ArrayList<Lesson>();
                    for (int j = 0; j < savedLessonIDsJSON.size(); j++) {
                        savedLessons.add(LessonList.getInstance().getLesson(UUID.fromString((String)savedLessonIDsJSON.get(j))));
                    }
                    JSONArray assignmentsJSON = (JSONArray)accountJSON.get(ACCOUNT_ASSIGNMENTS);
                    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                    for (int j = 0; j < assignmentsJSON.size(); j++) {
                        JSONObject assignmentJSON = (JSONObject)assignmentsJSON.get(j);
                        if(((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Song") ) {
                            assignments.add(new SongAssignment(SongList.getInstance().getSong(UUID.fromString((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_SONG_ID)))));
                        } else if(((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Lesson")) {
                            assignments.add(new LessonAssignment(LessonList.getInstance().getLesson(UUID.fromString((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_LESSON_ID)))));
                        }
                    }
                    JSONArray achievementsJSON = (JSONArray)accountJSON.get(ACCOUNT_ACHIEVEMENTS);
                    ArrayList<Achievement> achievements = new ArrayList<Achievement>();
                    for (int j = 0; j < achievementsJSON.size(); j++) {
                        achievements.add(Achievement.valueOf((String)achievementsJSON.get(j)));
                    }
                    JSONArray songPlayedIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_SONGS_PLAYED);
                    ArrayList<Song> songsPlayed = new ArrayList<Song>();
                    for (int j = 0; j < songPlayedIDsJSON.size(); j++) {
                        songsPlayed.add(SongList.getInstance().getSong(UUID.fromString(((String)songPlayedIDsJSON.get(j)))));
                    }
                    JSONArray completedSongIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_COMPLETED_SONGS);
                    ArrayList<Song> completedSongs = new ArrayList<Song>();
                    for (int j = 0; j < completedSongIDsJSON.size(); j++) {
                        completedSongs.add(SongList.getInstance().getSong(UUID.fromString((String)completedSongIDsJSON.get(j))));
                    }
                    JSONArray completedLessonIDsJSON = (JSONArray)accountJSON.get(ACCOUNT_COMPLETED_LESSONS);
                    ArrayList<Lesson> completedLessons = new ArrayList<Lesson>();
                    for (int j = 0; j < completedLessonIDsJSON.size(); j++) {
                        completedLessons.add(LessonList.getInstance().getLesson(UUID.fromString((String)completedLessonIDsJSON.get(j))));
                    }
                    accounts.add(new Student(username, password, savedSongs, savedLessons, assignments, achievements, songsPlayed, completedSongs, completedLessons));
                } else if(role.equals("Teacher")) {
                    accounts.add(new Teacher(username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCoursesForAccounts(accounts);
    }

    public static ArrayList<Account> getCoursesForAccounts(ArrayList<Account> accounts) {
        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
                String title = (String) courseJSON.get(COURSE_NAME);
                UUID id = UUID.fromString((String)courseJSON.get(COURSE_ID));
                JSONArray studentsJSON = (JSONArray)courseJSON.get(COURSE_STUDENTS);
                ArrayList<Student> students = new ArrayList<Student>();
                JSONArray assignmentsJSON = (JSONArray)courseJSON.get(COURSE_ASSIGNMENTS);
                ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                for (int j = 0; j < assignmentsJSON.size(); j++) {
                    JSONObject assignmentJSON = (JSONObject)assignmentsJSON.get(j);
                    if(((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Song") ) {
                        assignments.add(new SongAssignment(SongList.getInstance().getSong(UUID.fromString((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_SONG_ID)))));
                    } else if(((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Lesson")) {
                        assignments.add(new LessonAssignment(LessonList.getInstance().getLesson(UUID.fromString((String)assignmentJSON.get(ACCOUNT_ASSIGNMENT_LESSON_ID)))));
                    }
                }
                Teacher teacher = null;
                for (Account a : accounts) {
                    if(a.getUsername().equals((String)courseJSON.get(COURSE_TEACHER))) {
                        teacher = (Teacher)a;
                    }
                }
                Course course = new Course(title, id, students, assignments, teacher);
                for (int j = 0; j < studentsJSON.size(); j++) {
                    for (Account a : accounts) {
                        if (a.getUsername().equals((String)studentsJSON.get(j))) {
                            course.addStudent((Student)a);
                            ((Student)a).addCourse(course);
                        }
                    }
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
                Instrument instrument = instrumentFromString((String)songJSON.get(SONG_INSTRUMENT));
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
                        double length = ((Long)noteJSON.get(NOTE_LENGTH)).intValue();
                        double position = ((Long)noteJSON.get(NOTE_POSITION)).intValue();
                        notes.add(new Note(noteName, octave, length, position));
                    }
                    measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, notes));
                }
                songs.add(new Song(id, title, artistName, difficulty, genre, instrument, measures));
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
        try {
            FileReader reader = new FileReader(LESSON_FILE_NAME);
            JSONArray lessonsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < lessonsJSON.size(); i++) {
                JSONObject lessonJSON = (JSONObject)lessonsJSON.get(i);
                UUID id = UUID.fromString((String)lessonJSON.get(LESSON_ID));
                String title = (String)lessonJSON.get(LESSON_TITLE);
                ArrayList<Question> questions = new ArrayList<Question>();
                JSONArray questionsJSON = (JSONArray)lessonJSON.get(LESSON_QUESTIONS);
                for (int j = 0; j < questionsJSON.size(); j++) {
                    questions.add(QuestionList.getInstance().getQuestion(UUID.fromString((String)questionsJSON.get(j))));
                }
                ArrayList<String> text = new ArrayList<String>();
                JSONArray textJSON = (JSONArray)lessonJSON.get(LESSON_TEXT);
                for (int j = 0; j < textJSON.size(); j++) {
                    text.add((String) textJSON.get(j));
                }
                lessons.add(new Lesson(id, title, questions, text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }
    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            FileReader reader = new FileReader(QUESTION_FILE_NAME);
            JSONArray questionsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < questionsJSON.size(); i++) {
                JSONObject questionJSON = (JSONObject) questionsJSON.get(i);
                UUID id = UUID.fromString((String)questionJSON.get(QUESTION_ID));
                String question = (String)questionJSON.get(QUESTION_QUESTION);
                String studentAnswer = (String)questionJSON.get(QUESTION_STUDENT_ANSWER);
                ArrayList<String> answerChoices = new ArrayList<String>();
                JSONArray answerChoicesJSON = (JSONArray)questionJSON.get(QUESTION_ANSWER_CHOICES);
                for (int j = 0; j < answerChoices.size(); j++) {
                    answerChoices.add((String)answerChoicesJSON.get(i));
                }
                int points = ((Long)questionJSON.get(QUESTION_POINTS)).intValue();
                String correctAnswer = (String)questionJSON.get(QUESTION_CORRECT_ANSWER);
                String feedback = (String)questionJSON.get(QUESTION_FEEDBACK);
                String hint = (String)questionJSON.get(QUESTION_HINT);
                questions.add(new Question(id, question, studentAnswer, answerChoices, points, correctAnswer, feedback, hint));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static void main(String[] args) {
        ArrayList<Song> songs = DataLoader.getSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
        ArrayList<Account> accounts = DataLoader.getAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
        ArrayList<Question> questions = DataLoader.getQuestions();
        for (Question q : questions) {
            System.out.println(q);
        }
        ArrayList<Lesson> lessons = DataLoader.getLessons();
        for (Lesson l : lessons) {
            System.out.println(l);
        }
    }

}