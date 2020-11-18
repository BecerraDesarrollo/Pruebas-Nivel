package com.ecommercefarm.test;

import android.os.Bundle;

import com.ecommercefarm.test.data.Film;
import com.ecommercefarm.test.data.FilmList;
import com.ecommercefarm.test.data.FirebaseData;
import com.ecommercefarm.test.data.RemoteList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("MainActivity","Created");
    }


}