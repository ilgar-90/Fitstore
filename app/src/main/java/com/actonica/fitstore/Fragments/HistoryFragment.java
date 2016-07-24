package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actonica.fitstore.Adapters.ExtendedProgramsAdapter;
import com.actonica.fitstore.Adapters.HistoryProgramsAdapter;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;
import com.actonica.fitstore.Models.HistoryProgram;
import com.actonica.fitstore.R;

import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView history_rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        history_rv = (RecyclerView)v.findViewById(R.id.history);
        history_rv.setHasFixedSize(true);

        List<HistoryProgram> history = SharedPrefsHelper.getProgramsHistory(getActivity().getApplicationContext());

        HistoryProgramsAdapter adapter = new HistoryProgramsAdapter(getActivity(), history);
        history_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        history_rv.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider));
        history_rv.setAdapter(adapter);


        return v;
    }
}
