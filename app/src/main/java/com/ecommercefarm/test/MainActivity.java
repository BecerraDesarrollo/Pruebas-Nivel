package com.ecommercefarm.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommercefarm.test.data.FirebaseData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
