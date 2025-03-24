package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class contains all methods to load data from JSON files.
 * @author Ethan Little
 */
public class DataLoader extends DataConstants {
    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileReader reader = new FileReader(ACCOUNT_FILE_NAME);
            JSONArray accountsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < accountsJSON.size(); i++) {
                JSONObject accountJSON = (JSONObject) accountsJSON.get(i);
                String username = (String) accountJSON.get(ACCOUNT_USERNAME);
                String password = (String) accountJSON.get(ACCOUNT_PASSWORD);
                String role = (String) accountJSON.get(ACCOUNT_ROLE);
                if (role.equals("Student")) {
                    JSONArray savedSongIDsJSON = (JSONArray) accountJSON.get(ACCOUNT_SAVED_SONGS);
                    ArrayList<Song> savedSongs = new ArrayList<Song>();
                    if (savedSongIDsJSON != null) {
                        for (int j = 0; j < savedSongIDsJSON.size(); j++) {
                            savedSongs.add(songFromUUIDString((String) savedSongIDsJSON.get(j)));
                        }
                    }
                    JSONArray savedLessonIDsJSON = (JSONArray) accountJSON.get(ACCOUNT_SAVED_LESSONS);
                    ArrayList<Lesson> savedLessons = new ArrayList<Lesson>();
                    if (savedSongIDsJSON != null) {
                        for (int j = 0; j < savedLessonIDsJSON.size(); j++) {
                            savedLessons.add(lessonFromUUIDString((String) savedLessonIDsJSON.get(j)));
                        }
                    }
                    JSONArray assignmentsJSON = (JSONArray) accountJSON.get(ACCOUNT_ASSIGNMENTS);
                    ArrayList<Assignment> assignments = assignmentsFromJSON(assignmentsJSON);
                    JSONArray achievementsJSON = (JSONArray) accountJSON.get(ACCOUNT_ACHIEVEMENTS);
                    ArrayList<Achievement> achievements = new ArrayList<Achievement>();
                    if (achievementsJSON != null) {
                        for (int j = 0; j < achievementsJSON.size(); j++) {
                            achievements.add(Achievement.valueOf((String) achievementsJSON.get(j)));
                        }
                    }
                    JSONArray songPlayedIDsJSON = (JSONArray) accountJSON.get(ACCOUNT_SONGS_PLAYED);
                    ArrayList<Song> songsPlayed = new ArrayList<Song>();
                    if (songPlayedIDsJSON != null) {
                        for (int j = 0; j < songPlayedIDsJSON.size(); j++) {
                            songsPlayed.add(songFromUUIDString((String) songPlayedIDsJSON.get(j)));
                        }
                    }
                    JSONArray completedSongIDsJSON = (JSONArray) accountJSON.get(ACCOUNT_COMPLETED_SONGS);
                    ArrayList<Song> completedSongs = new ArrayList<Song>();
                    if (completedSongIDsJSON != null) {
                        for (int j = 0; j < completedSongIDsJSON.size(); j++) {
                            completedSongs.add(songFromUUIDString((String) completedSongIDsJSON.get(j)));
                        }
                    }
                    JSONArray completedLessonIDsJSON = (JSONArray) accountJSON.get(ACCOUNT_COMPLETED_LESSONS);
                    ArrayList<Lesson> completedLessons = new ArrayList<Lesson>();
                    if (completedLessonIDsJSON != null) {
                        for (int j = 0; j < completedLessonIDsJSON.size(); j++) {
                            completedLessons.add(lessonFromUUIDString((String) completedLessonIDsJSON.get(j)));
                        }
                    }
                    accounts.add(new Student(username, password, savedSongs, savedLessons, assignments, achievements,
                            songsPlayed, completedSongs, completedLessons));
                } else if (role.equals("Teacher")) {
                    accounts.add(new Teacher(username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCoursesForAccounts(accounts);
    }

    private static ArrayList<Account> getCoursesForAccounts(ArrayList<Account> accounts) {
        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONArray coursesJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
                String title = (String) courseJSON.get(COURSE_NAME);
                UUID id = UUID.fromString((String) courseJSON.get(COURSE_ID));
                JSONArray studentIDsJSON = (JSONArray) courseJSON.get(COURSE_STUDENTS);
                ArrayList<Student> students = new ArrayList<Student>();
                for (int j = 0; j < studentIDsJSON.size(); j++) {
                    for (Account a : accounts) {
                        if (a.getUsername().equals((String) studentIDsJSON.get(j))) {
                            students.add((Student) a);
                        }
                    }
                }
                JSONArray assignmentsJSON = (JSONArray) courseJSON.get(COURSE_ASSIGNMENTS);
                ArrayList<Assignment> assignments = assignmentsFromJSON(assignmentsJSON);
                Teacher teacher = null;
                for (Account a : accounts) {
                    if (a.getUsername().equals((String) courseJSON.get(COURSE_TEACHER))) {
                        teacher = (Teacher) a;
                    }
                }
                JSONArray invitedStudentIDsJSON = (JSONArray) courseJSON.get(COURSE_INVITED_STUDENTS);
                ArrayList<Student> invitedStudents = new ArrayList<Student>();
                for (int j = 0; j < invitedStudentIDsJSON.size(); j++) {
                    for (Account a : accounts) {
                        if (a.getUsername().equals((String) invitedStudentIDsJSON.get(j))) {
                            invitedStudents.add((Student) a);
                        }
                    }
                }
                Course course = new Course(title, id, students, assignments, teacher, invitedStudents);
                teacher.addCourse(course);
                for (Student s : students) {
                    s.addCourse(course);
                }
                for (Student s : invitedStudents) {
                    s.addCourseInvitedTo(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private static Song songFromUUIDString(String id) {
        return SongList.getInstance().getSong(UUID.fromString(id));
    }
    private static Lesson lessonFromUUIDString(String id) {
        return LessonList.getInstance().getLesson(UUID.fromString(id));
    }
    private static Question questionFromUUIDString(String id) {
        return QuestionList.getInstance().getQuestion(UUID.fromString(id));
    }

    private static ArrayList<Assignment> assignmentsFromJSON(JSONArray assignmentsJSON) {
        if(assignmentsJSON == null) {
            return new ArrayList<Assignment>();
        }
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (int j = 0; j < assignmentsJSON.size(); j++) {
            JSONObject assignmentJSON = (JSONObject) assignmentsJSON.get(j);
            String assignmentTitle = (String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_TITLE);
            String description = (String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_DESCRIPTION);
            boolean complete = (boolean) assignmentJSON.get(ACCOUNT_ASSIGNMENT_COMPLETE);
            if (((String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Song")) {
                Song song = songFromUUIDString((String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_SONG_ID));
                String difficultyLevel = (String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_DIFFICULTY_LEVEL);
                JSONArray recommendedSongsJSON = (JSONArray) assignmentJSON.get(ACCOUNT_ASSIGNMENT_RECOMMENDED_LESSONS);
                ArrayList<Song> recommendedSongs = new ArrayList<Song>();
                if(recommendedSongsJSON != null) {
                    for (int k = 0; k < recommendedSongsJSON.size(); k++) {
                        recommendedSongs.add(songFromUUIDString((String) recommendedSongsJSON.get(k)));
                    }
                }
                assignments.add(new SongAssignment(assignmentTitle, description, complete, song, difficultyLevel, recommendedSongs));
            } else if (((String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_TYPE)).equals("Lesson")) {
                Lesson lesson = lessonFromUUIDString((String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_LESSON_ID));
                String difficultyLevel = (String) assignmentJSON.get(ACCOUNT_ASSIGNMENT_DIFFICULTY_LEVEL);
                JSONArray recommendedLessonsJSON = (JSONArray) assignmentJSON.get(ACCOUNT_ASSIGNMENT_RECOMMENDED_LESSONS);
                ArrayList<Lesson> recommendedLessons = new ArrayList<Lesson>();
                if (recommendedLessonsJSON != null) {
                    for (int k = 0; k < recommendedLessonsJSON.size(); k++) {
                        recommendedLessons.add(lessonFromUUIDString((String) recommendedLessonsJSON.get(k)));
                    }
                }
                assignments.add(new LessonAssignment(assignmentTitle, description, complete, lesson,
                        difficultyLevel, recommendedLessons));
            }
        }
        return assignments;
    }

    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONArray songsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject) songsJSON.get(i);
                UUID id = UUID.fromString((String) songJSON.get(SONG_ID));
                String title = (String) songJSON.get(SONG_TITLE);
                String artistName = (String) songJSON.get(SONG_ARTIST_NAME);
                String difficulty = (String) songJSON.get(SONG_DIFFICULTY);
                String genre = (String) songJSON.get(SONG_GENRE);
                Instrument instrument = instrumentFromString((String) songJSON.get(SONG_INSTRUMENT));
                int tempo = ((Long) songJSON.get(SONG_TEMPO)).intValue();
                ArrayList<Measure> measures = new ArrayList<Measure>();
                JSONArray measuresJSON = (JSONArray) songJSON.get(SONG_MEASURES);
                for (int j = 0; j < measuresJSON.size(); j++) {
                    JSONObject measureJSON = (JSONObject) measuresJSON.get(j);
                    int timeSignatureTop = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_TOP)).intValue();
                    int timeSignatureBottom = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_BOTTOM)).intValue();
                    String keySignature = (String) measureJSON.get(MEASURE_KEY_SIGNATURE);
                    ArrayList<Note> notes = new ArrayList<Note>();
                    JSONArray notesJSON = (JSONArray) measureJSON.get(MEASURE_NOTES);
                    for (int k = 0; k < notesJSON.size(); k++) {
                        JSONObject noteJSON = (JSONObject) notesJSON.get(k);
                        String noteName = (String) noteJSON.get(NOTE_NAME);
                        int octave = ((Long) noteJSON.get(NOTE_OCTAVE)).intValue();
                        double length = ((Long) noteJSON.get(NOTE_LENGTH)).intValue();
                        double position = ((Long) noteJSON.get(NOTE_POSITION)).intValue();
                        notes.add(new Note(noteName, octave, length, position));
                    }
                    measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, notes));
                }
                songs.add(new Song(id, title, artistName, difficulty, genre, instrument, tempo, measures));
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
                JSONObject lessonJSON = (JSONObject) lessonsJSON.get(i);
                UUID id = UUID.fromString((String) lessonJSON.get(LESSON_ID));
                String title = (String) lessonJSON.get(LESSON_TITLE);
                ArrayList<Question> questions = new ArrayList<Question>();
                JSONArray questionsJSON = (JSONArray) lessonJSON.get(LESSON_QUESTIONS);
                for (int j = 0; j < questionsJSON.size(); j++) {
                    questions.add(questionFromUUIDString((String) questionsJSON.get(j)));
                }
                ArrayList<String> text = new ArrayList<String>();
                JSONArray textJSON = (JSONArray) lessonJSON.get(LESSON_TEXT);
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
                UUID id = UUID.fromString((String) questionJSON.get(QUESTION_ID));
                String question = (String) questionJSON.get(QUESTION_QUESTION);
                String studentAnswer = (String) questionJSON.get(QUESTION_STUDENT_ANSWER);
                ArrayList<String> answerChoices = new ArrayList<String>();
                JSONArray answerChoicesJSON = (JSONArray) questionJSON.get(QUESTION_ANSWER_CHOICES);
                for (int j = 0; j < answerChoicesJSON.size(); j++) {
                    answerChoices.add((String) answerChoicesJSON.get(j));
                }
                int points = ((Long) questionJSON.get(QUESTION_POINTS)).intValue();
                String correctAnswer = (String) questionJSON.get(QUESTION_CORRECT_ANSWER);
                String feedback = (String) questionJSON.get(QUESTION_FEEDBACK);
                String hint = (String) questionJSON.get(QUESTION_HINT);
                questions.add(new Question(id, question, answerChoices, correctAnswer, studentAnswer, points, feedback, hint));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

}