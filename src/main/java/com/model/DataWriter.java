package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    public static boolean savedAccounts(AccountList accountList) {
        JSONArray accountArray = new JSONArray();

        for (Account account : accountList.getAccounts()) {
            JSONObject accountObject = new JSONObject();
            accountObject.put(DataConstants.ACCOUNT_USERNAME, account.getUsername());
            accountObject.put(DataConstants.ACCOUNT_PASSWORD, account.getPassword());
            accountObject.put(DataConstants.ACCOUNT_ROLE, account.getRole());
            accountArray.add(accountObject);
        }
        return saveToFile(DataConstants.ACCOUNT_FILE_NAME, accountArray);
    }

    public static boolean savedSongs(SongList songList) {
        JSONArray songArray = new JSONArray();

        for (Song song : songList.getSongs()) {
            ;
            JSONObject songObject = new JSONObject();
            songObject.put(DataConstants.SONG_ID, song.getID().toString());
            songObject.put(DataConstants.SONG_TITLE, song.getTitle());
            songObject.put(DataConstants.SONG_ARTIST_NAME, song.getArtistName());
            songObject.put(DataConstants.SONG_DIFFICULTY, song.getDifficulty());
            songObject.put(DataConstants.SONG_GENRE, song.getGenre());
            songArray.add(songObject);
        }
        return saveToFile(DataConstants.SONG_FILE_NAME, songArray);// Add songs json later..(Done)
    }

    public static boolean savedLessons(LessonList lessonList) {
        JSONArray lessonArray = new JSONArray();
        for (Lesson lessons : lessonList.getLessons()) {
            JSONObject lessonObject = new JSONObject();

            lessonObject.put(DataConstants.LESSON_TITLE, lessons.getTitle());
            lessonObject.put(DataConstants.LESSON_ID, lessons.getID().toString());

            // A way for lesson questions to convert to a Json
            JSONArray questionArray = new JSONArray();
            for (Question question : lessons.getQuestions()) {
                questionArray.add(convertQuestionJSON(question));
            }

            lessonObject.put(DataConstants.LESSON_QUESTIONS, questionArray);
            JSONArray textArray = new JSONArray();
            textArray.addAll(lessons.getText());
            lessonObject.put(DataConstants.LESSON_TEXT, textArray);
            lessonArray.add(lessonArray);
        }
        return saveToFile(DataConstants.LESSON_FILE_NAME, lessonArray);
    }

    public static boolean savedQuestions(QuestionList questionList) {
        JSONArray questionArray = new JSONArray();
        for (Question question : questionList.getQuestions()) {
            questionArray.add(convertQuestionJSON(question));
        }
        return saveToFile(DataConstants.QUESTION_FILE_NAME, questionArray);
    }
    

    private static JSONObject convertQuestionJSON(Question question) {
        JSONObject questionObject = new JSONObject();
        questionObject.put(DataConstants.QUESTION_QUESTION, question.getQuestionText());
        JSONArray choicesArray = new JSONArray();
        choicesArray.addAll(question.getAnswerChoices());
        questionObject.put(DataConstants.QUESTION_ANSWER_CHOICES, choicesArray);

        questionObject.put(DataConstants.QUESTION_CORRECT_ANSWER, question.getCorrectAnswer());
        questionObject.put(DataConstants.QUESTION_POINTS, question.getPoints());
        questionObject.put(DataConstants.QUESTION_FEEDBACK, question.getFeedback());
        questionObject.put(DataConstants.QUESTION_HINT, question.getHint());
        return questionObject;
    }
    private static JSONObject convertMeasureJSON (Measure measure){
    JSONObject measureObject = new JSONObject();
    measureObject.put(DataConstants.MEASURE_TIME_SIGNATURE_TOP, measure.getTimeSignatureTop());
    measureObject.put(DataConstants.MEASURE_TIME_SIGNATURE_BOTTOM, measure.getTimeSignatureBottom());
    measureObject.put(DataConstants.MEASURE_KEY_SIGNATURE, measure.getKeySignature());

    JSONArray noteArray = new JSONArray();
    for (Note note : measure.getNotes()){
        noteArray.add(convertNoteJSON(note));
    }
    measureObject.put(DataConstants.MEASURE_NOTES, noteArray);
    return measureObject;
    }
    private static JSONObject convertNoteJSON(Note note){
        JSONObject noteObject = new JSONObject();
        noteObject.put(DataConstants.NOTE_NAME, note.getName());
        noteObject.put(DataConstants.NOTE_OCTAVE, note.getOctave());
        noteObject.put(DataConstants.NOTE_LENGTH, note.getLength());
        noteObject.put(DataConstants.NOTE_POSITION, note.getPosition());
        return noteObject;
    }
	public static boolean savedCourses(List<Course> courses){
        JSONArray courseArray = new JSONArray();
        for (Course course : courses){
            JSONObject courseObject = new JSONObject();
            courseObject.put(DataConstants.COURSE_NAME, course.getCourseName());
            courseObject.put(DataConstants.COURSE_ID, course.getCourseID().toString());
            JSONArray studentArray = new JSONArray();
            for (Student student : course.getStudents()){
                studentArray.add(student.getUsername());
            }
            courseObject.put(DataConstants.COURSE_STUDENTS, studentArray);

            JSONArray assignmentArray = new JSONArray();
            for (Assignment assignment : course.getAssignments()){
                JSONObject assignmentObject = new JSONObject();
                assignmentObject.put(DataConstants.ASSIGNMENT_TITLE, assignment.getTitle());
                assignmentObject.put(DataConstants.ASSIGNMENT_DESCRIPTION, assignment.getDescription());
                assignmentObject.put(DataConstants.ASSIGNMENT_COMPLETE,assignment.isComplete());
                 
                   if(assignment instanceof LessonAssignment){
                    LessonAssignment lessonAssignment = (LessonAssignment) assignment;
                    assignmentObject.put(DataConstants.COURSE_LESSON_ID, lessonAssignment.getLessonID().toString());
                   }
                   else if (assignment instanceof SongAssignment){
                    SongAssignment songAssignment = (SongAssignment) assignment;
                    assignmentObject.put(DataConstants.COURSE_SONG_ID, songAssignment.getSongID().toString());
                   }
                  
                assignmentArray.add(assignmentObject);
            }
            courseObject.put(DataConstants.COURSE_ASSIGNMENTS, assignmentArray);
             
             //Helps convert the Teacher to JSON
             if(course.getTeacher() != null){
                courseObject.put(DataConstants.COURSE_TEACHER, course.getTeacher().getUsername());
             }
             courseArray.add(courseObject);
        }
        return saveToFile(DataConstants.COURSE_FILE_NAME, courseArray);
    }

    // Will help wite JSONarray to a file
    private static boolean saveToFile(String fileName, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toString());// Keeps things neat
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}