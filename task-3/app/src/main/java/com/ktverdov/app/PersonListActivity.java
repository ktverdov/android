package com.ktverdov.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PersonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 15);

        final PersonAdapter adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);

        PersonData.initialize(this);
        adapter.setPersonList(PersonData.getPersonList());
    }
}