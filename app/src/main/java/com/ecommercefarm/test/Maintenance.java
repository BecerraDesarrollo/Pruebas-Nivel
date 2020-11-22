package com.ecommercefarm.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class Maintenance extends AppCompatActivity {
    private final Maintenance maintenance=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_layout);

    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseData data=new FirebaseData();

        data.setActionListeners(new FirebaseData.FireBaseDataEvent() {

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
                //Activa o desactiva un layout en función de si está o no en mantenimiento.
                if(!is){
                    intent=new Intent(maintenance,MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        data.service();
    }
}