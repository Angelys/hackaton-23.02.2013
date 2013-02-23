package ua.org.gdg.cherkassy.hackaton.askme.objects;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Question {

    public int id;
    public String title;

    public Question(){}

    public Question(JSONObject object)
    {
        //TODO question from json
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
