package com.sukrit.mckkrs.Models;

import java.util.ArrayList;

public class QuestionModel {
    String id_activity;
    String id_question;
    String question_txt;
    ArrayList<AnswerModel> answerModels;

    public String getId_activity() {
        return id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getQuestion_txt() {
        return question_txt;
    }

    public void setQuestion_txt(String question_txt) {
        this.question_txt = question_txt;
    }

    public ArrayList<AnswerModel> getAnswerModels() {
        return answerModels;
    }

    public void setAnswerModels(ArrayList<AnswerModel> answerModels) {
        this.answerModels = answerModels;
    }
}
