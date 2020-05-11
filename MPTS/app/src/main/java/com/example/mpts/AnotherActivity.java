package com.example.mpts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnotherActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTitleTv, mDescTv;
    ImageView mImageIv;
    Button mButton;
    Button sButton;
    Button lButton;
    Button nButton;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);



        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.title1);
        mDescTv = findViewById(R.id.description1);
        mImageIv = findViewById(R.id.imageView10);
        mButton = findViewById(R.id.but1);
        mButton.setOnClickListener(this);
        sButton = findViewById(R.id.but2);
        sButton.setOnClickListener(this);
        lButton = findViewById(R.id.but3);
        lButton.setOnClickListener(this);
        nButton = findViewById(R.id.but4);
        nButton.setOnClickListener(this);



        Intent intent = getIntent();

        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = intent.getStringExtra("iDesc");

        byte[] mBytes = getIntent().getByteArrayExtra("iImage");

        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);

        actionBar.setTitle(mTitle);

        mTitleTv.setText(mTitle);
        mDescTv.setText(mDescription);
        mImageIv.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.but1:
               Intent i = new Intent(getApplicationContext(),FeatureActivity.class);
               i.putExtra("element",mTitleTv.getText().toString());
               startActivity(i);
               break;
           case R.id.but2:
               Intent i2 = new Intent(getApplicationContext(),UsesActivity.class);
               i2.putExtra("element",mTitleTv.getText().toString());
               startActivity(i2);
               break;
           case R.id.but3:
               Intent i3 = new Intent(getApplicationContext(),CompundsActivity.class);
               i3.putExtra("element",mTitleTv.getText().toString());
               startActivity(i3);
               break;
           case R.id.but4:
               Intent i4 = new Intent(getApplicationContext(),InorganicActivity.class);
               i4.putExtra("element",mTitleTv.getText().toString());
               startActivity(i4);
               break;


       }
    }
}
