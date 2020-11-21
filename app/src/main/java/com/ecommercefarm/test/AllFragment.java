package com.ecommercefarm.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommercefarm.test.data.FirebaseData;

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

        //Se instancia el RecyclerView//
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        ////////

        //Se instancia el switch que controlara el filtro//
        Switch filter = view.findViewById(R.id.filter);
        filter.setChecked(false);
        //Se crea una escucha para pasar al otro fragment
        filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    NavHostFragment.findNavController(AllFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            }
        });
        ////////

        //Se instancia FirebaseData//
        FirebaseData data=new FirebaseData();
        //Se crea una escucha para que cuando estén los datos cargados se cree la lista.
        data.setActionListeners(new FirebaseData.FireBaseDataEvent() {
            @Override
            public void loaded() {

                recyclerView.setAdapter(new ListAdapter());

                Log.i("AllFragment","Created");
            }

            @Override
            public void canceled() {
                Toast.makeText(getContext(),"Carga de datos cancelada",Toast.LENGTH_SHORT);
            }

            @Override
            public void maintenance(boolean is) {
                Log.i("AllFragment","maintenance");
            }
        });
        //Se intenta cargar todos los datos
        data.loadAll();
        ////////

    }

}