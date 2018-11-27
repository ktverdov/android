package com.ktverdov.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PersonListFragment extends Fragment {
    public static PersonListFragment newInstance() {
        return new PersonListFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_list, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 15);

        final PersonAdapter adapter = new PersonAdapter((ViewHolderListener) getActivity());
        recyclerView.setAdapter(adapter);

        PersonData.initialize(getActivity());
        adapter.setPersonList(PersonData.getPersonList());
    }
}
