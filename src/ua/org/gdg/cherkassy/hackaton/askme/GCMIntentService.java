package ua.org.gdg.cherkassy.hackaton.askme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.google.android.gcm.GCMBaseIntentService;
import org.json.JSONException;
import org.json.JSONObject;
import ua.org.gdg.cherkassy.hackaton.askme.fragments.QuestionsListFragment;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;
import ua.org.gdg.cherkassy.hackaton.askme.objects.QuestionsCollection;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class GCMIntentService extends GCMBaseIntentService {

    public static String TAG = "gcm";
    public static String ID_TAG = "user_id";

    public GCMIntentService(){}

    @Override
    protected void onRegistered(Context context, String regId)
    {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(TAG, MODE_PRIVATE);

        preferences.edit().putString(ID_TAG, regId);

        ServerAPI.sendRegData(context, regId);

    }
    @Override
    protected void onUnregistered(Context context, String regId)
    {

    }
    @Override
    protected void onMessage(Context context, Intent intent)
    {
        if(QuestionsListFragment.Instance != null)
        {
            JSONObject data = new JSONObject();

            try
            {
                data = new JSONObject(intent.getStringExtra("data"));
            } catch (JSONException e){}

            QuestionsListFragment.Instance.addQuestion(new Question(data));
        }
    }
    @Override
    protected void onError(Context context, String errorId)
    {

    }
    @Override
    protected boolean onRecoverableError(Context context, String errorId)
    {
        return true;
    }
}
