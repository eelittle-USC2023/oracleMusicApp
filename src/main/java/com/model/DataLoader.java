package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class contains all methods to load data from JSON files.
 * The order in which things should always be loaded is first Songs, then Questions, then Lessons, and finally Accounts.
 * This is because Lessons contain Questions, so the Questions must be loaded into the QuestionList before
 * @author Ethan Little
 */
public class DataLoader extends DataConstants {
    /**
     * Loads in the accounts from the JSON file.
     * @return The loaded accounts.
     * @author Ethan Little
     */
    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileReader reader = getReaderFromFile(ACCOUNT_FILE_NAME, ACCOUNT_TEST_FILE_NAME);
            JSONArray accountsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < accountsJSON.size(); i++) {
                JSONObject accountJSON = (JSONObject) accountsJSON.get(i);
                String username = (String) accountJSON.get(ACCOUNT_USERNAME);
                String password = (String) accountJSON.get(ACCOUNT_PASSWORD);
                String role = (String) accountJSON.get(ACCOUNT_ROLE);
                if (role.equals("Student")) {
                    ArrayList<Song> savedSongs = getSongsFromIDsJSON((JSONArray) accountJSON.get(ACCOUNT_SAVED_SONGS));
                    ArrayList<Lesson> savedLessons = getLessonsFromIDsJSON((JSONArray) accountJSON.get(ACCOUNT_SAVED_LESSONS));
                    ArrayList<Assignment> assignments = assignmentsFromJSON((JSONArray) accountJSON.get(ACCOUNT_ASSIGNMENTS));
                    ArrayList<Achievement> achievements = achievementsFromJSON((JSONArray) accountJSON.get(ACCOUNT_ACHIEVEMENTS));
                    ArrayList<Song> songsPlayed = getSongsFromIDsJSON((JSONArray) accountJSON.get(ACCOUNT_SONGS_PLAYED));
                    ArrayList<Lesson> completedLessons = getLessonsFromIDsJSON((JSONArray) accountJSON.get(ACCOUNT_COMPLETED_LESSONS));
                    accounts.add(new Student(username, password, savedSongs, savedLessons, assignments, achievements, songsPlayed, completedLessons));
                } else if (role.equals("Teacher")) {
                    accounts.add(new Teacher(username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCoursesForAccounts(accounts);
    }
    /**
     * Given a JSON array of song ids, gets the songs associated with those ids.
     * @param songIDsJSON the song IDs
     * @return ArrayList of the songs
     * @author Ethan Little
     */
    private static ArrayList<Song> getSongsFromIDsJSON(JSONArray songIDsJSON) {
        ArrayList<Song> songs = new ArrayList<Song>();
        if (songIDsJSON != null) {
            for (int j = 0; j < songIDsJSON.size(); j++) {
                songs.add(songFromUUIDString((String) songIDsJSON.get(j)));
            }
        }
        return songs;
    }
    /**
     * Not relevant this sprint
     * @param lessonIDsJSON
     * @return
     */
    private static ArrayList<Lesson> getLessonsFromIDsJSON(JSONArray lessonIDsJSON) {
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        if (lessonIDsJSON != null) {
            for (int j = 0; j < lessonIDsJSON.size(); j++) {
                lessons.add(lessonFromUUIDString((String) lessonIDsJSON.get(j)));
            }
        }
        return lessons;
    }
    /**
     * not relevant this sprint
     * @param achievementsJSON
     * @return
     */
    private static ArrayList<Achievement> achievementsFromJSON(JSONArray achievementsJSON) {
        ArrayList<Achievement> achievements = new ArrayList<Achievement>();
        if (achievementsJSON != null) {
            for (int j = 0; j < achievementsJSON.size(); j++) {
                achievements.add(Achievement.valueOf((String) achievementsJSON.get(j)));
            }
        }
        return achievements;
    }
    /**
     * not relevant this sprint
     * @param accounts
     * @return
     */
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
                ArrayList<Assignment> assignments = assignmentsFromJSON((JSONArray) courseJSON.get(COURSE_ASSIGNMENTS));
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
    /**
     * From a string UUID, gets the song from the song List
     * @param id the String ID.
     * @return the song that the getSong method returns
     * @author Ethan Little
     */
    private static Song songFromUUIDString(String id) {
        return SongList.getInstance().getSong(UUID.fromString(id));
    }
    /**
     * Not relevant this sprint
     * @param id
     * @return
     */
    private static Lesson lessonFromUUIDString(String id) {
        return LessonList.getInstance().getLesson(UUID.fromString(id));
    }
    /** 
     * Not relevant for this Sprint 
     * */
    private static ArrayList<Assignment> assignmentsFromJSON(JSONArray assignmentsJSON) {
        if (assignmentsJSON == null) {
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
                if (recommendedSongsJSON != null) {
                    for (int k = 0; k < recommendedSongsJSON.size(); k++) {
                        recommendedSongs.add(songFromUUIDString((String) recommendedSongsJSON.get(k)));
                    }
                }
                assignments.add(new SongAssignment(assignmentTitle, description, complete, song, difficultyLevel,
                        recommendedSongs));
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
            FileReader reader = getReaderFromFile(SONG_FILE_NAME, SONG_TEST_FILE_NAME);
            JSONArray songsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject) songsJSON.get(i);
                UUID id = UUID.fromString((String) songJSON.get(SONG_ID));
                String title = (String) songJSON.get(SONG_TITLE);
                String artistName = (String) songJSON.get(SONG_ARTIST_NAME);
                String difficulty = (String) songJSON.get(SONG_DIFFICULTY);
                String genre = (String) songJSON.get(SONG_GENRE);
                Instrument instrument = instrumentFromString((String) songJSON.get(SONG_INSTRUMENT));
                //int tempo = ((Long) songJSON.get(SONG_TEMPO)).intValue();
                ArrayList<Measure> measures = measuresFromJSON((JSONArray) songJSON.get(SONG_MEASURES));
                songs.add(new Song(id, title, artistName, difficulty, genre, instrument, 120, measures));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
    /**
     * Given a JSONArray of measure objects, creates an arrayList of Measures from the JSON, as long as the JSONArray is not null.
     * @param measuresJSON The JSONArray of measures.
     * @return The newly created ArrayList of measures.
     * @author Ethan Little
     */
    private static ArrayList<Measure> measuresFromJSON(JSONArray measuresJSON) {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        if (measuresJSON != null) {
            for (int j = 0; j < measuresJSON.size(); j++) {
                JSONObject measureJSON = (JSONObject) measuresJSON.get(j);
                int timeSignatureTop = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_TOP)).intValue();
                int timeSignatureBottom = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_BOTTOM)).intValue();
                String keySignature = (String) measureJSON.get(MEASURE_KEY_SIGNATURE);
                ArrayList<Note> notes = notesFromJSON((JSONArray) measureJSON.get(MEASURE_NOTES));
                measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, notes));
            }
        }
        return measures;
    }
    /**
     * Given an JSONArray of notes, returns an arrayList of notes, as long as the JSON isn't null.
     * @param notesJSON the JSONArray of notes
     * @return ArrayList of notes
     * @author Ethan Little
     */
    private static ArrayList<Note> notesFromJSON(JSONArray notesJSON) {
        ArrayList<Note> notes = new ArrayList<Note>();
        if (notesJSON != null) {
            for (int k = 0; k < notesJSON.size(); k++) {
                JSONObject noteJSON = (JSONObject) notesJSON.get(k);
                String noteName = (String) noteJSON.get(NOTE_NAME);
                int octave = ((Long) noteJSON.get(NOTE_OCTAVE)).intValue();
                double length = (double) noteJSON.get(NOTE_LENGTH);
                double position = (double) noteJSON.get(NOTE_POSITION);
                notes.add(new Note(noteName, octave, length, position));
            }
        }
        return notes;
    }
    /**
     * Given a string from JSON, will find and return the instrument class associated with it.
     * @param s String from JSON giving instrument class.
     * @return A new instance of the appropriate instrument
     * @author Ethan Little
     */
    private static Instrument instrumentFromString(String s) {
        if (s.equals("Guitar")) {
            return new Guitar();
        } else {
            return null;
        }
    }
    /**
     * Not relevant to this sprint
     * @return
     */
    public static ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        try {
            FileReader reader = new FileReader(LESSON_FILE_NAME);
            JSONArray lessonsJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < lessonsJSON.size(); i++) {
                JSONObject lessonJSON = (JSONObject) lessonsJSON.get(i);
                UUID id = UUID.fromString((String) lessonJSON.get(LESSON_ID));
                String title = (String) lessonJSON.get(LESSON_TITLE);
                ArrayList<Question> questions = getQuestionsFromIDsJSON((JSONArray) lessonJSON.get(LESSON_QUESTIONS));
                ArrayList<String> text = stringsFromJSON((JSONArray) lessonJSON.get(LESSON_TEXT));
                lessons.add(new Lesson(id, title, questions, text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }
    /**
     * Not relevant to this sprint
     * @param stringsJSON
     * @return
     */
    private static ArrayList<String> stringsFromJSON(JSONArray stringsJSON) {
        ArrayList<String> strings = new ArrayList<String>();
        if (stringsJSON != null) {
            for (int j = 0; j < stringsJSON.size(); j++) {
                strings.add((String) stringsJSON.get(j));
            }
        }
        return strings;
    }
    /**
     * Not relevant to this sprint
     * @param questionIDsJSON
     * @return
     */
    private static ArrayList<Question> getQuestionsFromIDsJSON(JSONArray questionIDsJSON) {
        ArrayList<Question> questions = new ArrayList<Question>();
        if (questionIDsJSON != null) {
            for (int j = 0; j < questionIDsJSON.size(); j++) {
                questions.add(questionFromUUIDString((String) questionIDsJSON.get(j)));
            }
        }
        return questions;
    }
    /**
     * Not relevant to this sprint
     * @param id
     * @return
     */
    private static Question questionFromUUIDString(String id) {
        return QuestionList.getInstance().getQuestion(UUID.fromString(id));
    }
    /**
     * Not relevant to this sprint
     * @return
     */
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
                questions.add(new Question(id, question, answerChoices, correctAnswer, studentAnswer, points, feedback,
                        hint));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    private static FileReader getReaderFromFile(String fileName, String jsonFileName){
		try {
			if(isJUnitTest()){
				return new FileReader(jsonFileName);
			} else {
				return new FileReader(fileName);
			}
		} catch(Exception e){
			System.out.println("Can't load");
			return null;
		}
			
	}
}