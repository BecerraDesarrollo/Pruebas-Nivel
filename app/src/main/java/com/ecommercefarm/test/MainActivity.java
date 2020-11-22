package com.ecommercefarm.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class MainActivity extends AppCompatActivity {
    private final MainActivity mainActivity=this;
    private FirebaseData service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                //Sen activa un layout de mantenimiento.
                if(is){
                    service.removeListeners();
                    intent=new Intent(mainActivity,Maintenance.class);
                    startActivity(intent);

                }
            }
        });

        Log.i("MainActivity","Created");
    }

    @Override
    protected void onResume() {
        super.onResume();
        service.service();
    }
}
