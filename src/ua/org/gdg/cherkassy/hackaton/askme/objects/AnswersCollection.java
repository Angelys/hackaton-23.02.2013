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
public class AnswersCollection extends ArrayList<Answer> {

    public AnswersCollection(){}

    public AnswersCollection(JSONArray json)
    {
        for(int i = 0; !(i >= json.length()); i++)
        {
            this.add(new Answer(json.optJSONObject(i)));
        }
    }

    public static AnswersCollection generate()
    {
        AnswersCollection answersCollection = new AnswersCollection();

        for(int i = 0; i < 25; i++)
        {
            Answer a = new Answer();
            a.setBody("This is answer  "+i);

            answersCollection.add(a);

        }

        return answersCollection;
    }
}
