package com.example.shubham.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {
    EditText editText,editText1,editText2;
    boolean AddMode=true;
    int position;
    public static final String TASK_NAME_KEY="task_name";
    public static final String TASK_COST_kEY="task_cost";
    public static final String TASK_DESCRIPTION_kEY="task_description";
    public static final String TASK_ID_KEY="task_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        editText = findViewById(R.id.newtask);
        editText1 = findViewById(R.id.taskcost);
        editText2 =  findViewById(R.id.desp);
        Intent intent = getIntent();
        if (intent.hasExtra(TASK_NAME_KEY)) {
            String name = intent.getStringExtra(TASK_NAME_KEY);
            String cost=intent.getStringExtra(TASK_COST_kEY);
            String description = intent.getStringExtra(TASK_DESCRIPTION_kEY);
            position = intent.getIntExtra(ListActivity.POSITION_KEY, -1);
            editText.setText(name);
            editText1.setText(cost+"");
            editText2.setText(description);
            AddMode = false;
        }
    }


    public void save(View view){
        String taskString = editText.getText().toString();
        String taskCost  = editText1.getText().toString();
        String taskdescription = editText2.getText().toString();
        Intent data = new Intent();
        data.putExtra(TASK_NAME_KEY,taskString);
        data.putExtra(TASK_COST_kEY,taskCost);
        data.putExtra(TASK_DESCRIPTION_kEY,taskdescription);
        if (AddMode){
            setResult(1,data);
        }
        else{
            data.putExtra(ListActivity.POSITION_KEY,position);
            setResult(2,data);
        }
        finish();
    }
}
