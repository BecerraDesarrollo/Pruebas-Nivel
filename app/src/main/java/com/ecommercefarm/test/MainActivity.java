package com.ecommercefarm.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se crea un objeto de FirebaseData para que en caso que entre o salga de mantenimiento se gestione.
        FirebaseData data=new FirebaseData();
        data.setActionListeners(new FirebaseData.FireBaseDataEvent() {
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
                //Cambiamos de layout dependiendo de si está o no en mantenimiento.
                if(is){
                    setContentView(R.layout.maintenance_layout);
                }else{
                    setContentView(R.layout.activity_main);
                }
            }
        });

        data.service();

        Log.i("MainActivity","Created");
    }
}
