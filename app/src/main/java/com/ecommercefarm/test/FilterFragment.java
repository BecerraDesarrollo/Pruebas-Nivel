package com.ecommercefarm.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommercefarm.test.data.Film;
import com.ecommercefarm.test.data.RemoteList;

public class FilterFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RemoteList.setActionListeners(new RemoteList.RemoteListEvent() {
            @Override
            public void loaded() {

                Switch filter = view.findViewById(R.id.filter);
                filter.setChecked(true);

                filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(!isChecked){
                            NavHostFragment.findNavController(FilterFragment.this)
                                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
                        }
                    }
                });

                RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new ListAdapter());
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);

                Log.i("FilterFragment","Created");
            }
        });
        RemoteList.load(RemoteList.Filter.YES);

    }
}