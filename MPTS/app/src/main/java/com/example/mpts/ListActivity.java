package com.example.mpts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);





        mRecyclerView = findViewById(R.id.recyclerView);

        preferences = this.getSharedPreferences("My Pref", MODE_PRIVATE);

        getMyList();


    }

    private void getMyList() {

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Hydrogen");
        m.setDescription("At.no:1");
        m.setImg(R.drawable.h1);
        models.add(m);

        m = new Model();
        m.setTitle("Helium");
        m.setDescription("At.no:2");
        m.setImg(R.drawable.he1);
        models.add(m);

        m = new Model();
        m.setTitle("Lithium");
        m.setDescription("At.no:3");
        m.setImg(R.drawable.lit1);
        models.add(m);

        m = new Model();
        m.setTitle("Beryllium");
        m.setDescription("At.no:4");
        m.setImg(R.drawable.be1);
        models.add(m);

        m = new Model();
        m.setTitle("Boron");
        m.setDescription("At.no:5");
        m.setImg(R.drawable.bo);
        models.add(m);

        m = new Model();
        m.setTitle("Carbon");
        m.setDescription("At.no:6");
        m.setImg(R.drawable.c);
        models.add(m);

        m = new Model();
        m.setTitle("Nitrogen");
        m.setDescription("At.no:7");
        m.setImg(R.drawable.n1);
        models.add(m);

        m = new Model();
        m.setTitle("Oxygen");
        m.setDescription("At.no:8");
        m.setImg(R.drawable.o1);
        models.add(m);

        m = new Model();
        m.setTitle("Fluorine");
        m.setDescription("At.no:9");
        m.setImg(R.drawable.fll);
        models.add(m);

        m = new Model();
        m.setTitle("Neon");
        m.setDescription("At.no:10");
        m.setImg(R.drawable.ne1);
        models.add(m);

        String mSortSetting = preferences.getString("Sort", "ascending");

        if (mSortSetting.equals("ascending")) {
            Collections.sort(models, Model.By_TITLE_ASCENDING);
        }
        else if (mSortSetting.equals("descending")) {
            Collections.sort(models, Model.By_TITLE_DESCENDING);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, models);
        mRecyclerView.setAdapter(myAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                myAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sorting) {
            sortDailog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDailog() {

        String[] options = {"Ascending", "Dscending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                 if (which == 0) {
                     SharedPreferences.Editor editor = preferences.edit();
                     editor.putString("Sort", "ascending");
                     editor.apply();
                     getMyList();
                 }

                 if (which == 1) {
                     SharedPreferences.Editor editor = preferences.edit();
                     editor.putString("Sort", "descending");
                     editor.apply();
                     getMyList();


                 }


            }
        });

        builder.create().show();

    }








}
