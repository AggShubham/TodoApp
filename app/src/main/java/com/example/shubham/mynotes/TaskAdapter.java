package com.example.shubham.mynotes;

import android.content.Context;
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

    public TaskAdapter(Context context, ArrayList<TaskClass> tasks) {
        this.context = context;
        this.tasks = tasks;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = inflater.inflate(R.layout.item_list, viewGroup, false);
            viewHolder holder = new viewHolder();
            holder.name = view1.findViewById(R.id.item);
            holder.price = view1.findViewById(R.id.cost);
            holder.button = view1.findViewById(R.id.edit);
            view1.setTag(holder);
        }
        viewHolder holder1= (viewHolder) view1.getTag();
        final TaskClass task = tasks.get(i);
        holder1.name.setText(task.getName());
        holder1.price.setText(task.getTaskcost()+"");

        return view1;
    }
    class viewHolder{
        TextView name;
        TextView price;
        Button button;
    }
}
