package ua.org.gdg.cherkassy.hackaton.askme.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ua.org.gdg.cherkassy.hackaton.askme.R;
import ua.org.gdg.cherkassy.hackaton.askme.ServerAPI;
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

        if(savedInstanceState != null)
        {
            data = (AnswersCollection)savedInstanceState.getSerializable("collection");
            question = (Question)savedInstanceState.getSerializable("question");
        } else
        {
            question = (Question)getActivity().getIntent().getSerializableExtra("question");
        }

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
                final String message = textView.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Answer a = new Answer();
                        a.setQuestion_id(question.getId());
                        a.setBody(message);
                        if(ServerAPI.postAnswer(a))
                        {
                            showToast("Answer sent");
                            clearEditView();
                        } else
                        {
                            showToast("Server not responding");
                        }
                    }
                }).start();
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

        if(question != null)
        {
            setQuestion(question);
            //loadAnswers();
            data = AnswersCollection.generate();
            updateUI();
        }

    }

    public void onResume()
    {
        super.onResume();
    }

    public void showToast(final String text)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clearEditView()
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                data = ServerAPI.getAnswers(question);
                updateUI();
            }
        }).start();
    }

    public void onSaveInstanceState(Bundle out)
    {
        out.putSerializable("collection", data);
        out.putSerializable("question", question);
    }


}
