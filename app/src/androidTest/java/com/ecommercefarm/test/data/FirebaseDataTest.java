package com.ecommercefarm.test.data;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirebaseDataTest {

    @Test
    public void load() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        FirebaseApp.initializeApp(appContext);

        FirebaseData.load();
    }
}