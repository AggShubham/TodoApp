package com.example.shubham.mynotes;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CommentActivity extends AppCompatActivity {
    EditText editText;
    public static final String TASK_COMMENT_KEY = "comment";
    boolean Add_Mode = true;
//    OpenHelper openHelper;
//    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        editText = findViewById(R.id.addcomment);
        Intent intent = getIntent();
        if (intent.hasExtra(TASK_COMMENT_KEY)) {
            String comment = intent.getStringExtra(TASK_COMMENT_KEY);
            editText.setText(comment);
            Add_Mode = false;
        }
    }
    public void save_comment(View view) {
        String taskComment = editText.getText().toString();
        Intent data = new Intent();
        data.putExtra(TASK_COMMENT_KEY,taskComment);
        if (Add_Mode){
            setResult(1,data);
        }
        finish();
    }

}
