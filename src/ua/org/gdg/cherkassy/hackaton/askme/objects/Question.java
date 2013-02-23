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
public class Question implements Serializable {

    public int id;
    public String title;

    public Question(){}

    public Question(JSONObject object)
    {
        id = object.optInt("id");
        title = object.optString("title");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
