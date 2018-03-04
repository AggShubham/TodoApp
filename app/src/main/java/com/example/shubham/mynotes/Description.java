package com.example.shubham.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Description extends AppCompatActivity {
    TextView textView;
    Context context;
    ArrayList<String> comments = new ArrayList<>();
    SQLiteDatabase database;
    ArrayAdapter<String> adapter;
    OpenHelper openHelper;
    int taskid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        textView = findViewById(R.id.description);
        ListView listView = findViewById(R.id.commentslist);
        Intent intent = getIntent();
        String desc = intent.getStringExtra("task_description");
        taskid = intent.getIntExtra("task_id",-1);
        textView.setText(desc);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_description, comments);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_comment) ;
        Intent intent = new Intent(this, CommentActivity.class);
        startActivityForResult(intent, 1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String comment = null;
        if (requestCode == 1) {
            if (resultCode == 1) {
                comment = data.getStringExtra(CommentActivity.TASK_COMMENT_KEY);
                CommentsClass commentsClass = new CommentsClass(comment,taskid);
                openHelper = OpenHelper.getInstance(this);
                database = openHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.Comments.COMMENT, comment);
                contentValues.put(Contract.Comments.TASK_ID,commentsClass.getTaskid());

                database.insert(Contract.Comments.TABLE_NAME, null, contentValues);

                comments.add(comment);
                adapter.notifyDataSetChanged();


            }
        }
    }
}