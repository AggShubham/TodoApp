package com.example.shubham.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Description extends AppCompatActivity {
    TextView textView,textView1;
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
        textView1 = findViewById(R.id.time);
        ListView listView = findViewById(R.id.commentslist);
        Intent intent = getIntent();
        String desc = intent.getStringExtra("task_description");
        String time = intent.getStringExtra("task_todo_time");
        taskid = intent.getIntExtra("task_id",-1);
        textView.setText(desc);
        textView1.setText(time);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,comments);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i,final long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Description.this);
                final int a = i;
                alert.setTitle("Delete Comment!!");
                alert.setMessage("Are you sure to delete this comment");
                alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        comments.remove(a);
                        database=openHelper.getWritableDatabase();
                        String[] ids={l+""};
                        database.delete(Contract.Comments.TABLE_NAME,Contract.Comments.TASK_ID + " = ? ",ids);
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
        });
        fetchCommentsFromDb();
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
    private void fetchCommentsFromDb(){
        String [] selectionArgs = {taskid+""};
        openHelper = OpenHelper.getInstance(this);
        database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Contract.Comments.TABLE_NAME,null,Contract.Comments.TASK_ID + " = ?",selectionArgs,null,null,null,null);
        while(cursor.moveToNext()){
            String comment = cursor.getString(cursor.getColumnIndex(Contract.Comments.COMMENT));
            int id = cursor.getInt(cursor.getColumnIndex(Contract.Comments.TASK_ID));
            CommentsClass commentsClass = new CommentsClass(comment,id);
            comments.add(comment);
        }
    }

}