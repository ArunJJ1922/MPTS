package com.example.mpts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    EditText eText;
    Button b1;
    Feed feedBack;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        eText = (EditText)findViewById(R.id.ed1);
        b1 = (Button)findViewById(R.id.bt1);
        feedBack = new Feed();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = eText.getText().toString();
                eText.setVisibility(View.GONE);
                feedBack.setFd(f);
                ref.push().setValue(feedBack);
                Toast.makeText(getApplicationContext(), "Your Feedback is Successfully Submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
