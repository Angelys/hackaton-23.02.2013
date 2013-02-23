package ua.org.gdg.cherkassy.hackaton.askme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ua.org.gdg.cherkassy.hackaton.askme.R;
import ua.org.gdg.cherkassy.hackaton.askme.objects.AnswersCollection;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnswersAdapter extends ArrayAdapter {

    private Context context;
    private AnswersCollection values;

    public AnswersAdapter(Context context, AnswersCollection values) {
        super(context, R.layout.answer_row, values);
        this.context = context;
        this.values = values;
    }

    public void setData(AnswersCollection data)
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
            convertView =  inflater.inflate(R.layout.answer_row, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(values.get(position).getBody());

        return convertView;
    }

    public static class ViewHolder
    {
        TextView title;
    }

}
