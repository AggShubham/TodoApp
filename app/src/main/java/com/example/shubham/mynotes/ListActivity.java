package com.example.shubham.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener , AdapterView.OnItemLongClickListener {
    public static final String POSITION_KEY = "position";
    ListView listView;
    ArrayList<TaskClass> tasks=new ArrayList<>();
    TaskAdapter adapter;
    OpenHelper openHelper;
    TaskClass task;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        openHelper=new OpenHelper(this);
        adapter = new TaskAdapter(this, tasks);

//        tasks = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TaskClass task=new TaskClass("Task",i*100);
//            tasks.add(task);
//        }

        listView.setAdapter(adapter);
        fetchTasksFromDb();
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String description=tasks.get(i).description;
        Log.d("TAGGER",description);
//        String description=null;
//        task = new TaskClass(description);
//        description=task.getDescription();
        Intent intent = new Intent(this, Description.class);
        intent.putExtra(AddTaskActivity.TASK_DESCRIPTION_kEY,description);
//        intent.putExtra(AddTaskActivity.TASK_DESCRIPTION_kEY,description);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, final long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final int a = i;
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete this task");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tasks.remove(a);
                database=openHelper.getWritableDatabase();
                String[] ids={l+""};
                database.delete(Contract.TaskClass.TABLE_NAME,Contract.TaskClass.ID + " = ? ",ids);
                adapter.notifyDataSetChanged();
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) ;
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, 1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name = null;
        int taskcost = 0;
        String description = null;
        if (requestCode == 1) {
            if (resultCode == 1) {
                task = new TaskClass(name, taskcost, description);
                task.name = data.getStringExtra(AddTaskActivity.TASK_NAME_KEY);
                task.taskcost = Integer.parseInt(data.getStringExtra(AddTaskActivity.TASK_COST_kEY));
                task.description = data.getStringExtra(AddTaskActivity.TASK_DESCRIPTION_kEY);


                database = openHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.TaskClass.TITLE,task.getName());
                contentValues.put(Contract.TaskClass.DESCRIPTION,task.getDescription());
                contentValues.put(Contract.TaskClass.COST,task.getTaskcost());

                long id = database.insert(Contract.TaskClass.TABLE_NAME,null,contentValues);
                task.setId((int) id);
                tasks.add(task);
                adapter.notifyDataSetChanged();
            }
        }
//        else if (requestCode == 2) {
//            if (resultCode == 2) {
//                String task = data.getStringExtra(AddTaskActivity.TASK_NAME_KEY);
//                int position = data.getIntExtra(POSITION_KEY, -1);
//                if (position != -1) {
//                    tasks.set(position, task);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        }
    }

    private void fetchTasksFromDb() {

        database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Contract.TaskClass.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int titleColumnIndex = cursor.getColumnIndex(Contract.TaskClass.TITLE);
            String title = cursor.getString(titleColumnIndex);
            String desc = cursor.getString(cursor.getColumnIndex(Contract.TaskClass.DESCRIPTION));
            int cost = cursor.getInt(cursor.getColumnIndex(Contract.TaskClass.COST));
            int id = cursor.getInt(cursor.getColumnIndex(Contract.TaskClass.ID));

            TaskClass task = new TaskClass(title, cost, desc, id);
            tasks.add(task);

        }
        adapter.notifyDataSetChanged();
    }
}