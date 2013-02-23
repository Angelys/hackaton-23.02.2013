package ua.org.gdg.cherkassy.hackaton.askme.objects;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Answer implements Serializable {

    public int id;
    public int question_id;
    public String body;

    public Answer(){}

    public Answer(JSONObject object)
    {
        id = object.optInt("id");
        question_id = object.optInt("question_id");
        body = object.optString("text");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
