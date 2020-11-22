package com.ecommercefarm.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class Maintenance extends AppCompatActivity {
    private final Maintenance maintenance=this;
    private FirebaseData service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service=new FirebaseData();

        service.setActionListeners(new FirebaseData.FireBaseDataEvent() {

            Intent intent=new Intent();
            @Override
            public void loaded() {
                //Se ignora
            }

            @Override
            public void canceled() {
                //Se ignora
            }

            @Override
            public void maintenance(boolean is) {
                //Se desactiva un layout de mantenimiento.
                if(!is){
                    service.removeListeners();
                    intent=new Intent(maintenance,MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        setContentView(R.layout.maintenance_layout);

    }


    @Override
    protected void onResume() {
        super.onResume();
        service.service();
    }
}