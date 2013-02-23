package ua.org.gdg.cherkassy.hackaton.askme;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import ua.org.gdg.cherkassy.hackaton.askme.fragments.AnswersListFragment;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Answer;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailsActivity extends FragmentActivity implements AnswersListFragment.onListElementSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
    }

    public void onItemSelected(Answer a)
    {

    }

}
