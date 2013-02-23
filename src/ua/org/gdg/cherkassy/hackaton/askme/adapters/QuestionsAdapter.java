package ua.org.gdg.cherkassy.hackaton.askme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ua.org.gdg.cherkassy.hackaton.askme.R;
import ua.org.gdg.cherkassy.hackaton.askme.objects.QuestionsCollection;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsAdapter extends ArrayAdapter {

    private Context context;
    private QuestionsCollection values;

    public QuestionsAdapter(Context context, QuestionsCollection values) {
        super(context, R.layout.question_row, values);
        this.context = context;
        this.values = values;
    }

    public void setData(QuestionsCollection data)
    {
        this.values = data;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

	    if(convertView == null)
        {
            convertView =  inflater.inflate(R.layout.question_row, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(values.get(position).getTitle());

        return convertView;
    }

    public static class ViewHolder
    {
        TextView title;
    }

}
