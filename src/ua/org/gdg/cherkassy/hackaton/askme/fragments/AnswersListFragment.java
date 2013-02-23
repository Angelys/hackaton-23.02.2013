package ua.org.gdg.cherkassy.hackaton.askme.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ua.org.gdg.cherkassy.hackaton.askme.R;
import ua.org.gdg.cherkassy.hackaton.askme.adapters.AnswersAdapter;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Answer;
import ua.org.gdg.cherkassy.hackaton.askme.objects.AnswersCollection;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnswersListFragment extends Fragment {

    public static AnswersListFragment Instance;

    protected Button button;
    protected TextView title;
    protected EditText textView;
    protected ListView list;
    public Question question;
    public AnswersCollection data;

    onListElementSelectedListener mCallBack;

    public interface onListElementSelectedListener
    {
        public void onItemSelected(Answer item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container, savedInstanceState );

        Instance = this;

        return inflater.inflate(R.layout.answers_list, container, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        title = (TextView) view.findViewById(R.id.title);
        textView = (EditText) view.findViewById(R.id.input);
        button = (Button) view.findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        list = (ListView) view.findViewById(R.id.list_view);
        list.setEnabled(false);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                mCallBack.onItemSelected(data.get(position));
            }
        });
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallBack = (onListElementSelectedListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onListElementSelectedListener interface");
        }
    }

    public void updateUI()
    {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                list.setAdapter(new AnswersAdapter(getActivity(), data));

            }
        });

    }

    public void onDestroy()
    {
        Instance = null;
        super.onDestroy();
    }

    public void addAnswer(Answer a)
    {
        data.add(a);
        updateUI();
    }

    public void setQuestion(Question q)
    {
        question = q;
        title.setText(q.getTitle());
    }

    public void loadAnswers()
    {

    }


}
