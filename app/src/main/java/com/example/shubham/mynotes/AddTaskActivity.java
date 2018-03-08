package com.example.shubham.mynotes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    EditText editText,editText1,editText2,todoTime,todoDate;
    boolean AddMode=true;
    int position;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    String settime;
    String setdate;

    public static final String TASK_NAME_KEY="task_name";
    public static final String TASK_COST_kEY="task_cost";
    public static final String TASK_DESCRIPTION_kEY="task_description";
    public static final String TASK_ID_KEY="task_id";
    public static final String TASK_DATE_KEY="task_todo_date";
    public static final String TASK_TIME_KEY="task_todo_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        editText = findViewById(R.id.newtask);
        editText1 = findViewById(R.id.taskcost);
        editText2 =  findViewById(R.id.desp);
        todoDate = findViewById(R.id.todoDate);
        todoTime = findViewById(R.id.todoTime);
        calendar = Calendar.getInstance();
        Intent intent = getIntent();
        if (intent.hasExtra(TASK_NAME_KEY)) {
            String name = intent.getStringExtra(TASK_NAME_KEY);
            String cost=intent.getStringExtra(TASK_COST_kEY);
            String description = intent.getStringExtra(TASK_DESCRIPTION_kEY);
            String Date = intent.getStringExtra(TASK_DATE_KEY);
            String Time = intent.getStringExtra(TASK_TIME_KEY);
            position = intent.getIntExtra(ListActivity.POSITION_KEY, -1);
            editText.setText(name);
            editText1.setText(cost+"");
            editText2.setText(description);
            todoDate.setText(Date);
            todoTime.setText(Time);
            AddMode = false;
        }
        todoTime.setOnClickListener(new EditText.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Date date = new Date(System.currentTimeMillis());
//                date.toString();
//                long epoch = date.getTime();

                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {


                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String hr = i + "";
                        String min = i1 + "";
                        if(hr.length()==1){
                            hr = "0" + hr ;
                        }
                        if(min.length()==1){
                            min = "0" + min;
                        }
                        settime = (hr + ":" + min + "");
                        todoTime.setText(settime);
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), i, i1);
                    }


                }, hour, minute, true);
                timePickerDialog.setTitle("Set notification Time");
                timePickerDialog.show();
            }
        });


        todoDate.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year = calendar.get(Calendar.YEAR);
                int mon = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        int monthpro = i1 + 1;
                        String day = i2 + "";
                        String month = i1 + "";
                        String year = i + "";
                        if(day.length() == 1){
                            day = "0" + day;
                        }
                        if(month.length() == 1){
                            month = "0" +month;
                        }
                        if(year.length() == 1){
                            year = "0" + year;
                        }
                        setdate = (day + "-" + month + "-" + year);
                        todoDate.setText(setdate);
                        calendar.set(i, i1, i2, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

                    }
                }, year, mon, day);
                datePickerDialog.setTitle("Set notification Date");
                datePickerDialog.show();
            }
        });
    }


    public void save(View view){
        String taskString = editText.getText().toString();
        String taskCost  = editText1.getText().toString();
        String taskdescription = editText2.getText().toString();
        String taskDate = todoDate.getText().toString();
        String taskTime = todoTime.getText().toString();
        if(isNullOrEmpty(taskString)){
            Toast.makeText(this,"Title can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isNullOrEmpty(taskCost)){
            Toast.makeText(this,"Amount can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isNullOrEmpty(taskdescription)){
            Toast.makeText(this,"Description can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isNullOrEmpty(taskTime)){
            Toast.makeText(this,"Todo time can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isNullOrEmpty(taskDate)){
            Toast.makeText(this,"Todo date can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }

//        Calendar calendar1 = Calendar.getInstance();
//        String a = todoDate.getText().toString();
//        String b= todoTime.getText().toString();
//        calendar1.set(Calendar.YEAR,Integer.parseInt(a.substring(6,10)));
//        calendar1.set(Calendar.DAY_OF_MONTH,Integer.parseInt(a.substring(0,2)));
//        calendar1.set(Calendar.MONTH,Integer.parseInt(a.substring(3,5))-1);
//        calendar1.set(Calendar.HOUR_OF_DAY,Integer.parseInt(b.substring(0,2)));
//        calendar1.set(Calendar.MINUTE,Integer.parseInt(b.substring(3,5)));
//        calendar1.set(Calendar.SECOND,0);
//        calendar1.set(Calendar.MILLISECOND,0);
//
//        long epoch = calendar1.getTimeInMillis();


        Intent data = new Intent();
        data.putExtra(TASK_NAME_KEY,taskString);
        data.putExtra(TASK_COST_kEY,taskCost);
        data.putExtra(TASK_DESCRIPTION_kEY,taskdescription);
        data.putExtra(TASK_TIME_KEY,taskTime);
        data.putExtra(TASK_DATE_KEY,taskDate);
        if (AddMode){
            setResult(1,data);
        }
        else{
            data.putExtra(ListActivity.POSITION_KEY,position);
            setResult(2,data);
        }
        finish();
    }
    private boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }
}
