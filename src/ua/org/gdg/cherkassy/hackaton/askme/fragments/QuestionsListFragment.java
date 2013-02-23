package ua.org.gdg.cherkassy.hackaton.askme.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ua.org.gdg.cherkassy.hackaton.askme.R;
import ua.org.gdg.cherkassy.hackaton.askme.adapters.QuestionsAdapter;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;
import ua.org.gdg.cherkassy.hackaton.askme.objects.QuestionsCollection;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsListFragment extends Fragment {

    public static QuestionsListFragment Instance;

    protected EditText textView;
    protected Button button;
    protected ListView list;
    public QuestionsCollection data;

    onListElementSelectedListener mCallBack;

    public interface onListElementSelectedListener
    {
        public void onItemSelected(Question item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container, savedInstanceState );

        Instance = this;

        return inflater.inflate(R.layout.quesions_list, container, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        textView = (EditText) view.findViewById(R.id.input);
        button = (Button) view.findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        list = (ListView) view.findViewById(R.id.list_view);

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

                list.setAdapter(new QuestionsAdapter(getActivity(), data));

            }
        });

    }

    public void onDestroy()
    {
        Instance = null;
        super.onDestroy();
    }

    public void addQuestion(Question q)
    {
        data.add(q);
        updateUI();
    }

}
