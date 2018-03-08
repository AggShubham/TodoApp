package com.example.shubham.mynotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Shubham on 15-02-2018.
 */

public class TaskAdapter extends BaseAdapter {
    Context context;
    ArrayList<TaskClass> tasks;
    EditButtonClickListener listener;

    interface EditButtonClickListener{
        void onEditButtonClicked(int pos);
    }

    public TaskAdapter(Context context, ArrayList<TaskClass> tasks, EditButtonClickListener listener) {
        this.context = context;
        this.tasks = tasks;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tasks.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = inflater.inflate(R.layout.item_list, viewGroup, false);
            viewHolder holder = new viewHolder();
            holder.name = view1.findViewById(R.id.item);
            holder.price = view1.findViewById(R.id.cost);
            holder.date = view1.findViewById(R.id.date);
            holder.button = view1.findViewById(R.id.edit);
            view1.setTag(holder);
        }
        viewHolder holder1= (viewHolder) view1.getTag();
        final TaskClass task = tasks.get(i);
        holder1.name.setText(task.getName());
        holder1.price.setText(task.getTaskcost()+"");
        holder1.date.setText(task.getDate());
        holder1.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditButtonClicked(i);
            }
        });

        return view1;
    }
    class viewHolder{
        TextView name;
        TextView price;
        TextView date;
        Button button;
    }
}
