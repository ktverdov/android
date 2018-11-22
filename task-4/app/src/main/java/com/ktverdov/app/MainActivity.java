package com.ktverdov.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button listButton = findViewById(R.id.list_button);
        listButton.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()) {
            case R.id.list_button:
                openList();
                break;
        }
    }

    private void openList() {
        Intent profileIntent = ListActivity.getIntent(MainActivity.this);
        startActivity(profileIntent);
    }
}
