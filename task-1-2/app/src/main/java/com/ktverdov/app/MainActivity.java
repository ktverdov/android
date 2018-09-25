package com.ktverdov.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mEmailButton = findViewById(R.id.email_button);
        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toEmail = getResources().getString(R.string.to_email);
                String emailText = getResources().getString(R.string.email_text);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { toEmail });
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);

                startActivity(Intent.createChooser(emailIntent, "Open via"));
            }
        });


        Button mProfileButton = findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this,
                                                    ProfileActivity.class);
                startActivity(profileIntent);
            }
        });
    }
}
