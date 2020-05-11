package com.example.mpts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    TextView fullName, email, phone;
    String mName, mMail, mPhone;
    Toolbar toolbar;
    Button goTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.profileFullName);
        email = findViewById(R.id.profileEmail);
        phone = findViewById(R.id.profilePhone);
        goTo = findViewById(R.id.goTo);

        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });


        DocumentReference docRef = fStore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    mName = documentSnapshot.getString("first") + " " + documentSnapshot.getString("last");
                    mMail = documentSnapshot.getString("email");
                    mPhone = firebaseAuth.getCurrentUser().getPhoneNumber();


                    fullName.setText(mName);
                    email.setText(mMail);
                    phone.setText(mPhone);


                } else {
                    Log.d(TAG, "Retreiving Data: Profile Data Not Found");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}






