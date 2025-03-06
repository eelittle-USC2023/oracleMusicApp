package com.model; 
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class DataWriter{

    public static boolean savedAccounts(AccountList accountList){
        JSONArray accountArray = new JSONArray();

        for (Account account : accountList.getAccounts()){
            JSONObject accountObject = new JSONObject();
            accountObject.put("Username", account.getUsername());
            accountObject.put("Password", account.getPassword());
            accountObject.put("Role", account.getRole().toString());
            accountArray.add(accountObject);
        }
        return saveToFile ("Account.json", accountArray);
    }
public static boolean savedSongs (SongList songList){
    JSONArray songArray = new JSONArray();

    for (Song song : songList.getSongs()){;
        JSONObject songObject = new JSONObject();
    songObject.put("title" , song.getTitle());
    songObject.put("artistName", song.getArtistName());
    songObject.put("genre", song.getGenre());
    songArray.add(songObject);
    }
    return saveToFile("songs.json", songArray);//Add songs json later..
} 
public static boolean savedLessons (LessonList lessonList){
    JSONArray lessonArray = new JSONArray();
    for (Lesson lessons : lessonList.getLesson()){
        JsonObject.put("title", lesson.getTitle());

        //A way for lesson questions to convert to a Json
        JSONArray questionArray = new JsonArray();
        for (Question question : lesson.getQuestions()){
            questionArray.put(convertQuestionJSON(question));
        }
lessonsObject.put("questions", questionArray);
lessonsObject.put("text", new JSONArray(lesson.getText));
lessonsArray.put(lessonObject);
    }
    return saveToFIle("lessons.json", lessonArray);
}
public static boolean savedQuestions (QuestionList questionList){
    JSONArray questionArray = new JSONArray();
    for (Question question : questionList.getQuestion()){
        questionArray.put(convertQuestionJSON(question));
    }
    return saveToFile("questions.json", questionArray);
}
private static JSONObject convertQuestionJSON(Question question ){
JSONObject questionObject = new JSONObject();
questionObject.put("question", question.getQuestionText());
questionObject.put("answerChoices", new JSONArray(question.getAnswerChoices()));
questionObject.put("correctAnswer", question.getCorrectAnswer());
questionObject.put("points", question.getPoints());
questionObject.put("feedback", question.getFeedback());
questionObject.put("hint", question.getHint());
    return questionObject;
}
//Will help wite JSONarray to a file
private static boolean saveToFile(String fileName, JSONArray jsonArray){
    try (FileWriter file = new FileWriter(fileName)){
        file.write(jsonArray.toString(4));//Keeps things neat 
        return true;
    }
    catch(IOException e ){
        e.printStackTrace();
        return false;
    }
}
}