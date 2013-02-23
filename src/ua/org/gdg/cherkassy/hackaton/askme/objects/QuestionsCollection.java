package ua.org.gdg.cherkassy.hackaton.askme.objects;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsCollection extends ArrayList<Question> {

    public QuestionsCollection(){}

    public QuestionsCollection(JSONArray array)
    {
        for(int i = 0; i < array.length(); i++)
        {
            add(new Question(array.optJSONObject(i)));
        }
    }

}
