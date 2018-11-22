package com.ktverdov.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    private static String ID_KEY = "ID_KEY";

    public static ProfileFragment newInstance(final long id) {
        final ProfileFragment fragment = new ProfileFragment();

        final Bundle arguments = new Bundle();
        arguments.putLong(ID_KEY, id);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final long id = getArguments().getLong(ID_KEY);

        final Person person = PersonData.getPersonById(id);

        final TextView textView = view.findViewById(R.id.name);
        textView.setText(person.getName());

        final EditText editTextView = view.findViewById(R.id.note);
        editTextView.setText(person.getNote());


        final ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(person.getImageRes());
    }
}