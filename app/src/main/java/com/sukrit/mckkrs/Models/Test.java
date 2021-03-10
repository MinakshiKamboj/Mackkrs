package com.sukrit.mckkrs.Models;

public class Test {
    String status;
    String message;
    String userdata;
    String id_client_activity;
    String activity_name;
    String question;
    String id_activity;
    String id_question;
    String question_txt;
    String answers;
    String answer_txt;
    String id_answer;
    String answer_correct;

    public Test(String status, String message, String userdata, String id_client_activity,
                String activity_name, String question, String id_activity,
                String id_question, String question_txt, String answers,
                String answer_txt, String id_answer, String answer_correct) {
        this.status = status;
        this.message = message;
        this.userdata = userdata;
        this.id_client_activity = id_client_activity;
        this.activity_name = activity_name;
        this.question = question;
        this.id_activity = id_activity;
        this.id_question = id_question;
        this.question_txt = question_txt;
        this.answers = answers;
        this.answer_txt = answer_txt;
        this.id_answer = id_answer;
        this.answer_correct = answer_correct;
    }

    public Test() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserdata() {
        return userdata;
    }

    public void setUserdata(String userdata) {
        this.userdata = userdata;
    }

    public String getId_client_activity() {
        return id_client_activity;
    }

    public void setId_client_activity(String id_client_activity) {
        this.id_client_activity = id_client_activity;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

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

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAnswer_txt() {
        return answer_txt;
    }

    public void setAnswer_txt(String answer_txt) {
        this.answer_txt = answer_txt;
    }

    public String getId_answer() {
        return id_answer;
    }

    public void setId_answer(String id_answer) {
        this.id_answer = id_answer;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }
}
