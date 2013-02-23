package ua.org.gdg.cherkassy.hackaton.askme;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gcm.GCMRegistrar;
import ua.org.gdg.cherkassy.hackaton.askme.fragments.QuestionsListFragment;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;

public class MainActivity extends FragmentActivity implements QuestionsListFragment.onListElementSelectedListener {

    private String TAG = "MainActivity";
    private String SENDER_ID = "847062316174";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            GCMRegistrar.register(this, SENDER_ID);
        } else {
            Log.v(TAG, "Already registered");
        }

        setContentView(R.layout.main);
    }

    public void onItemSelected(Question q)
    {

    }
}
