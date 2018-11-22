package com.ktverdov.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PersonListFragment extends Fragment {
    private static ListLoadingTask loadingTask;
    private ProgressBarManager progressBar;

    public PersonListFragment() {
        progressBar = new ProgressBarManager();
    }

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

        progressBar.initialize(view);
        loadingTask = new ListLoadingTask(adapter);
        loadingTask.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (loadingTask != null) {
            loadingTask.cancel(true);
        }
    }

    private class ListLoadingTask extends AsyncTask<Void, Integer, List<Person>> {
        private PersonAdapter adapter;

        public ListLoadingTask(PersonAdapter adapter_) {
            super();
            adapter = adapter_;
        }

        @Override
        protected void onPreExecute() {
            if (!PersonData.isInitialized()) {
                progressBar.show();
            }

            super.onPreExecute();
        }

        @Override
        protected List<Person> doInBackground(Void... params) {
            try {
                if (!PersonData.isInitialized()) {
                    Integer max_steps = 5;
                    for (int i = 0; i < max_steps; i++) {
                        if (isCancelled()) {
                            return null;
                        }

                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                        publishProgress((int) ((double) (i + 1) / max_steps * 100));
                    }

                    PersonData.initialize(getActivity());
                }

                return PersonData.getPersonList();

            } catch (InterruptedException e) {
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.update(values[0]);
        }

        @Override
        protected void onPostExecute(List<Person> result) {
            super.onPostExecute(result);
            progressBar.hide();
            adapter.setPersonList(result);
        }
    }


    private class ProgressBarManager {
        private ProgressBar progressBar;

        public void initialize(View view) {
            progressBar = view.findViewById(R.id.progress_bar);
        }

        public void show() {
            progressBar.setVisibility(View.VISIBLE);
        }

        public void update(int value) {
            progressBar.setProgress(value);
        }

        public void hide() {
            progressBar.setVisibility(View.GONE);
        }
    }
}
