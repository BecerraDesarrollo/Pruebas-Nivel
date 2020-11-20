package com.ecommercefarm.test.data;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class FirebaseDataTest {

    @Test
    public void load() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        new FirebaseData().load();

    }
}
