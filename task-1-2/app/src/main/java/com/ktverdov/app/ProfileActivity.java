package com.ktverdov.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.EditText;


public class ProfileActivity extends AppCompatActivity {

//    private EditText mTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        mTextInput = findViewById(R.id.text_input);
//
//        if (savedInstanceState != null) {
//            mTextInput.setText(savedInstanceState.getString("profile_note"));
//        }
    }

//    @Override
//    protected void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putString("profile_note", mTextInput.getText().toString());
//        super.onSaveInstanceState(savedInstanceState);
//    }
//
//    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mTextInput.setText(savedInstanceState.getString("profile_note"));
//    }
}