package com.example.mpts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsesActivity extends AppCompatActivity {

    DatabaseReference reff = FirebaseDatabase.getInstance().getReference();


    ArrayList<String> use = new ArrayList<>();
    ListView sList;


    TextView mUsesTv;
    TextView et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uses);

        et = findViewById(R.id.tv);
        final String home ="Home";
        final String chat ="Chat";
        final String about ="About Us";
        final String feedback ="Feedback";
        final String picture ="PT Image";


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.action_home:
                        et.setText(home);
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.action_chat:
                        et.setText(chat);
                        Intent i1 = new Intent(getApplicationContext(),ChatActivity.class);
                        startActivity(i1);
                        break;
                    case R.id.action_about:
                        et.setText(about);
                        Intent i2 = new Intent(getApplicationContext(),AboutActivity.class);
                        startActivity(i2);
                        break;
                    case R.id.action_feedback:
                        et.setText(feedback);
                        Intent i3 = new Intent(getApplicationContext(),FeedbackActivity.class);
                        startActivity(i3);
                        break;
                    case R.id.action_pic:
                        et.setText(picture);
                        Intent i4 = new Intent(getApplicationContext(),ZoomActivity.class);
                        startActivity(i4);
                        break;

                }
                return true;




            }
        });
        mUsesTv = findViewById(R.id.title4);
        mUsesTv.setText(getIntent().getExtras().getString("element"));
        String el = mUsesTv.getText().toString();
        String ele = el + "uses";
        DatabaseReference fRef = reff.child(ele);
        sList = findViewById(R.id.listView);
        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,use);
        sList.setAdapter(arrayAdapter);
        fRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value =dataSnapshot.getValue(String.class);
                use.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
