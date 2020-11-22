package com.ecommercefarm.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommercefarm.test.data.FirebaseData;
import com.ecommercefarm.test.data.Genre;

public class FilterFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    public void onViewCreated(@NonNull View viewFragment, Bundle savedInstanceState) {
        super.onViewCreated(viewFragment, savedInstanceState);

        //Se instancia el RecyclerView//
        RecyclerView recyclerView=viewFragment.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        ////////

        //Se instancia FirebaseData//
        FirebaseData data=new FirebaseData();
        //Se crea una escucha para que cuando estén los datos cargados se cree la lista.
        data.setActionListeners(new FirebaseData.FireBaseDataEvent() {
            @Override
            public void loaded() {

                recyclerView.setAdapter(new ListAdapter());

                Log.i("FilterFragment","Created");
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

        //Se instancia el switch que controlara el filtro//
        Switch filter = viewFragment.findViewById(R.id.filter);
        filter.setChecked(true);
        //Se crea una escucha para pasar al otro fragment
        filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    data.removeListeners();
                    NavHostFragment.findNavController(FilterFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        });
        ////////

        //Se crea una lsta vacia
        data.loadFilter(Genre.EMPTY);
        ////////

        //Se instancia el spinner que muetra los géneros para poder filtar
        Spinner spinner=viewFragment.findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);

        //Escuchamos eventos de selección en spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (adapterView.getSelectedItem().toString()){
                    case "Fantasía":
                        data.loadFilter(Genre.FANTASY);
                        break;
                    case  "Crimen":
                        data.loadFilter(Genre.CRIME);
                        break;
                    case "Romance":
                        data.loadFilter(Genre.ROMANCE);
                        break;
                    default:
                        data.loadFilter(Genre.EMPTY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                data.loadFilter(Genre.EMPTY);

            }
        });
    }

}