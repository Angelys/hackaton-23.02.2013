package ua.org.gdg.cherkassy.hackaton.askme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import ua.org.gdg.cherkassy.hackaton.askme.fragments.AnswersListFragment;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Answer;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;

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

        Intent i = getIntent();
        if(AnswersListFragment.Instance != null)
        {
            AnswersListFragment.Instance.setQuestion((Question)i.getSerializableExtra("question"));
            AnswersListFragment.Instance.loadAnswers();
            AnswersListFragment.Instance.updateUI();
        }

        setContentView(R.layout.details);
    }

    public void onItemSelected(Answer a)
    {
    }

}
