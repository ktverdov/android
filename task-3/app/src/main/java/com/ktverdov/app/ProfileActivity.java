package com.ktverdov.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private static final String ID_KEY = "ID_KEY";

    public static Intent getIntent(final Context context, final long id) {
        final Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final long id = getIntent().getLongExtra(ID_KEY, -1);
        final Person person = PersonData.getPersonById(id);

        final TextView textView = findViewById(R.id.name);
        textView.setText(person.getName());

        final EditText editTextView = findViewById(R.id.note);
        editTextView.setText(person.getNote());

        final ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(person.getImageRes());
    }
}