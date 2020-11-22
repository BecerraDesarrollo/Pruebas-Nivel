package com.ecommercefarm.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class MainActivity extends AppCompatActivity {
    public static FirebaseData service;
    private final MainActivity mainActivity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("MainActivity","Created");
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
                if(is){
                    intent=new Intent(mainActivity,Maintenance.class);
                    startActivity(intent);

                }
            }
        });

        data.service();
        service=data;
    }
}
