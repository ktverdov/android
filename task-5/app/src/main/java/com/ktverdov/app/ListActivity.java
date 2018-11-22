package com.ktverdov.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ListActivity extends AppCompatActivity implements ViewHolderListener {
    private static long curId;

    public static Intent getIntent(@NonNull final Context context) {
        return new Intent(context, ListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_placeholder, PersonListFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

        } else {
            final View profileView = findViewById(R.id.profile_placeholder);
            final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.list_placeholder);


            if (profileView != null && fragment instanceof ProfileFragment) {
                getSupportFragmentManager().popBackStack();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.profile_placeholder, ProfileFragment.newInstance(curId))
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    public void showProfileFragment(final long id) {
        final View profileView = findViewById(R.id.profile_placeholder);

        curId = id;

        if (profileView == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_placeholder, ProfileFragment.newInstance(id))
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.profile_placeholder, ProfileFragment.newInstance(id))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        final View profileView = findViewById(R.id.profile_placeholder);
        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.list_placeholder);

        if (getSupportFragmentManager().getBackStackEntryCount() == 1 ||
                profileView == null && fragment instanceof PersonListFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPersonClicked(long id) {
        showProfileFragment(id);
    }
}
