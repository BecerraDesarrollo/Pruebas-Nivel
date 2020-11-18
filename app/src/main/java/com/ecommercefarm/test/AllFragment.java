package com.ecommercefarm.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommercefarm.test.data.Film;
import com.ecommercefarm.test.data.FilmList;
import com.ecommercefarm.test.data.RemoteList;

public class AllFragment extends Fragment {

    private ViewGroup container;
    private View content;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RemoteList.setActionListeners(new RemoteList.RemoteListEvent() {
            @Override
            public void loaded() {

                Switch filter = view.findViewById(R.id.filter);
                filter.setChecked(false);

                filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            NavHostFragment.findNavController(AllFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
                        }
                    }
                });

                RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new ListAdapter());
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);


                Log.i("AllFragment","Created");
            }
        });

        RemoteList.load(RemoteList.Filter.NOT);

    }
}