package com.example.b07project.ScheduledEvents_studentview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.b07project.LoginAndSignup.Student_dashboardActivity;
import com.example.b07project.R;
import com.example.b07project.complaints_studentview.StudentComplaint;
import com.example.b07project.dbOperation_Information.CreateItem;
import com.example.b07project.dbOperation_Information.CreateOperation;
import com.example.b07project.dbOperation_Information.DefaultCallback;
import com.example.b07project.main.CheckValidity;
import com.example.b07project.main.Feedback;

public class Feedback_StudentActivity extends AppCompatActivity {

    NumberPicker numberpicker;
    String comment;
    int numberrate;
    String eventid;

    String generateComment(){
        EditText FeedbackContent = findViewById(R.id.ContentEdit);
        comment = FeedbackContent.getText().toString();
        return comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_student);

        eventid = getIntent().getStringExtra("EVENTID");
        numberpicker = findViewById(R.id.number_picker);
        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(10);

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCommentAndRate();
            }
        });

        findViewById(R.id.back_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to dashboard
               finish();
            }
        });
    }

    private void generateCommentAndRate(){
        if(CheckValidity.checkLongValidity(generateComment())){
            numberrate = numberpicker.getValue();
            CreateOperation newItem = new CreateItem();
            Feedback feedback = new Feedback(numberrate, comment, eventid);
            newItem.create("Feedback/" + feedback.EventID, feedback, new DefaultCallback() {
                @Override
                public void onSuccess() {
                    showToast("Submit Success!");
                    finish();
                }

                @Override
                public void onFailure(Exception e) {
                    showToast(e.getMessage());
                }
            });
        } else{
            showToast ("the number of characters of the comment must be within 1 to 5000");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}